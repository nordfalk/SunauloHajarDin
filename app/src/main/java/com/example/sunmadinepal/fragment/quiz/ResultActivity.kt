package com.example.sunmadinepal.fragment.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.rangeTo
import com.example.sunmadinepal.R
import com.example.sunmadinepal.databinding.ActivityResultBinding
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor

class ResultActivity : AppCompatActivity() {

    lateinit var activityResultBinding: ActivityResultBinding
    @SuppressLint("StringFormatInvalid", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(activityResultBinding.root)

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val totalCorrectQuestions = intent.getIntExtra(Constants.CORRECT_QUESTIONS,0)
//        activityResultBinding.answerTv.text = "Your answered $totalCorrectQuestions question out of $totalQuestions correctly"
        activityResultBinding.answerTv.text = "${getString(R.string.your_answered, totalCorrectQuestions.toString())} ${getString(R.string.question_out_of)} $totalQuestions ${getString(R.string.correctly, totalQuestions)}"



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