package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val beginGameCard: CardView = findViewById(R.id.BeginGame)
        beginGameCard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // *** CRITICAL FIX: Load and display the saved nickname ***
        val usernameTextView: TextView = findViewById(R.id.username)
        val sharedPrefs = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val nickname = sharedPrefs.getString("NICKNAME", "Username") // Default to "Username"
        usernameTextView.text = nickname
    }
}
