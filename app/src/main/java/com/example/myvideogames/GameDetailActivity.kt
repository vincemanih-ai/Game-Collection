package com.example.myvideogames

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class GameDetailActivity : AppCompatActivity() {

    private lateinit var textTitle: TextView
    private lateinit var textPlatform: TextView
    private lateinit var textGenre: TextView
    private lateinit var textStatus: TextView
    private lateinit var textPlaytime: TextView
    private lateinit var textNote: TextView
    private var currentGame: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)

        textTitle = findViewById(R.id.text_detail_title)
        textPlatform = findViewById(R.id.text_detail_platform)
        textGenre = findViewById(R.id.text_detail_genre)
        textStatus = findViewById(R.id.text_detail_status)
        textPlaytime = findViewById(R.id.text_detail_playtime)
        textNote = findViewById(R.id.text_detail_note)

        val gameId = intent.getLongExtra("GAME_ID", -1L)
        if (gameId != -1L) {
            loadGame(gameId)
        }

        findViewById<Button>(R.id.button_delete_game).setOnClickListener {
            showDeleteDialog()
        }

        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish()
        }
    }

    private fun loadGame(gameId: Long) {
        lifecycleScope.launch {
            val game = AppDatabase.getInstance(this@GameDetailActivity)
                .gameDao()
                .getGameById(gameId)

            currentGame = game
            game?.let {
                textTitle.text = it.title
                textPlatform.text = it.platform ?: "Non renseigné"
                textGenre.text = it.genre ?: "Non renseigné"
                textStatus.text = it.status ?: "Non renseigné"
                textPlaytime.text = it.playtime ?: "Non renseigné"
                textNote.text = it.note ?: "Non renseigné"
            }
        }
    }

    private fun showDeleteDialog() {
        currentGame?.let { game ->
            AlertDialog.Builder(this)
                .setTitle("Supprimer ${game.title} ?")
                .setMessage("Cette action est irréversible.")
                .setPositiveButton("Supprimer") { _, _ ->
                    lifecycleScope.launch {
                        AppDatabase.getInstance(this@GameDetailActivity)
                            .gameDao()
                            .delete(game)
                        finish()
                    }
                }
                .setNegativeButton("Annuler", null)
                .show()
        }
    }
}