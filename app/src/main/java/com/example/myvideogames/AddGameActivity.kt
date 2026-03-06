package com.example.myvideogames

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddGameActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editPlatform: EditText
    private lateinit var editGenre: EditText
    private lateinit var editStatus: EditText
    private lateinit var editPlaytime: EditText
    private lateinit var editNote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)

        editTitle = findViewById(R.id.edit_game_title)
        editPlatform = findViewById(R.id.edit_game_platform)
        editGenre = findViewById(R.id.edit_game_genre)
        editStatus = findViewById(R.id.edit_game_status)
        editPlaytime = findViewById(R.id.edit_game_playtime)
        editNote = findViewById(R.id.edit_game_note)

        findViewById<Button>(R.id.button_save_game).setOnClickListener {
            saveGame()
        }

        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            finish()
        }
    }

    private fun saveGame() {
        val title = editTitle.text.toString().trim()
        if (title.isEmpty()) {
            Toast.makeText(this, "Le titre est obligatoire", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val game = Game(
                title = title,
                platform = editPlatform.text.toString().trim(),
                genre = editGenre.text.toString().trim(),
                status = editStatus.text.toString().trim(),
                playtime = editPlaytime.text.toString().trim(),
                note = editNote.text.toString().trim()
            )
            AppDatabase.getInstance(this@AddGameActivity).gameDao().insert(game)
            Toast.makeText(this@AddGameActivity, "Jeu ajouté !", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun Unit.insert(game: Game) {
        TODO("Not yet implemented")
    }

    private fun Unit.gameDao() {
        TODO("Not yet implemented")
    }

    private fun AppDatabase.Companion.getInstance(addGameActivity: AddGameActivity) {
        TODO("Not yet implemented")
    }
}