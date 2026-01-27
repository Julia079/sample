package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.data.BasicHardwareQuiz
import com.example.sample.data.CssBasicsQuiz
import com.example.sample.data.HtmlBasicsQuiz
import com.example.sample.data.IntroToITQuiz
import com.example.sample.data.IntroductionToProgrammingQuiz
import com.example.sample.data.OperatingSystemsQuiz
import com.example.sample.models.Question

class TopicSummaryActivity : AppCompatActivity() {

    private lateinit var skillName: String
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_summary)

        skillName = intent.getStringExtra("SKILL_NAME") ?: ""
        val titleTextView: TextView = findViewById(R.id.topic_summary_title)
        titleTextView.text = skillName

        recyclerView = findViewById(R.id.topic_summary_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val showScoreButton: Button = findViewById(R.id.show_score_button)
        showScoreButton.setOnClickListener {
            showScore()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshAnsweredQuestions()
    }

    private fun refreshAnsweredQuestions() {
        val allQuestions = getQuestionsForSkill(skillName)

        val answeredQuestions = allQuestions.mapIndexedNotNull { index, question ->
            val levelStatusPrefs = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE)
            val passedKey = "${skillName}_${index + 1}_passed"
            if (levelStatusPrefs.contains(passedKey)) {
                Pair(index, question)
            } else {
                null
            }
        }

        recyclerView.adapter = TopicSummaryAdapter(this, answeredQuestions, skillName)
    }

    private fun getQuestionsForSkill(skillName: String): List<Question> {
        return when (skillName) {
            "Introduction to IT" -> IntroToITQuiz.questions
            "Basic Computer Hardware" -> BasicHardwareQuiz.questions
            "Operating Systems" -> OperatingSystemsQuiz.questions
            "Introduction to Programming" -> IntroductionToProgrammingQuiz.questions
            "HTML Basics" -> HtmlBasicsQuiz.questions
            "CSS Basics" -> CssBasicsQuiz.questions
            else -> emptyList()
        }
    }

    private fun showScore() {
        val score = calculateScore()
        val percentage = (score.toDouble() / 10.0 * 100).toInt()

        val intent = Intent(this, ScoreActivity::class.java).apply {
            putExtra("SKILL_NAME", skillName)
            putExtra("SCORE", score)
            putExtra("PERCENTAGE", percentage)
            putExtra("SOURCE", "TopicSummaryActivity")
        }
        startActivity(intent)
    }

    private fun calculateScore(): Int {
        val sharedPref = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE)
        var score = 0
        for (i in 1..10) {
            val key = "${skillName}_${i}_passed"
            if (sharedPref.getBoolean(key, false)) {
                score++
            }
        }
        return score
    }
}
