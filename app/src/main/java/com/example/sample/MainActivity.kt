package com.example.sample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.data.SampleSkills

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val skillTreeRecyclerView: RecyclerView = findViewById(R.id.skill_tree_recycler_view)
        skillTreeRecyclerView.layoutManager = LinearLayoutManager(this)
        val titleTextView: TextView = findViewById(R.id.topic_text)
        titleTextView.text = getString(R.string.topic)

        val resetButton: Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener {
            getSharedPreferences("LevelStatus", Context.MODE_PRIVATE).edit().clear().apply()
            getSharedPreferences("TopicStatus", Context.MODE_PRIVATE).edit().clear().apply()
            getSharedPreferences("WrongAnswers", Context.MODE_PRIVATE).edit().clear().apply()
            getSharedPreferences("AttemptCounter", Context.MODE_PRIVATE).edit().clear().apply()
            getSharedPreferences("TopicUnlockStatus", Context.MODE_PRIVATE).edit().clear().apply()

            Toast.makeText(this, "All progress has been reset.", Toast.LENGTH_SHORT).show()

            recreate()
        }

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateTopicUnlockStatus() {
        val unlockPrefs = getSharedPreferences("TopicUnlockStatus", Context.MODE_PRIVATE)
        val levelStatusPrefs = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE)
        val editor = unlockPrefs.edit()
        var changed = false

        for (i in 1 until SampleSkills.skills.size) {
            val currentSkill = SampleSkills.skills[i]
            val previousSkill = SampleSkills.skills[i - 1]

            if (!unlockPrefs.getBoolean(currentSkill.name, false)) {
                if (isTopicPassed(levelStatusPrefs, previousSkill.name)) {
                    editor.putBoolean(currentSkill.name, true)
                    changed = true
                }
            }
        }

        if (changed) {
            editor.apply()
        }
    }

    private fun isTopicPassed(prefs: SharedPreferences, topicName: String): Boolean {
        var passedLevels = 0
        for (i in 1..10) {
            if (prefs.getBoolean("${topicName}_${i}_passed", false)) {
                passedLevels++
            }
        }
        val score = (passedLevels.toDouble() / 10.0) * 100
        return score >= 80
    }

    override fun onResume() {
        super.onResume()
        updateTopicUnlockStatus()
        val skillTreeRecyclerView: RecyclerView = findViewById(R.id.skill_tree_recycler_view)
        skillTreeRecyclerView.adapter = SkillAdapter(SampleSkills.skills, this)
    }
}
