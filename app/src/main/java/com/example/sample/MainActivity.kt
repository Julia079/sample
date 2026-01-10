package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.data.SampleSkills

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // *** CRITICAL FIX: The entire enableEdgeToEdge feature and its listener have been removed to fix the blank screen bug. ***
        setContentView(R.layout.activity_main)

        val skillTreeRecyclerView: RecyclerView = findViewById(R.id.skill_tree_recycler_view)
        skillTreeRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SkillAdapter(SampleSkills.skills) { skill ->
            val intent = Intent(this, SkillActivity::class.java)
            intent.putExtra("SKILL_NAME", skill.name)
            startActivity(intent)
        }
        skillTreeRecyclerView.adapter = adapter

        val resetButton: Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener {
            // Clear all saved data files synchronously to prevent race conditions
            getSharedPreferences("QuizProgress", Context.MODE_PRIVATE).edit().clear().commit()
            getSharedPreferences("LevelStatus", Context.MODE_PRIVATE).edit().clear().commit()
            getSharedPreferences("CorrectAnswers", Context.MODE_PRIVATE).edit().clear().commit()
            getSharedPreferences("TopicStatus", Context.MODE_PRIVATE).edit().clear().commit()

            Toast.makeText(this, "All progress has been reset.", Toast.LENGTH_SHORT).show()

            // Recreate the activity to refresh the UI
            recreate()
        }
    }
}
