package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EndingDialogActivity : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private val questions = listOf(
        "So, did you enjoy your short adventure in IT?",
        "Do you want to learn more?",
        "Do you want to commit to an IT program?"
    )
    private val userResponses = mutableListOf<String>()

    private lateinit var questionTextView: TextView
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endingdialog)

        questionTextView = findViewById(R.id.assessment_question_text)
        radioGroup = findViewById(R.id.assessment_radio_group)
        val submitButton: Button = findViewById(R.id.submit_assessment_button)

        displayCurrentQuestion()

        submitButton.setOnClickListener {
            handleSubmission()
        }
    }

    private fun displayCurrentQuestion() {
        questionTextView.text = questions[currentQuestionIndex]
        radioGroup.clearCheck() // Clear selection for the new question
    }

    private fun handleSubmission() {
        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Please make a selection", Toast.LENGTH_SHORT).show()
            return
        }

        val isYes = findViewById<RadioButton>(selectedId).text.toString().equals("YES", ignoreCase = true)
        collectResponse(isYes)

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            displayCurrentQuestion()
        } else {
            finishAssessment()
        }
    }

    private fun collectResponse(isYes: Boolean) {
        val response = when (currentQuestionIndex) {
            0 -> if (isYes) "Good! luck on your journey, " else "Good luck in finding your path."
            1 -> if (isYes) "Knowledge is power, and learning leads to change." else "Become one with yourself, and keep exploring."
            2 -> if (isYes) "Future IT" else ""
            else -> ""
        }
        if (response.isNotEmpty()) {
            userResponses.add(response)
        }
    }

    private fun finishAssessment() {
        // Mark the assessment as complete regardless of the answers.
        val userProfilePrefs = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val nickname = userProfilePrefs.getString("NICKNAME", "")
        val assessmentPrefs = getSharedPreferences("AssessmentStatus", Context.MODE_PRIVATE)
        assessmentPrefs.edit().putBoolean("${nickname}_hasTakenAssessment", true).apply()

        // Combine all the collected responses.
        val combinedMessage = userResponses.joinToString(" ")

        // Navigate to the final screen.
        val intent = Intent(this, YesActivity::class.java).apply {
            putExtra("USER_CHOICE", combinedMessage.trim())
        }
        startActivity(intent)
        finish() // Finish this activity so the user can't come back to it
    }
}
