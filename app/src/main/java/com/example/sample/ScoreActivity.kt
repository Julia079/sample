package com.example.sample

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ScoreActivity : AppCompatActivity() {

    private lateinit var skillName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        skillName = intent.getStringExtra("SKILL_NAME") ?: ""
        val score = intent.getIntExtra("SCORE", 0)
        val percentage = intent.getIntExtra("PERCENTAGE", 0)

        val titleTextView: TextView = findViewById(R.id.score_popup_title)
        val scoreTextView: TextView = findViewById(R.id.score_popup_score)
        val percentageTextView: TextView = findViewById(R.id.score_popup_percentage)
        val levelTitleTextView: TextView = findViewById(R.id.knowledge_level_title)
        val levelDescTextView: TextView = findViewById(R.id.knowledge_level_description)
        val levelKnowledge: TextView = findViewById(R.id.text_know)
        val closeButton: ImageView = findViewById(R.id.score_popup_close_button)

        titleTextView.text = skillName
        scoreTextView.text = "Score: $score / 10"
        percentageTextView.text = "($percentage%)"

        // *** CRITICAL FIX: Apply color based on percentage ***
        val colorRes = if (percentage >= 80) R.color.green else R.color.red
        scoreTextView.setTextColor(ContextCompat.getColor(this, colorRes))
        percentageTextView.setTextColor(ContextCompat.getColor(this, colorRes))

        val (level, description) = getKnowledgeLevel(percentage)
        levelTitleTextView.text = level
        levelDescTextView.text = description
        levelKnowledge.text = "Knowledge Level: "

        val resetButton: Button = findViewById(R.id.score_popup_reset_button)
        resetButton.setOnClickListener {
            resetCurrentTopicProgress()
            setResult(RESULT_OK)
            finish()
        }

        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun getKnowledgeLevel(percentage: Int): Pair<String, String> {
        return when {
            percentage <= 49 -> "Smooth Brain" to "no lumps just smooth."
            percentage <= 69 -> "Brain Rot" to "lumpier than smooth brain."
            percentage <= 79 -> "Chicken Head" to "Just a little bit further."
            percentage <= 89 -> "Average Joe" to "well, well, well welcome Joe !"
            percentage <= 99 -> "Super Brain" to "I eat books in the morning."
            else -> "Megamind" to "I don't seek knowledge, knowledge seek me."
        }
    }

    private fun resetCurrentTopicProgress() {
        val levelStatusPrefs = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE).edit()
        val topicStatusPrefs = getSharedPreferences("TopicStatus", Context.MODE_PRIVATE).edit()
        val wrongAnswersPrefs = getSharedPreferences("WrongAnswers", Context.MODE_PRIVATE).edit()
        val attemptCounterPrefs = getSharedPreferences("AttemptCounter", Context.MODE_PRIVATE).edit()

        for (i in 1..10) {
            levelStatusPrefs.remove("${skillName}_${i}_passed")
            wrongAnswersPrefs.remove("${skillName}_${i}_wrong_answers")
            attemptCounterPrefs.remove("${skillName}_${i}_attempts")
        }
        
        topicStatusPrefs.remove(skillName)

        levelStatusPrefs.apply()
        topicStatusPrefs.apply()
        wrongAnswersPrefs.apply()
        attemptCounterPrefs.apply()

        Toast.makeText(this, "Progress for '$skillName' has been reset.", Toast.LENGTH_SHORT).show()
    }
}
