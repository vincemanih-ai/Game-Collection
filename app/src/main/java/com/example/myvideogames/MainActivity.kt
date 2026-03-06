
package com.example.myvideogames

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppDatabase.populateIfEmpty(this)
        AppDatabase.populateConsoleIfEmpty(this)

        findViewById<Button>(R.id.button_games_list).setOnClickListener {
            startActivity(Intent(this, GamesListActivity::class.java))
        }
        findViewById<Button>(R.id.button_consoles_list).setOnClickListener {
            startActivity(Intent(this, ConsolesListActivity::class.java))
        }
    }
}