package com.example.votingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.votingapp.databinding.ActivityLoginScreenBinding


class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("votingApp", MODE_PRIVATE)



        binding.enterVotingBtn.setOnClickListener {


            val finished = prefs.getBoolean("voting_finished", false)
            if (finished) {
                toast("Voting has finished!")
                return@setOnClickListener
            }

            val idText = binding.etStudentId.text.toString()
            if (idText.isEmpty()) {
                toast("Enter student ID")
                return@setOnClickListener
            }

            val id = idText.toInt()

            if (id !in 1..24) {
                toast("Invalid ID (1–24 only)")
                return@setOnClickListener
            }

            if (prefs.getBoolean("voted_$id", false)) {
                toast("This student already voted")
            } else {
                val intent = Intent(this, VotingScreen::class.java)
                intent.putExtra("student_id", id)
                startActivity(intent)
            }
            startActivity(Intent(this@LoginScreen, VotingScreen::class.java))
        }

        binding.finishedVotingBtn.setOnClickListener {
            prefs.edit { putBoolean("voting_finished", true) }
            toast("Voting Finished")
        }

        binding.resultBtn.setOnClickListener {
            val finished = prefs.getBoolean("voting_finished", false)
            if (!finished) {
                toast("Finish voting first!")
            } else {
                startActivity(Intent(this, ResultScreen::class.java))
            }
        }


    }
    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}