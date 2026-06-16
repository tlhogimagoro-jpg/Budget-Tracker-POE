package com.budgettracker.ui.expenses

import android.Manifest
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.budgettracker.data.AppDatabase
import com.budgettracker.data.entities.Category
import com.budgettracker.data.entities.ExpenseEntry
import com.budgettracker.databinding.ActivityAddExpenseBinding
import com.budgettracker.utils.DateUtils
import com.budgettracker.utils.SessionManager
import com.budgettracker.utils.ValidationUtils
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * AddExpenseActivity — Form for logging expenses with robust validation.
 */
class AddExpenseActivity : AppCompatActivity() {

    private val TAG = "AddExpenseActivity"
    private lateinit var binding: ActivityAddExpenseBinding
    private lateinit var sessionManager: SessionManager

    private var selectedDateMs: Long = DateUtils.startOfDay(System.currentTimeMillis())
    private var categories: List<Category> = emptyList()
    private var selectedCategoryId: Int? = null
    private var photoPath: String? = null
    private var cameraImageUri: Uri? = null

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success && cameraImageUri != null) {
            photoPath = cameraImageUri!!.path
            binding.ivPhotoPreview.visibility = android.view.View.VISIBLE
            binding.ivPhotoPreview.setImageURI(cameraImageUri)
        }
    }

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            val destFile = createImageFile()
            contentResolver.openInputStream(uri)?.use { input ->
                destFile.outputStream().use { output -> input.copyTo(output) }
            }
            photoPath = destFile.absolutePath
            binding.ivPhotoPreview.visibility = android.view.View.VISIBLE
            binding.ivPhotoPreview.setImageURI(Uri.fromFile(destFile))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        if (sessionManager.getUserId() == -1) { finish(); return }

        supportActionBar?.title = "Add Expense"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.etDate.setText(DateUtils.formatDate(selectedDateMs))
        setupDatePicker()
        setupTimePickers()
        setupPhotoButtons()
        loadCategories()

        binding.btnSaveExpense.setOnClickListener { saveExpense() }
    }

    private fun setupDatePicker() {
        binding.etDate.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(selectedDateMs)
                .build()
            picker.addOnPositiveButtonClickListener { ms ->
                selectedDateMs = DateUtils.startOfDay(ms)
                binding.etDate.setText(DateUtils.formatDate(selectedDateMs))
            }
            picker.show(supportFragmentManager, "datePicker")
        }
    }

    private fun setupTimePickers() {
        val calendar = Calendar.getInstance()
        val timeSetListener = { isStart: Boolean ->
            TimePickerDialog(this, { _, h, m ->
                val time = "%02d:%02d".format(h, m)
                if (isStart) binding.etStartTime.setText(time) else binding.etEndTime.setText(time)
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
        binding.etStartTime.setOnClickListener { timeSetListener(true) }
        binding.etEndTime.setOnClickListener { timeSetListener(false) }
    }

    private fun setupPhotoButtons() {
        binding.btnTakePhoto.setOnClickListener { launchCamera() }
        binding.btnChoosePhoto.setOnClickListener { pickImageLauncher.launch("image/*") }
    }

    private fun launchCamera() {
        val imageFile = createImageFile()
        cameraImageUri = FileProvider.getUriForFile(this, "${packageName}.fileprovider", imageFile)
        takePictureLauncher.launch(cameraImageUri)
    }

    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("EXPENSE_${timestamp}_", ".jpg", storageDir)
    }

    private fun loadCategories() {
        lifecycleScope.launch {
            categories = AppDatabase.getDatabase(this@AddExpenseActivity)
                .categoryDao()
                .getCategoriesByUserSync(sessionManager.getUserId())

            runOnUiThread {
                val adapter = ArrayAdapter(this@AddExpenseActivity, android.R.layout.simple_dropdown_item_1line, categories.map { it.name })
                binding.actvCategory.setAdapter(adapter)
                binding.actvCategory.setOnItemClickListener { _, _, position, _ ->
                    selectedCategoryId = categories[position].id
                }
            }
        }
    }

    private fun saveExpense() {
        val description = binding.etDescription.text.toString().trim()
        val amountStr = binding.etAmount.text.toString().trim()
        val startTime = binding.etStartTime.text.toString().trim()
        val endTime = binding.etEndTime.text.toString().trim()
        val userId = sessionManager.getUserId()

        if (description.isEmpty() || !ValidationUtils.isValidAmount(amountStr) || selectedCategoryId == null) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val expense = ExpenseEntry(
            userId = userId,
            categoryId = selectedCategoryId,
            description = description,
            amount = amountStr.toDouble(),
            date = selectedDateMs,
            startTime = startTime,
            endTime = endTime,
            photoPath = photoPath
        )

        lifecycleScope.launch {
            try {
                AppDatabase.getDatabase(this@AddExpenseActivity).expenseDao().insertExpense(expense)
                runOnUiThread {
                    Toast.makeText(this@AddExpenseActivity, "Expense saved!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Save failed", e)
                runOnUiThread { Toast.makeText(this@AddExpenseActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show() }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
