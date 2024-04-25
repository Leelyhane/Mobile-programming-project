package com.example.doapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddSubtaskActivity : AppCompatActivity() {

    private lateinit var addSubTaskNameEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_subtask)

        // Initialize views
        addSubTaskNameEditText = findViewById(R.id.addSubTaskName)
        saveButton = findViewById(R.id.saveButton)

        // Set click listener for the save button
        saveButton.setOnClickListener {
            // Get the subtask name from EditText
            val subtaskName = addSubTaskNameEditText.text.toString()

            // Save the subtask using the subtask name
            saveSubtask(subtaskName)
        }
    }

    private fun saveSubtask(subtaskName: String) {
        // Add your logic here to save the subtask
        // For example, you can send the subtaskName to a database or store it locally
        // After saving, you may want to navigate back to the previous activity or perform other actions
    }
}
