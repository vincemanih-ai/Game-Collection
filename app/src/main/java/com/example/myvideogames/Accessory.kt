package com.example.myvideogames

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "accessories",
    foreignKeys = [
        ForeignKey(
            entity = Console::class,
            parentColumns = ["id"],
            childColumns = ["consoleId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("consoleId")]
)
data class Accessory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val consoleId: Long,
    val type: String,
    val description: String,
    val quantity: Int
)