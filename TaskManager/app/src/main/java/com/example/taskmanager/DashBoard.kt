package com.example.taskmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashBoard : AppCompatActivity() {

    private lateinit var  floatingButton :FloatingActionButton
    private lateinit var ongoingButton :Button
    private lateinit var completedButton :Button
    private lateinit var overdueButton :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.dash_board)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val receivedText = intent.getStringExtra("enteredText")
        val textView = findViewById<TextView>(R.id.username_display)
        textView.text = receivedText

        // Find the floating action button
        floatingButton= findViewById(R.id.fab_button)
        ongoingButton= findViewById(R.id.ongoing)
        completedButton= findViewById(R.id.completed)
        overdueButton= findViewById(R.id.overdue)

        // Set OnClickListener to the floating action button
        floatingButton.setOnClickListener {
            // Create an intent to open the CreateNewTaskMainActivity
            val intent = Intent(this@DashBoard, CreateNewTaskMainActivity::class.java)
            startActivity(intent)
        }

        ongoingButton.setOnClickListener {
            // Update displayed items for overdue tasks
            // Change button colors
            updateItemsAndButtonColors(ongoingButton)
        }
        completedButton.setOnClickListener{
            updateItemsAndButtonColors(completedButton)
        }
        overdueButton.setOnClickListener{
            updateItemsAndButtonColors(overdueButton)
        }

    }
    private fun updateItemsAndButtonColors(clickedButton: Button) {
        // Reset background color of all buttons
        ongoingButton.setBackgroundColor(getColor(R.color.orange))
        overdueButton.setBackgroundColor(getColor(R.color.orange))
        completedButton.setBackgroundColor(getColor(R.color.orange))

        // Set background color of clicked button
        clickedButton.setBackgroundColor(getColor(R.color.bachelor_blue))

        // Update displayed items based on the clicked button
        when (clickedButton.id) {
            R.id.ongoing -> {
                // Update items for ongoing tasks
            }
            R.id.overdue -> {
                // Update items for overdue tasks
            }
            R.id.completed -> {
                // Update items for completed tasks
            }
        }
    }
}