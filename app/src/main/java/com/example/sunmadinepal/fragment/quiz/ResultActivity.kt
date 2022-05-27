package com.example.sunmadinepal.fragment.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunmadinepal.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var activityResultBinding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(activityResultBinding.root)


        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val totalCorrectQuestions = intent.getIntExtra(Constants.CORRECT_QUESTIONS,0)
        activityResultBinding.answerTv.text = "Your answered $totalCorrectQuestions question out of $totalQuestions correctly"


        activityResultBinding.apply {
            removeImage.setOnClickListener {
                finish()
            }

            gotButton.setOnClickListener {
                finish()
            }
        }

    }
}