package com.example.sunmadinepal.fragment.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sunmadinepal.R
import com.example.sunmadinepal.databinding.ActivityStartQuizBinding
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor
import java.util.*
import kotlin.collections.ArrayList

class StartQuizActivity : AppCompatActivity() , View.OnClickListener {
    lateinit var activityStartQuizBinding: ActivityStartQuizBinding
    private var currentPosition: Int = 1
    private var questionList: MutableList<Question> = mutableListOf()
    private var selectedOptionPosition: Int = 0
    private var correctAnswer: Int = 0
    val string = Locale.getDefault().language

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityStartQuizBinding = ActivityStartQuizBinding.inflate(layoutInflater)
        setContentView(activityStartQuizBinding.root)

        activityStartQuizBinding.startQuizToolBar.toolbarActionTitle.text = getString(R.string.nutrition)
        questionList = Constants.getQuestions(this)

        activityStartQuizBinding.yesTv.setOnClickListener(this)
        activityStartQuizBinding.noTv.setOnClickListener(this)
        activityStartQuizBinding.thirdQuestion.setOnClickListener(this)
        activityStartQuizBinding.submitButton.setOnClickListener(this)

        activityStartQuizBinding.skipTv.setOnClickListener {
           finishAndRemoveTask()
        }

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()

        activityStartQuizBinding.progressBar.max = questionList.size
        setQuestion()
        updateImage()
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        selectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#757575"))
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(){
        val question = questionList[currentPosition-1]

        defaultOptionsView()

        if(currentPosition == questionList.size){
            activityStartQuizBinding.submitButton.text = getString(R.string.finish)
        }
        else{
            activityStartQuizBinding.submitButton.text = getString(R.string.submit)
        }

        activityStartQuizBinding.progressBar.progress = currentPosition
        activityStartQuizBinding.progressCountTv.text = "$currentPosition / ${activityStartQuizBinding.progressBar.max}"
        activityStartQuizBinding.questionTv.text = question.question
        activityStartQuizBinding.yesTv.text = question.firstOption
        activityStartQuizBinding.noTv.text = question.secondOption

        if (question.thirdOption.isNotEmpty()){
            activityStartQuizBinding.thirdQuestion.visibility = View.VISIBLE
            activityStartQuizBinding.thirdQuestion.text = question.thirdOption

        }else{
            activityStartQuizBinding.thirdQuestion.visibility = View.GONE

        }

        activityStartQuizBinding.yesTv.setTextColor(Color.parseColor("#757575"))
        activityStartQuizBinding.noTv.setTextColor(Color.parseColor("#757575"))
        activityStartQuizBinding.thirdQuestion.setTextColor(Color.parseColor("#757575"))

    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, activityStartQuizBinding.yesTv)
        options.add(1, activityStartQuizBinding.noTv)
        options.add(1, activityStartQuizBinding.thirdQuestion)


        options.forEach { it ->
            it.setTextColor(Color.parseColor("#757575"))
            it.typeface = Typeface.DEFAULT
            it.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                activityStartQuizBinding.yesTv.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                activityStartQuizBinding.noTv.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            3 -> {
                activityStartQuizBinding.thirdQuestion.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.yes_tv ->{
                selectedOptionView(activityStartQuizBinding.yesTv,1)
            }
            R.id.no_tv ->{
                selectedOptionView(activityStartQuizBinding.noTv,2)

            }
            R.id.third_question ->{
                selectedOptionView(activityStartQuizBinding.thirdQuestion,3)

            }
            R.id.submit_button ->{
                if(selectedOptionPosition == 0){

                    currentPosition++

                    when{
                        currentPosition <= questionList.size ->{
                            setQuestion()
                        }
                        else->{
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.CORRECT_QUESTIONS, correctAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionList.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else{
                    val question = questionList[currentPosition-1]
                    if(question.correctAnswer != selectedOptionPosition){
                        answerView(selectedOptionPosition,R.drawable.incorrect_option_border_bg)
                    }
                    else{
                        correctAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(currentPosition == questionList.size){
                        activityStartQuizBinding.submitButton.text = getString(R.string.finish)
                    }
                    else{
                        activityStartQuizBinding.submitButton.text = getString(R.string.go_to_next_question)
                    }
                    selectedOptionPosition = 0
                }
                updateImage()
            }
        }
    }

    private fun updateImage(){
        Log.d("curreponsot ", currentPosition.toString())
        try {
            Glide.with(this).load(questionList[currentPosition-1].questionImage).apply(
                RequestOptions().error(R.drawable.ic_launcher_background)
            ).into(activityStartQuizBinding.questionRelatedImageView)
        }catch (e:Exception){

        }

    }
}