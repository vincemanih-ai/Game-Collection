package com.example.myvideogames

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GamesListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var buttonAddGame: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)

        recyclerView = findViewById(R.id.recycler_games)
        buttonAddGame = findViewById(R.id.button_add_game)

        // Setup RecyclerView
        gamesAdapter = GamesAdapter(
            onItemClick = { game ->
                val intent = Intent(this, GameDetailActivity::class.java)
                intent.putExtra("GAME_ID", game.id)
                startActivity(intent)
            },
            onLongClick = { game ->
                showDeleteDialog(game)
            }
        )
        recyclerView.adapter = gamesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe les changements de la base
        lifecycleScope.launch {
            AppDatabase.getInstance(this@GamesListActivity)
                .gameDao()
                .getAllGames()
                .collectLatest { games ->
                    gamesAdapter.submitList(games)
                }
        }

        // Bouton Ajouter
        buttonAddGame.setOnClickListener {
            startActivity(Intent(this, AddGameActivity::class.java))
        }
    }

    private fun showDeleteDialog(game: Game) {
        AlertDialog.Builder(this)
            .setTitle("Supprimer ${game.title} ?")
            .setMessage("Cette action est irréversible.")
            .setPositiveButton("Supprimer") { _, _ ->
                lifecycleScope.launch {
                    AppDatabase.getInstance(this@GamesListActivity)
                        .gameDao()
                        .delete(game)
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }
}