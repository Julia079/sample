package com.example.sample

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.data.SampleSkills

class SummaryActivity : AppCompatActivity() {

    private lateinit var summaryRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        summaryRecyclerView = findViewById(R.id.summary_recycler_view)
        summaryRecyclerView.layoutManager = LinearLayoutManager(this)

        val titleTextView: TextView = findViewById(R.id.summary_text)
        titleTextView.text = getString(R.string.summary)
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshSkillList()
    }

    private fun refreshSkillList() {
        val levelStatusPrefs = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE)
        val startedSkills = SampleSkills.skills.filter { skill ->
            (1..10).any { level ->
                levelStatusPrefs.contains("${skill.name}_${level}_passed")
            }
        }

        val adapter = SummaryAdapter(startedSkills, this)
        summaryRecyclerView.adapter = adapter
    }
}
