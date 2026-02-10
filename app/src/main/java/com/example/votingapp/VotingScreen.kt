package com.example.votingapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.votingapp.databinding.ActivityVotingScreenBinding

class VotingScreen : AppCompatActivity() {
    private lateinit var binding: ActivityVotingScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVotingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.votingABtn.setOnClickListener {
            startActivity(Intent(this, ResultScreen::class.java))
            finish()
        }

        binding.votingBBtn.setOnClickListener {
            startActivity(Intent(this, ResultScreen::class.java))
            finish()
        }



    }
}