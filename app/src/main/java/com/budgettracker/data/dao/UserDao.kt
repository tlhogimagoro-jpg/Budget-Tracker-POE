package com.budgettracker.data.dao

import androidx.room.*
import com.budgettracker.data.entities.User

/**
 * UserDao — Data Access Object for the users table.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users WHERE username = :username AND passwordHash = :passwordHash LIMIT 1")
    suspend fun login(username: String, passwordHash: String): User?

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): User?

    @Query("UPDATE users SET multiplierPoints = multiplierPoints + :points WHERE id = :userId")
    suspend fun incrementMultiplierPoints(userId: Int, points: Int)
    
    @Query("UPDATE users SET lastStreakAwardDate = :date WHERE id = :userId")
    suspend fun updateLastStreakAwardDate(userId: Int, date: Long)
}
