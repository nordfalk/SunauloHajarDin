package com.example.sunmadinepal.fragment.quiz

import android.content.Context
import com.example.sunmadinepal.R

object Constants{

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_QUESTIONS: String = "correct_questions"

    fun getQuestions(context : Context): MutableList<Question>{
        val questionsList = mutableListOf<Question>()

        val que1 = Question(
            1, context.getString(R.string.first_question),
            context.getString(R.string.first_option), context.getString(R.string.second_option),
            context.getString(R.string.third_option),2
        )

        questionsList.add(que1)


        val que2 = Question(
            2, context.getString(R.string.second_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option),"",
             2
        )

        questionsList.add(que2)


        val que3 = Question(
            3, context.getString(R.string.third_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option),"",1
        )
        questionsList.add(que3)
        val que4 = Question(
            4, context.getString(R.string.fourth_question),
            context.getString(R.string.fourth_question_first_option),
            context.getString(R.string.fourth_question_second_option),
            context.getString(R.string.fourth_question_third_option),1
        )

        questionsList.add(que4)

        val que5 = Question(
            5, context.getString(R.string.fifth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option),"",2
        )
        questionsList.add(que5)

        val que6 = Question(
            6, context.getString(R.string.sixth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option),"",2
        )
        questionsList.add(que6)

        val que7 = Question(
            7, context.getString(R.string.seventh_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option),"",1
        )
        questionsList.add(que7)

        val que8 = Question(
            8, context.getString(R.string.eight_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option),"",2
        )
        questionsList.add(que8)

        val que9 = Question(
            9, context.getString(R.string.ninth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option),"",1
        )
        questionsList.add(que9)

        val que10 = Question(
            10, context.getString(R.string.tenth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option),"",1
        )

        questionsList.add(que10)
        return questionsList
    }

}