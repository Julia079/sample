package com.example.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EndingDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endingdialog)

        val radioGroup = findViewById<RadioGroup>(R.id.assessment_radio_group)
        val submitButton = findViewById<Button>(R.id.submit_assessment_button)

        submitButton.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Please select an option.", Toast.LENGTH_SHORT).show()
            } else {
                val choice = if (selectedId == R.id.yes_radio_button) "Yes" else "No"
                val intent = Intent(this, YesActivity::class.java).apply {
                    putExtra("USER_CHOICE", choice)
                }
                startActivity(intent)
                finish()
            }
        }
    }
}
