package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SkillActivity : AppCompatActivity() {

    private lateinit var levelContainer: LinearLayout
    private val levelStatus = mutableMapOf<Int, Boolean?>()
    private lateinit var skillName: String
    private lateinit var scoreTextView: TextView
    private lateinit var percentageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)

        skillName = intent.getStringExtra("SKILL_NAME") ?: ""
        val skillTitle: TextView = findViewById(R.id.skill_title)
        skillTitle.text = skillName

        levelContainer = findViewById(R.id.level_container)
        scoreTextView = findViewById(R.id.score_text_view)
        percentageTextView = findViewById(R.id.percentage_text_view)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val resetButton: Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener {
            resetCurrentTopicProgress()
        }

        createLevelButtons()
    }

    override fun onResume() {
        super.onResume()
        loadLevelStatus()
        updateLevelButtons()
        updateScoreDisplay()
        updatePercentageDisplay()
        // *** CRITICAL FIX: Check for completion every time the screen is shown ***
        checkTopicCompletion()
    }

    private fun createLevelButtons() {
        levelContainer.removeAllViews()
        for (i in 1..10) {
            val levelButtonView = LayoutInflater.from(this).inflate(R.layout.level_button, levelContainer, false)
            val levelButton: Button = levelButtonView.findViewById(R.id.level_button)
            levelButton.text = "Level $i"

            levelButton.setOnClickListener {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("SKILL_NAME", skillName)
                intent.putExtra("LEVEL", i)
                startActivity(intent)
            }
            levelContainer.addView(levelButtonView)
        }
    }

    private fun updateLevelButtons() {
        for (i in 0 until levelContainer.childCount) {
            val levelButtonView = levelContainer.getChildAt(i)
            val level = i + 1
            val passed = levelStatus[level]

            val levelButton: Button = levelButtonView.findViewById(R.id.level_button)
            val statusIcon: ImageView = levelButtonView.findViewById(R.id.level_status_icon)

            when (passed) {
                true -> {
                    levelButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.green)
                    statusIcon.setImageResource(R.drawable.ic_star)
                    statusIcon.visibility = View.VISIBLE
                }
                false -> {
                    levelButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.red)
                    statusIcon.setImageResource(R.drawable.ic_star_gray)
                    statusIcon.visibility = View.VISIBLE
                }
                null -> {
                    levelButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.celestial_blue)
                    statusIcon.visibility = View.GONE
                }
            }
        }
    }

    private fun updateScoreDisplay() {
        val score = levelStatus.values.count { it == true }
        scoreTextView.text = "Score: $score / 10"
    }

    private fun updatePercentageDisplay() {
        val score = levelStatus.values.count { it == true }
        val percentage = (score.toDouble() / 10.0) * 100
        percentageTextView.text = "(${percentage.toInt()}%)"

        if (percentage >= 80) {
            percentageTextView.setTextColor(ContextCompat.getColorStateList(this, R.color.green))
        } else {
            percentageTextView.setTextColor(ContextCompat.getColorStateList(this, R.color.red))
        }
    }

    private fun checkTopicCompletion() {
        val topicStatusPrefs = getSharedPreferences("TopicStatus", Context.MODE_PRIVATE)
        val hasBeenShown = topicStatusPrefs.getBoolean(skillName, false)

        if (!hasBeenShown) {
            val answeredCount = levelStatus.values.count { it != null }
            if (answeredCount == 10) {
                val score = levelStatus.values.count { it == true }
                val percentage = (score.toDouble() / 10.0) * 100

                if (percentage >= 80) {
                    startActivity(Intent(this, CompleteActivity::class.java))
                } else {
                    startActivity(Intent(this, FailedActivity::class.java))
                }

                with(topicStatusPrefs.edit()) {
                    putBoolean(skillName, true)
                    commit()
                }
            }
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

        recreate()
    }

    private fun loadLevelStatus() {
        val sharedPref = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE) ?: return
        levelStatus.clear()
        for (i in 1..10) {
            val key = "${skillName}_${i}_passed"
            if (sharedPref.contains(key)) {
                levelStatus[i] = sharedPref.getBoolean(key, false)
            } else {
                levelStatus[i] = null
            }
        }
    }
}
