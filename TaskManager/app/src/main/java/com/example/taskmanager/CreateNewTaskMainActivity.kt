package com.example.taskmanager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CreateNewTaskMainActivity : AppCompatActivity(), SubtaskDialogFragment.SubtaskListener {

    private lateinit var spinnerPriority: Spinner
    private lateinit var addSubtaskButton: Button
    private lateinit var subtaskContainer: LinearLayout
    private lateinit var noSubtaskTextView: TextView
    private lateinit var noUploadedFileTextView: TextView
    private val FILE_PICKER_REQUEST_CODE = 1
    private var subtaskCount = 0
    private var fileCounter = 1 // Variable to keep track of the file number


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.create_new_task_activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerPriority = findViewById(R.id.spinner_priority)
        addSubtaskButton = findViewById(R.id.add_subtask_button)
        subtaskContainer = findViewById(R.id.subtaskContainer)
        noSubtaskTextView = findViewById(R.id.no_subtasks_textview)
        noUploadedFileTextView = findViewById(R.id.no_uploaded_file_textview)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.priorities,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPriority.adapter = adapter

        addSubtaskButton.setOnClickListener {
            val dialogFragment = SubtaskDialogFragment()
            dialogFragment.subtaskListener = this
            dialogFragment.show(supportFragmentManager, "SubtaskDialogFragment")
        }
    }

    override fun onSubtaskAdded(subtaskName: String) {
        Log.d("SubtaskAdded", "Subtask added: $subtaskName")
        subtaskCount++

        // Hide the "No subtasks yet" text view
        noSubtaskTextView.visibility = View.GONE

        // Create a new TextView to display the subtask name with number
        val textView = TextView(this)
        textView.text = "$subtaskCount. $subtaskName"
        textView.setTextColor(Color.BLACK)
        textView.setTextSize(resources.getDimension(R.dimen.subtask_text_size) / resources.displayMetrics.density)
        textView.gravity = Gravity.CENTER_VERTICAL

        // Create layout params with WRAP_CONTENT for width and height
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        // Set margins to create space between subtasks and align them to the left
        layoutParams.gravity = Gravity.START or Gravity.CENTER_VERTICAL
        layoutParams.setMargins(0, 0, 0, resources.getDimensionPixelSize(R.dimen.subtask_margin_bottom))
        textView.layoutParams = layoutParams

        // Add the TextView to the LinearLayout
        subtaskContainer.addView(textView)
    }


    //This function handles behaviour when the upload file button is clicked
    fun onUploadFileButtonClick(view: View) {


        // Hide the "No subtasks yet" text view
        noSubtaskTextView.visibility = View.GONE
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*" // Allow any file type to be selected
        }
        startActivityForResult(intent, FILE_PICKER_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {



        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Handle the selected file here
            val selectedFileUri = data?.data
            // Retrieve the selected file's display name
            val selectedFileName = selectedFileUri?.let { uri ->
                contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    cursor.moveToFirst()
                    cursor.getString(nameIndex)
                }
            }

            // Increment fileCounter for each uploaded file
            val fileNumber = fileCounter++

            // Hide the "No subtasks yet" text view
            noUploadedFileTextView.visibility = View.GONE

            // Create a new TextView to display the selected file information
            selectedFileName?.let { fileName ->

                // Append the file number to the file name
                val displayFileName = "$fileNumber. $fileName"
                val textView = TextView(this).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    text = displayFileName
                    textSize = 16f // Adjust text size as needed
                    setTextColor(Color.BLACK)
                    gravity = Gravity.START
                }
                // Add the TextView to your layout
                findViewById<LinearLayout>(R.id.file_container_layout).addView(textView)
            }
        }
    }


}
