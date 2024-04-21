package com.example.doapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskDetails : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_details)

        val taskName = intent.getStringExtra("taskName")
        val startDate = intent.getStringExtra("startDate")
        val endDate = intent.getStringExtra("endDate")

        // Display task information in the layout
        val taskNameTextView: TextView = findViewById(R.id.taskNameTextView)
        val startDateTextView: TextView = findViewById(R.id.startDateTextView)
        val endDateTextView: TextView = findViewById(R.id.endDateTextView)

        taskNameTextView.text = "Task Name: $taskName"
        startDateTextView.text = "Start Date: $startDate"
        endDateTextView.text = "End Date: $endDate"
    }
}