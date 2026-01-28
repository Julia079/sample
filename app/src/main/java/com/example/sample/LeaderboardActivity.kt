package com.example.sample

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.data.SampleSkills

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var leaderboardRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        leaderboardRecyclerView = findViewById(R.id.leaderboard_recycler_view)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(this)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshLeaderboard()
    }

    private fun refreshLeaderboard() {
        val leaderboardData = calculateLeaderboardData()
        leaderboardRecyclerView.adapter = LeaderboardAdapter(leaderboardData)
    }

    private fun calculateLeaderboardData(): List<Pair<String, Int>> {
        val allUsersPrefs = getSharedPreferences("AllUsers", Context.MODE_PRIVATE)
        val levelStatusPrefs = getSharedPreferences("LevelStatus", Context.MODE_PRIVATE)

        // Get the definitive list of all nicknames from the keys of the AllUsers storage.
        val allNicknames = allUsersPrefs.all.keys

        val leaderboard = mutableListOf<Pair<String, Int>>()

        // For each real user, calculate their total score.
        for (nickname in allNicknames) {
            var totalScore = 0
            for (skill in SampleSkills.skills) {
                for (i in 1..10) {
                    val passedKey = "${nickname}_${skill.name}_${i}_passed"
                    if (levelStatusPrefs.getBoolean(passedKey, false)) {
                        totalScore++
                    }
                }
            }
            leaderboard.add(Pair(nickname, totalScore))
        }

        // Return the final list, sorted by score.
        return leaderboard.sortedByDescending { it.second }
    }
}
