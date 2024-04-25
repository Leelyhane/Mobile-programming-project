package com.example.doapp

<<<<<<< HEAD
=======

>>>>>>> 5626de4f92d5bda0ef973c4fdabafa64c1949396
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_details)

        // Extract task details from intent
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

<<<<<<< HEAD
           }
=======
        // Button to navigate to AddSubtaskActivity
        val addSubtaskButton: Button = findViewById(R.id.addSubtaskButton)
        addSubtaskButton.setOnClickListener {
            val intent = Intent(this@TaskDetails, AddSubtaskActivity::class.java)
            startActivity(intent)
        }
    }
>>>>>>> 5626de4f92d5bda0ef973c4fdabafa64c1949396
}

//package com.example.doapp
//
//import android.app.Activity
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class TaskDetails : AppCompatActivity() {
//
//    private val PICK_FILE_REQUEST_CODE = 123 // Define your request code
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.task_details)
//
//        val taskName = intent.getStringExtra("taskName")
//        val startDate = intent.getStringExtra("startDate")
//        val endDate = intent.getStringExtra("endDate")
//
//        // Display task information in the layout
//        val taskNameTextView: TextView = findViewById(R.id.taskNameTextView)
//        val startDateTextView: TextView = findViewById(R.id.startDateTextView)
//        val endDateTextView: TextView = findViewById(R.id.endDateTextView)
//
//        taskNameTextView.text = "Task Name: $taskName"
//        startDateTextView.text = "Start Date: $startDate"
//        endDateTextView.text = "End Date: $endDate"
//
//        val attachFileButton = findViewById<Button>(R.id.attachFile)
//
//        attachFileButton.setOnClickListener {
//            // Create an intent to open the file picker
//            val pickFileIntent = Intent(Intent.ACTION_GET_CONTENT)
//            pickFileIntent.type = "*/*"
//            startActivityForResult(pickFileIntent, PICK_FILE_REQUEST_CODE)
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            // Check if data is not null and that it contains a URI
//            val selectedFileUri: Uri? = data?.data
//            if (selectedFileUri != null) {
//                // you can work with the selected file URI
//                //display the file name or handle the file
//                val fileNameTextView: TextView = findViewById(R.id.fileNameTextView)
//                fileNameTextView.text = "Selected File: ${selectedFileUri.path}"
//            }
//        }
//    }
//}

