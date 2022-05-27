package com.example.sunmadinepal.fragment.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sunmadinepal.databinding.FragmentQuizBinding
import com.example.sunmadinepal.utils.openActivity

class QuizFragment : Fragment() {
    lateinit var quizBinding: FragmentQuizBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        quizBinding = FragmentQuizBinding.inflate(layoutInflater)
        return quizBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizBinding.startQuizButton.setOnClickListener {
            requireActivity().openActivity(StartQuizActivity::class.java).apply {
                requireActivity().finish()
            }
        }
    }
}