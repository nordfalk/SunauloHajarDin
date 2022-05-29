package com.example.sunmadinepal.fragment.quiz

import android.graphics.drawable.Drawable

data class Question(
    val id: Int,
    val question: String,
    val firstOption: String,
    val secondOption: String,
    val thirdOption: String,
    val correctAnswer: Int,
    val questionImage: Drawable?
)