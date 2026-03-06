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

class ConsolesListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var consolesAdapter: ConsolesAdapter
    private lateinit var buttonAddConsole: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consoles_list)

        recyclerView = findViewById(R.id.recycler_consoles)
        buttonAddConsole = findViewById(R.id.button_add_console)

        // Setup RecyclerView
        consolesAdapter = ConsolesAdapter(
            onItemClick = { console ->
                val intent = Intent(this, ConsoleDetailActivity::class.java)
                intent.putExtra("CONSOLE_ID", console.id)
                startActivity(intent)
            },
            onLongClick = { console ->
                showDeleteDialog(console)
            }
        )
        recyclerView.adapter = consolesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe les consoles
        lifecycleScope.launch {
            AppDatabase.getInstance(this@ConsolesListActivity)
                .consoleDao()
                .getAllConsoles()
                .collectLatest { consoles ->
                    consolesAdapter.submitList(consoles)
                }
        }

        buttonAddConsole.setOnClickListener {
            startActivity(Intent(this, AddConsoleActivity::class.java))
        }
    }

    private fun showDeleteDialog(console: Console) {
        AlertDialog.Builder(this)
            .setTitle("Supprimer ${console.name} ?")
            .setMessage("Cette action est irréversible.")
            .setPositiveButton("Supprimer") { _, _ ->
                lifecycleScope.launch {
                    AppDatabase.getInstance(this@ConsolesListActivity)
                        .consoleDao()
                        .delete(console)
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }
}