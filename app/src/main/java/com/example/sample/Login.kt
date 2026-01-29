package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()

        // **THE FIX**: Check if user is already logged in at the very start.
        // If they are, bypass the login screen entirely.
        if (mAuth.currentUser != null) {
            checkNicknameAndProceed(mAuth.currentUser)
            // Return prevents the login screen from being shown unnecessarily
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etEmail = findViewById(R.id.input_email)
        etPassword = findViewById(R.id.input_password)

        val loginButton: Button = findViewById(R.id.confirm_login)
        loginButton.setOnClickListener {
            validateAndLogin()
        }

        val registerTextView: TextView = findViewById(R.id.textViewRegister)
        registerTextView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    // The onStart method was removed to prevent the race condition.

    private fun validateAndLogin() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty()) {
            etEmail.error = "Email is required"
            etEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Invalid email format"
            etEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            etPassword.error = "Password is required"
            etPassword.requestFocus()
            return
        }

        Toast.makeText(this, "Logging In...", Toast.LENGTH_SHORT).show()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    checkNicknameAndProceed(user)
                } else {
                    Toast.makeText(
                        this, "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun checkNicknameAndProceed(user: FirebaseUser?) {
        if (user == null) return

        val sharedPrefs = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val nickname = sharedPrefs.getString("NICKNAME", null)

        val intent = if (nickname.isNullOrBlank()) {
            Intent(this, NicknameActivity::class.java)
        } else {
            Intent(this, DashboardActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
