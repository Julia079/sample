package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class NicknameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        val saveButton: Button = findViewById(R.id.create_nickname_popup_button)
        val closeButton: ImageView = findViewById(R.id.nickname_popup_close_button) // Use the new ID
        val nicknameLayout: TextInputLayout = findViewById(R.id.nickname_box)

        val sharedPrefs = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val currentNickname = sharedPrefs.getString("NICKNAME", "")
        nicknameLayout.editText?.setText(currentNickname)

        saveButton.setOnClickListener {
            val newNickname = nicknameLayout.editText?.text.toString()

            with(sharedPrefs.edit()) {
                putString("NICKNAME", newNickname)
                apply()
            }

            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        closeButton.setOnClickListener {
            finish()
        }
    }
}
