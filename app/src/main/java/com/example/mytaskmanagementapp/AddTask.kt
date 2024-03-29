package com.example.mytaskmanagementapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task)

        val taskNameEditText: EditText = findViewById(R.id.taskNameEditText)
        val startDateEditText: EditText = findViewById(R.id.startDateEditText)
        val endDateEditText: EditText = findViewById(R.id.endDateEditText)
        val addButton: Button = findViewById(R.id.saveButton)

        addButton.setOnClickListener {
            val taskName = taskNameEditText.text.toString()
            val startDate = startDateEditText.text.toString()
            val endDate = endDateEditText.text.toString()

            // Save the task details to a database or data structure

            // You can also pass the task details back to the HomeActivity to display the new task
            // For example, you can use Intent.putExtra() to pass the task details back
        }
    }
}