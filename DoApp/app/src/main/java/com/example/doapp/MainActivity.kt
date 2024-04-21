package com.example.doapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), TaskListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.RVTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter with an empty list for now
        adapter = TaskAdapter(emptyList(), this) // Pass `this` as TaskListener
        recyclerView.adapter = adapter

        // Initialize the add button
        val addButton: ImageButton = findViewById(R.id.AddButton)

        // Set OnClickListener for the Add Button
        addButton.setOnClickListener {
            // Navigate to AddTask activity when the button is clicked
            startActivityForResult(Intent(this@MainActivity, AddTask::class.java), ADD_TASK_REQUEST_CODE)
        }

        // Fetch tasks and update the adapter
        updateTaskList()
    }

    override fun onTaskCompleted(task: Task) {
        // Handle task completion here
        // For example, you can update the `isCompleted` property of the task
        // and refresh the RecyclerView to reflect the changes
        task.isCompleted = true
        adapter.notifyDataSetChanged()
    }

    private fun updateTaskList() {
        // Log statement to indicate that the function is being called
        Log.d("MainActivity", "updateTaskList() called")

        // Retrieve tasks from TaskManager
        val tasks = TaskManager.getTasks()

        // Log the number of tasks retrieved
        Log.d("MainActivity", "Number of tasks retrieved: ${tasks.size}")

        // Update the adapter with the new list of tasks
        adapter.updateTasks(tasks)

        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Refresh the task list when a new task is added
            updateTaskList()
        }
    }

    companion object {
        private const val ADD_TASK_REQUEST_CODE = 1
    }
}
