package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var inputTextView: TextView
    private lateinit var outputTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTextView = findViewById(R.id.input)
        outputTextView = findViewById(R.id.output)

        // Set click listeners for buttons
        findViewById<View>(R.id.AddButton).setOnClickListener { appendCharacter('+') }
        findViewById<View>(R.id.SubtractionButton).setOnClickListener { appendCharacter('-') }
        findViewById<View>(R.id.MultiplicationButton).setOnClickListener { appendCharacter('*') }
        findViewById<View>(R.id.DivisionButton).setOnClickListener { appendCharacter('/') }
        findViewById<View>(R.id.button_C).setOnClickListener { clearFields() }
        findViewById<View>(R.id.button_bracket_left).setOnClickListener { appendCharacter('(') }
        findViewById<View>(R.id.button_bracket_right).setOnClickListener { appendCharacter(')') }
        findViewById<View>(R.id.button_dot).setOnClickListener { appendCharacter('.') }
        findViewById<View>(R.id.button_equals).setOnClickListener { calculateResult() }

        // Set click listeners for numeric buttons
        val numericButtonIds = listOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9
        )
        numericButtonIds.forEach { buttonId ->
            findViewById<View>(buttonId).setOnClickListener {
                appendCharacter((it as TextView).text[0])
            }
        }
    }

    private fun appendCharacter(char: Char) {
        inputTextView.append(char.toString())
    }

    private fun clearFields() {
        inputTextView.text = ""
        outputTextView.text = ""
    }

    private fun calculateResult() {
        val expression = inputTextView.text.toString()

        // Attempt to evaluate the expression
        try {
            val result = ExpressionBuilder(expression).build().evaluate()
            outputTextView.text = result.toString()
        } catch (e: Exception) {
            // If an exception occurs during evaluation, show an error message
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
