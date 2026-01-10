package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sample.data.BasicHardwareQuiz
import com.example.sample.data.CssBasicsQuiz
import com.example.sample.data.HtmlBasicsQuiz
import com.example.sample.data.IntroToITQuiz
import com.example.sample.data.IntroductionToProgrammingQuiz
import com.example.sample.data.OperatingSystemsQuiz
import com.example.sample.models.Question
import com.google.android.material.button.MaterialButton

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var questionImageView: ImageView
    private lateinit var previousLevelButton: MaterialButton
    private lateinit var nextLevelButton: MaterialButton
    private lateinit var homeButton: MaterialButton

    private lateinit var questions: List<Question>
    private lateinit var skillName: String
    private var currentQuestionIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        skillName = intent.getStringExtra("SKILL_NAME") ?: ""
        currentQuestionIndex = (intent.getIntExtra("LEVEL", 1) - 1).coerceAtLeast(0)

        initializeViews()
        loadQuestionsForSkill()
        setupNavigationListeners()

        if (questions.isNotEmpty()) {
            displayQuestion()
        } else {
            Toast.makeText(this, "No questions available for this topic.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initializeViews() {
        questionTextView = findViewById(R.id.question_text)
        optionsRadioGroup = findViewById(R.id.options_radio_group)
        questionImageView = findViewById(R.id.question_image)
        previousLevelButton = findViewById(R.id.previous_level_button)
        nextLevelButton = findViewById(R.id.next_level_button)
        homeButton = findViewById(R.id.home_button)
    }

    private fun loadQuestionsForSkill() {
        questions = when (skillName) {
            "Introduction to IT" -> IntroToITQuiz.questions
            "Basic Computer Hardware" -> BasicHardwareQuiz.questions
            "Operating Systems" -> OperatingSystemsQuiz.questions
            "Introduction to Programming" -> IntroductionToProgrammingQuiz.questions
            "HTML Basics" -> HtmlBasicsQuiz.questions
            "CSS Basics" -> CssBasicsQuiz.questions
            else -> emptyList()
        }
    }

    private fun displayQuestion() {
        val question = questions[currentQuestionIndex]
        questionTextView.text = question.text

        val imageRes = question.imageResId
        if (imageRes != null) {
            questionImageView.visibility = View.VISIBLE
            questionImageView.setImageResource(imageRes)
        } else {
            questionImageView.visibility = View.GONE
        }

        optionsRadioGroup.removeAllViews()
        optionsRadioGroup.setOnCheckedChangeListener(null)

        question.options.forEachIndexed { index, option ->
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioButton.id = index
            radioButton.textSize = 18f
            val layoutParams = RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT)
            radioButton.layoutParams = layoutParams
            optionsRadioGroup.addView(radioButton)
        }

        val (savedAnswer, isCorrect) = getSavedAnswer(currentQuestionIndex)
        if (isCorrect) {
            optionsRadioGroup.check(savedAnswer)
            setOptionsEnabled(false)
        } else {
            optionsRadioGroup.clearCheck()
            setOptionsEnabled(true)
            optionsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                handleAnswerSubmission(checkedId)
            }
        }

        previousLevelButton.visibility = if (currentQuestionIndex > 0) View.VISIBLE else View.INVISIBLE
        nextLevelButton.visibility = if (currentQuestionIndex < questions.size - 1) View.VISIBLE else View.INVISIBLE
    }

    private fun setupNavigationListeners() {
        previousLevelButton.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                displayQuestion()
            }
        }

        nextLevelButton.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion()
            }
        }

        homeButton.setOnClickListener {
            finish()
        }
    }

    private fun handleAnswerSubmission(checkedId: Int) {
        val question = questions[currentQuestionIndex]
        val passed = checkedId == question.correctAnswerIndex

        if (passed) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }

        saveAnswer(currentQuestionIndex, passed)
        setOptionsEnabled(false)

        optionsRadioGroup.postDelayed({ moveToNextQuestion() }, 1200)
    }

    private fun moveToNextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            displayQuestion()
        } else {
            handleTopicCompletion()
        }
    }

    private fun handleTopicCompletion() {
        val sharedPref = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE)
        var score = 0
        for (i in questions.indices) {
            if (sharedPref.getBoolean("${skillName}_${i + 1}_passed", false)) {
                score++
            }
        }

        val percentage = (score.toDouble() / questions.size.toDouble()) * 100

        if (percentage >= 80) {
            startActivity(Intent(this, CompleteActivity::class.java))
        } else {
            startActivity(Intent(this, FailedActivity::class.java))
        }
        finish()
    }

    private fun getAnswerKey(index: Int): String = "${skillName}_${index + 1}_passed"

    private fun saveAnswer(index: Int, passed: Boolean) {
        val sharedPref = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean(getAnswerKey(index), passed)
            apply()
        }
    }

    private fun getSavedAnswer(index: Int): Pair<Int, Boolean> {
        val sharedPref = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE) ?: return Pair(-1, false)
        val isCorrect = sharedPref.getBoolean(getAnswerKey(index), false)
        val answer = if (isCorrect) questions[index].correctAnswerIndex else -1
        return Pair(answer, isCorrect)
    }

    private fun setOptionsEnabled(enabled: Boolean) {
        for (i in 0 until optionsRadioGroup.childCount) {
            optionsRadioGroup.getChildAt(i).isEnabled = enabled
        }
    }
}
