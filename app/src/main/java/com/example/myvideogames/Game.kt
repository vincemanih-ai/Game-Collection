package com.example.myvideogames

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,           // Titre obligatoire
    val platform: String? = null, // Plateforme
    val genre: String? = null,    // Genre (manquant !)
    val status: String? = null,   // Statut (Terminé, En cours...)
    val playtime: String? = null, // Temps joué texte
    val note: String? = null      // Note texte "9.5/10"
)