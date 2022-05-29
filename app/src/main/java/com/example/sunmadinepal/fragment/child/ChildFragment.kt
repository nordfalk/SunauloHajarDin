package com.example.sunmadinepal.fragment.child

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sunmadinepal.databinding.FragmentChildBinding


class ChildFragment : Fragment() {
    lateinit var fragmentChildBinding: FragmentChildBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentChildBinding = FragmentChildBinding.inflate(layoutInflater)
        return fragmentChildBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}