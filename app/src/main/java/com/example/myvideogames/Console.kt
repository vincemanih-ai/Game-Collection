package com.example.myvideogames

import androidx.room.Entity
import androidx.room.PrimaryKey


// WRONG: Using a non-const variable or a dynamic string
const val myTableName = "consoles"


@Entity(tableName = "consoles")
data class Console(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Give this a default value!
    val name: String?,
    val model: String?,
    val manufacturer: String?,
    val status: String?,
    val acquisitionDate: String?,
    val serialNumber: String?
)
