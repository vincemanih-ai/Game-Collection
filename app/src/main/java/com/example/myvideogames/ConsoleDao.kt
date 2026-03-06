package com.example.myvideogames

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ConsoleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(console: Console)

    @Delete
    suspend fun delete(console: Console)

    @Query("SELECT * FROM consoles ORDER BY name ASC")
    fun getAllConsoles(): Flow<List<Console>>

    @Query("SELECT * FROM consoles WHERE id = :consoleId")
    suspend fun getConsoleById(consoleId: Long): Console?

    @Query("DELETE FROM consoles")
    suspend fun clearAll()
}