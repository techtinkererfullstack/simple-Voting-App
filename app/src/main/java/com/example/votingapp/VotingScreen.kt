package com.example.votingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.votingapp.databinding.ActivityVotingScreenBinding

class VotingScreen : AppCompatActivity() {
    private lateinit var binding: ActivityVotingScreenBinding
    private var studentId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVotingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentId = intent.getIntExtra("student_id", -1)

        binding.votingABtn.setOnClickListener { submitVote("A") }
        binding.votingBBtn.setOnClickListener { submitVote("B") }
    }

    private fun submitVote(party: String) {
        val prefs = getSharedPreferences("VotingApp", MODE_PRIVATE)
        val editor = prefs.edit()

        if (party == "A") {
            val aVotes = prefs.getInt("partyA_votes", 0)
            editor.putInt("partyA_votes", aVotes + 1)
        } else {
            val bVotes = prefs.getInt("partyB_votes", 0)
            editor.putInt("partyB_votes", bVotes + 1)
        }

        editor.putBoolean("voted_$studentId", true)
        editor.apply()

        Toast.makeText(this, "Your vote has been counted", Toast.LENGTH_SHORT).show()

        finish() // Return to login for next student
    }
}