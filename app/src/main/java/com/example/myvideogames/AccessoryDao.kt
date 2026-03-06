package com.example.myvideogames

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccessoryDao {

    @Query("SELECT * FROM accessories WHERE consoleId = :consoleId")
    suspend fun getAccessoriesForConsole(consoleId: Long): List<Accessory>

    @Insert
    suspend fun insertAccessory(accessory: Accessory)

    @Update
    suspend fun updateAccessory(accessory: Accessory)

    @Delete
    suspend fun deleteAccessory(accessory: Accessory)
}