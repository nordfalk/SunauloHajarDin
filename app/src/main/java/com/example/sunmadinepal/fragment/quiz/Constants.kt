package com.example.sunmadinepal.fragment.quiz

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.example.sunmadinepal.R

object Constants {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_QUESTIONS: String = "correct_questions"

    fun getQuestions(context: Context): MutableList<Question> {
        val questionsList = mutableListOf<Question>()

        val que1 = Question(
            1, context.getString(R.string.first_question),
            context.getString(R.string.first_option), context.getString(R.string.second_option),
            context.getString(R.string.third_option), 2,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_1, null),
        )
        questionsList.add(que1)


        val que2 = Question(
            2, context.getString(R.string.second_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "",
            2,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_2, null),
        )
        questionsList.add(que2)

        val que3 = Question(
            3, context.getString(R.string.third_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 1,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_3, null),
        )
        questionsList.add(que3)

        val que4 = Question(
            4, context.getString(R.string.fourth_question),
            context.getString(R.string.fourth_question_first_option),
            context.getString(R.string.fourth_question_second_option),
            context.getString(R.string.fourth_question_third_option), 1,

            ResourcesCompat.getDrawable(context.resources, R.drawable.question_4, null),
        )

        questionsList.add(que4)

        val que5 = Question(
            5, context.getString(R.string.fifth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 2,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_5, null),
        )
        questionsList.add(que5)

        val que6 = Question(
            6, context.getString(R.string.sixth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 2,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_6, null),
        )
        questionsList.add(que6)

        val que7 = Question(
            7, context.getString(R.string.seventh_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 1,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_7, null),
        )
        questionsList.add(que7)

        val que7_1 = Question(
            8, context.getString(R.string.seventh_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 1,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_8, null),
        )
        questionsList.add(que7_1)

        val que7_2 = Question(
            9, context.getString(R.string.seventh_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 2,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_9, null),
        )
        questionsList.add(que7_2)

        val que8 = Question(
            10, context.getString(R.string.eight_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 1,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_10, null),
        )
        questionsList.add(que8)

        val que8_1 = Question(
            11, context.getString(R.string.eight_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 1,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_11, null),
        )
        questionsList.add(que8_1)

        val que8_2 = Question(
            12, context.getString(R.string.eight_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 2,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_12, null),
        )
        questionsList.add(que8_2)

        val que9 = Question(
            13, context.getString(R.string.ninth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 1,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_13, null),
        )
        questionsList.add(que9)

        val que9_1 = Question(
            15, context.getString(R.string.ninth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 1,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_14, null),
        )
        questionsList.add(que9_1)

        val que9_2 = Question(
            15, context.getString(R.string.ninth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 2,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_15, null),
        )
        questionsList.add(que9_2)

        val que10 = Question(
            16, context.getString(R.string.tenth_question),
            context.getString(R.string.second_question_first_option),
            context.getString(R.string.second_question_second_option), "", 1,
            ResourcesCompat.getDrawable(context.resources, R.drawable.question_16, null),
        )

        questionsList.add(que10)
        return questionsList
    }

}