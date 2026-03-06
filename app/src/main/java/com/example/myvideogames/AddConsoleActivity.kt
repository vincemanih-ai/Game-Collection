package com.example.myvideogames

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddConsoleActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editModel: EditText
    private lateinit var editManufacturer: EditText
    private lateinit var editStatus: EditText
    private lateinit var editDate: EditText
    private lateinit var editSerial: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_console)

        editName = findViewById(R.id.edit_console_name)
        editModel = findViewById(R.id.edit_console_model)
        editManufacturer = findViewById(R.id.edit_console_manufacturer)
        editStatus = findViewById(R.id.edit_console_status)
        editDate = findViewById(R.id.edit_console_date)
        editSerial = findViewById(R.id.edit_console_serial)

        findViewById<Button>(R.id.button_save_console).setOnClickListener { saveConsole() }
        findViewById<Button>(R.id.button_cancel_console).setOnClickListener { finish() }
    }

    private fun saveConsole() {
        val name = editName.text.toString().trim()
        if (name.isEmpty()) {
            Toast.makeText(this, "Le nom est obligatoire", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val console = Console(
                name = editName.text.toString().trim().ifEmpty { null },
                model = editModel.text.toString().trim().ifEmpty { null },
                manufacturer = editManufacturer.text.toString().trim().ifEmpty { null },
                status = editStatus.text.toString().trim().ifEmpty { null },
                acquisitionDate = editDate.text.toString().trim().ifEmpty { null },
                serialNumber = editSerial.text.toString().trim().ifEmpty { null }
            )
            AppDatabase.getInstance(this@AddConsoleActivity).consoleDao().insert(console)
            Toast.makeText(this@AddConsoleActivity, "Console ajoutée !", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}