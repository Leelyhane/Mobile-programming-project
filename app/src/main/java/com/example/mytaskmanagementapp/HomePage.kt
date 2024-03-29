package com.example.mytaskmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.ImageButton

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        // Find the button in the layout
        val addButton: ImageButton = findViewById(R.id.addButton)

        // Set click listener for the button
        addButton.setOnClickListener {
            // Launch the AddTaskActivity when the button is clicked
            startActivity(Intent(this, AddTask::class.java))
        }
    }
}