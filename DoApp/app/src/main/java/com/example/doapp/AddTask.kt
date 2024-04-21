package com.example.doapp


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddTask : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task)

        // Find views by their IDs
        val taskName: EditText = findViewById(R.id.Task)
        val startDate: EditText = findViewById(R.id.startdate)
        val endDate: EditText = findViewById(R.id.enddate)
        val saveButton: Button = findViewById(R.id.SaveButton)

        // Set OnClickListener for the Save Button
        saveButton.setOnClickListener {
            val taskNameText = taskName.text.toString().trim()
            val startDateText = startDate.text.toString().trim()
            val endDateText = endDate.text.toString().trim()
            if (taskNameText.isNotEmpty() && startDateText.isNotEmpty() && endDateText.isNotEmpty()) {
                // Call a function to create the task and add it to the TaskManager
                val task = Task(taskNameText, startDateText, endDateText)
                TaskManager.addTask(task)

                // Clear the EditTexts after creating the task
                taskName.text.clear()
                startDate.text.clear()
                endDate.text.clear()

                // Return to MainActivity and pass the result
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                // Show an error message if any field is empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
