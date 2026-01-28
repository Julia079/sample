package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import androidx.core.content.edit

class NicknameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        val saveButton: Button = findViewById(R.id.create_nickname_popup_button)
        val closeButton: ImageView = findViewById(R.id.nickname_popup_close_button)
        val nicknameLayout: TextInputLayout = findViewById(R.id.nickname_box)

        saveButton.setOnClickListener {
            val newNickname = nicknameLayout.editText?.text.toString().trim()

            if (newNickname.isEmpty()) {
                nicknameLayout.error = "Nickname is required to login"
                nicknameLayout.isErrorEnabled = true
            } else {
                nicknameLayout.error = null
                nicknameLayout.isErrorEnabled = false

                // Save the current user's nickname for this session.
                val userProfilePrefs = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                userProfilePrefs.edit {
                    putString("NICKNAME", newNickname)
                }

                // Add the new nickname to the definitive master list of all users.
                // This new method is simpler and more reliable.
                val allUsersPrefs = getSharedPreferences("AllUsers", Context.MODE_PRIVATE)
                allUsersPrefs.edit {
                    // We use the nickname itself as the key. The value doesn't matter.
                    putBoolean(newNickname, true)
                }

                val intent = Intent(this, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        closeButton.setOnClickListener {
            finish()
        }
    }
}
