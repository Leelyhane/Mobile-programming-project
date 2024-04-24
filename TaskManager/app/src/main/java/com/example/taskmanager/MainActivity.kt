package com.example.taskmanager

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.*
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var captureUsernameButton: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("registeredTaskManagerUserName", Context.MODE_PRIVATE)
        val currentRegisteredUserName: String? = sharedPreferences.getString("registeredTaskManagerUserName", null)

        // Check if user has already registered
        if (!currentRegisteredUserName.isNullOrEmpty()) {
            // Proceed to dashboard directly
            val intent = Intent(this@MainActivity, DashBoard::class.java)
            intent.putExtra("enteredText", currentRegisteredUserName)
            startActivity(intent)
            finish()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        username = findViewById(R.id.username)
        captureUsernameButton = findViewById(R.id.captureUsernameButton)

        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                captureUsernameButton.isEnabled = !s.isNullOrBlank()
            }
        })

        captureUsernameButton.setOnClickListener {
            val enteredText = username.text.toString()
            val userName: String = username.text.toString()

            reference = database.reference.child("users").push() // Create a new node under "users"

            reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Generate a new user ID
                    val userId = snapshot.childrenCount.toInt() + 1


                    // Store user data under the generated user ID
                    //reference.child("username").setValue(userName)
                    //reference.child("user_id").setValue(userId)
                    val newUserReference = reference.child("user_$userId")
                    newUserReference.child("username").setValue(userName)
                    newUserReference.child("user_id").setValue(userId)

                    // Store the registered username in SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("registeredTaskManagerUserName", userName)
                    editor.putString("registeredTaskManagerUserId", userId.toString())
                    editor.apply()


                    // Proceed to dashboard
                    val intent = Intent(this@MainActivity, DashBoard::class.java)
                    intent.putExtra("enteredText", userName)
                    startActivity(intent)
                    finish()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle onCancelled
                }
            })
        }
    }
}
