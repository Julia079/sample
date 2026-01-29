package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView
import androidx.core.content.edit
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var usernameTextView: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        val beginGameCard: MaterialCardView = findViewById(R.id.BeginGame)
        val logoutCard: MaterialCardView = findViewById(R.id.logout_card)
        val summaryCard: CardView = findViewById(R.id.view_summary)
        val leaderboardCard: MaterialCardView = findViewById(R.id.view_leaderboard)
        usernameTextView = findViewById(R.id.username)

        beginGameCard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        logoutCard.setOnClickListener {
            // 1. Sign out from Firebase
            mAuth.signOut()

            // 2. Clear local nickname data
            val sharedPrefs = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            sharedPrefs.edit { clear() }

            // 3. Navigate to Login and clear activity stack
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        summaryCard.setOnClickListener {
            val intent = Intent(this, SummaryActivity::class.java)
            startActivity(intent)
        }

        leaderboardCard.setOnClickListener {
            val intent = Intent(this, LeaderboardActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the nickname every time the activity is shown
        val sharedPrefs = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val nickname = sharedPrefs.getString("NICKNAME", "Username")
        usernameTextView.text = nickname
    }
}
