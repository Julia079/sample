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
    private val levelStatus = mutableMapOf<Int, Boolean>()
    private lateinit var skillName: String
    private lateinit var scoreTextView: TextView // The new TextView for the score

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)

        skillName = intent.getStringExtra("SKILL_NAME") ?: ""
        val skillTitle: TextView = findViewById(R.id.skill_title)
        skillTitle.text = skillName

        levelContainer = findViewById(R.id.level_container)
        scoreTextView = findViewById(R.id.score_text_view) // Find the TextView

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        createLevelButtons()
    }

    override fun onResume() {
        super.onResume()
        // Always reload status and update UI when the screen is shown
        loadLevelStatus()
        updateLevelButtons()
        updateScoreDisplay() // Update the score display
    }

    private fun createLevelButtons() {
        levelContainer.removeAllViews()
        for (i in 1..10) {
            val levelButtonView = LayoutInflater.from(this).inflate(R.layout.level_button, levelContainer, false)
            val levelButton: Button = levelButtonView.findViewById(R.id.level_button)
            levelButton.text = "Level $i"
            levelButton.setOnClickListener {
                if (levelStatus[i] != true) {
                    val intent = Intent(this, QuizActivity::class.java)
                    intent.putExtra("SKILL_NAME", skillName)
                    intent.putExtra("LEVEL", i)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Level already passed!", Toast.LENGTH_SHORT).show()
                }
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
                    statusIcon.visibility = View.GONE
                }
            }
        }
    }

    // *** NEW FUNCTION TO UPDATE THE SCORE DISPLAY ***
    private fun updateScoreDisplay() {
        val score = levelStatus.values.count { it }
        scoreTextView.text = "Score: $score / 10"
    }

    private fun loadLevelStatus() {
        val sharedPref = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE) ?: return
        levelStatus.clear()
        for (i in 1..10) {
            val key = "${skillName}_${i}_passed"
            if (sharedPref.contains(key)) {
                levelStatus[i] = sharedPref.getBoolean(key, false)
            }
        }
    }
}
