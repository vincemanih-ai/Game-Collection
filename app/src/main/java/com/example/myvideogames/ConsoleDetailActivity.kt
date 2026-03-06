package com.example.myvideogames

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ConsoleDetailActivity : AppCompatActivity() {

    private lateinit var textName: TextView
    private lateinit var textModel: TextView
    private lateinit var textManufacturer: TextView
    private lateinit var textStatus: TextView
    private lateinit var textAcquisitionDate: TextView
    private lateinit var textSerialNumber: TextView
    private var currentConsole: Console? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_console_detail)

        textName = findViewById(R.id.text_console_detail_name)
        textModel = findViewById(R.id.text_console_detail_model)
        textManufacturer = findViewById(R.id.text_console_detail_manufacturer)
        textStatus = findViewById(R.id.text_console_detail_status)
        textAcquisitionDate = findViewById(R.id.text_console_detail_date)
        textSerialNumber = findViewById(R.id.text_console_detail_serial)

        val consoleId = intent.getLongExtra("CONSOLE_ID", -1L)
        if (consoleId != -1L) {
            loadConsole(consoleId)
        }

        findViewById<Button>(R.id.button_delete_console).setOnClickListener {
            showDeleteDialog()
        }
        findViewById<Button>(R.id.button_back_console).setOnClickListener {
            finish()
        }
    }

    private fun loadConsole(consoleId: Long) {
        lifecycleScope.launch {
            val console = AppDatabase.getInstance(this@ConsoleDetailActivity)
                .consoleDao()
                .getConsoleById(consoleId)
            currentConsole = console
            console?.let {
                textName.text = it.name
                textModel.text = it.model ?: "Non renseigné"
                textManufacturer.text = it.manufacturer ?: "Non renseigné"
                textStatus.text = it.status ?: "Non renseigné"
                textAcquisitionDate.text = it.acquisitionDate ?: "Non renseigné"
                textSerialNumber.text = it.serialNumber ?: "Non renseigné"
            }
        }
    }

    private fun showDeleteDialog() {
        currentConsole?.let { console ->
            AlertDialog.Builder(this)
                .setTitle("Supprimer ${console.name} ?")
                .setPositiveButton("Supprimer") { _, _ ->
                    lifecycleScope.launch {
                        AppDatabase.getInstance(this@ConsoleDetailActivity)
                            .consoleDao()
                            .delete(console)
                        finish()
                    }
                }
                .setNegativeButton("Annuler", null)
                .show()
        }
    }
}