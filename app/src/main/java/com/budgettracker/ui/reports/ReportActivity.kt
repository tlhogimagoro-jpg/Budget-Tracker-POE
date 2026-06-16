package com.budgettracker.ui.reports

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.budgettracker.data.AppDatabase
import com.budgettracker.databinding.ActivityReportBinding
import com.budgettracker.ui.adapters.CategoryTotalAdapter
import com.budgettracker.utils.DateUtils
import com.budgettracker.utils.SessionManager
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * ReportActivity — shows total amount spent per category over a user-selected period.
 * Includes a BarChart visualization with min/max budget goal lines.
 */
class ReportActivity : AppCompatActivity() {

    private val TAG = "ReportActivity"
    private lateinit var binding: ActivityReportBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: CategoryTotalAdapter

    private var fromDateMs = DateUtils.startOfMonth(DateUtils.currentMonth(), DateUtils.currentYear())
    private var toDateMs   = DateUtils.endOfMonth(DateUtils.currentMonth(), DateUtils.currentYear())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Spending Report"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sessionManager = SessionManager(this)

        binding.etFromDate.setText(DateUtils.formatDate(fromDateMs))
        binding.etToDate.setText(DateUtils.formatDate(toDateMs))

        setupRecyclerView()
        setupDatePickers()
        setupChart()

        binding.btnGenerateReport.setOnClickListener { generateReport() }
        
        // Initial report
        generateReport()
    }

    private fun setupRecyclerView() {
        adapter = CategoryTotalAdapter()
        binding.rvCategoryTotals.layoutManager = LinearLayoutManager(this)
        binding.rvCategoryTotals.adapter = adapter
    }

    private fun setupChart() {
        binding.barChart.apply {
            description.isEnabled = false
            setDrawGridBackground(false)
            setDrawBarShadow(false)
            setFitBars(true)
            
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                granularity = 1f
                labelRotationAngle = -45f
            }
            
            axisLeft.apply {
                setDrawGridLines(true)
                axisMinimum = 0f
            }
            axisRight.isEnabled = false
            legend.isEnabled = false
        }
    }

    private fun setupDatePickers() {
        val openFrom = {
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("From date")
                .setSelection(fromDateMs)
                .build()
                .apply {
                    addOnPositiveButtonClickListener { ms ->
                        fromDateMs = DateUtils.startOfDay(ms)
                        binding.etFromDate.setText(DateUtils.formatDate(fromDateMs))
                    }
                }.show(supportFragmentManager, "fromDate")
        }
        val openTo = {
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("To date")
                .setSelection(toDateMs)
                .build()
                .apply {
                    addOnPositiveButtonClickListener { ms ->
                        toDateMs = DateUtils.endOfDay(ms)
                        binding.etToDate.setText(DateUtils.formatDate(toDateMs))
                    }
                }.show(supportFragmentManager, "toDate")
        }

        binding.etFromDate.setOnClickListener { openFrom() }
        binding.tilFromDate.setEndIconOnClickListener { openFrom() }
        binding.etToDate.setOnClickListener { openTo() }
        binding.tilToDate.setEndIconOnClickListener { openTo() }
    }

    private fun generateReport() {
        if (fromDateMs > toDateMs) {
            binding.tilFromDate.error = "From date must be before To date"
            return
        }
        binding.tilFromDate.error = null

        val userId = sessionManager.getUserId()
        val db     = AppDatabase.getDatabase(this)

        lifecycleScope.launch {
            val totals     = db.expenseDao().getCategoryTotals(userId, fromDateMs, toDateMs)
            val categories = db.categoryDao().getCategoriesByUserSync(userId)
            val catMap     = categories.associateBy { it.id }

            // Fetch goals for the "from" date's month to display as reference lines
            val cal = Calendar.getInstance().apply { timeInMillis = fromDateMs }
            val goal = db.goalDao().getGoalForMonth(userId, cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR))

            val grandTotal = totals.sumOf { it.total }

            val rows = totals.map { ct ->
                val cat     = ct.categoryId?.let { catMap[it] }
                val name    = cat?.name ?: "Uncategorised"
                val color   = cat?.colorHex ?: "#9E9E9E"
                val percent = if (grandTotal > 0) ((ct.total / grandTotal) * 100).toInt() else 0
                CategoryTotalAdapter.Row(name, color, ct.total, percent)
            }.sortedByDescending { it.total }

            runOnUiThread {
                updateUI(rows, grandTotal, goal)
            }
        }
    }

    private fun updateUI(rows: List<CategoryTotalAdapter.Row>, grandTotal: Double, goal: com.budgettracker.data.entities.MonthlyGoal?) {
        if (rows.isEmpty()) {
            binding.tvEmpty.visibility         = View.VISIBLE
            binding.rvCategoryTotals.visibility = View.GONE
            binding.cardChart.visibility       = View.GONE
            binding.tvGrandTotal.text          = ""
        } else {
            binding.tvEmpty.visibility         = View.GONE
            binding.rvCategoryTotals.visibility = View.VISIBLE
            binding.cardChart.visibility       = View.VISIBLE
            binding.tvGrandTotal.text          = "Grand Total: R %.2f".format(grandTotal)
            adapter.submitList(rows)
            
            updateChart(rows, goal)
        }
    }

    private fun updateChart(rows: List<CategoryTotalAdapter.Row>, goal: com.budgettracker.data.entities.MonthlyGoal?) {
        val entries = rows.mapIndexed { index, row ->
            BarEntry(index.toFloat(), row.total.toFloat())
        }

        val dataSet = BarDataSet(entries, "Spending per Category")
        dataSet.colors = rows.map { Color.parseColor(it.colorHex) }
        dataSet.valueTextSize = 10f
        dataSet.setDrawValues(true)

        binding.barChart.apply {
            data = BarData(dataSet)
            xAxis.valueFormatter = IndexAxisValueFormatter(rows.map { it.categoryName })
            
            axisLeft.removeAllLimitLines()
            goal?.let {
                val minLine = LimitLine(it.minGoal.toFloat(), "Min Goal").apply {
                    lineColor = Color.BLUE
                    lineWidth = 2f
                    textColor = Color.BLUE
                    textSize = 10f
                    labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
                }
                val maxLine = LimitLine(it.maxGoal.toFloat(), "Max Goal").apply {
                    lineColor = Color.RED
                    lineWidth = 2f
                    textColor = Color.RED
                    textSize = 10f
                    labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
                }
                axisLeft.addLimitLine(minLine)
                axisLeft.addLimitLine(maxLine)
            }
            
            invalidate()
            animateY(1000)
        }
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
