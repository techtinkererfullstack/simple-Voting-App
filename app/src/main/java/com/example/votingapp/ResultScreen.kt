package com.example.votingapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.votingapp.databinding.ActivityResultScreenBinding

class ResultScreen : AppCompatActivity() {
    private lateinit var binding: ActivityResultScreenBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("VotingApp", MODE_PRIVATE)
        val aVotes = prefs.getInt("partyA_votes", 0)
        val bVotes = prefs.getInt("partyB_votes", 0)

        binding.votingATV.text = "Party A Votes: $aVotes"
        binding.votingBTV.text = "Party B Votes: $bVotes"

        binding.tvWinner.text = when {
            aVotes > bVotes -> "🏆 Party A Wins!"
            bVotes > aVotes -> "🏆 Party B Wins!"
            else -> "🤝 It's a Tie!"
        }
    }


}