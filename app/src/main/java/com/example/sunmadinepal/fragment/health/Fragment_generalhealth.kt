package com.example.sunmadinepal.fragment.health

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunmadinepal.ViewModel.HealthViewModel
import com.example.sunmadinepal.databinding.FragmentGeneralhealthBinding
import com.example.sunmadinepal.database.adapter.CustomAdapter
import com.example.sunmadinepal.model.RecipesData
import java.util.*
import kotlin.collections.ArrayList


class Fragment_generalhealth : Fragment() {

    private var _binding: FragmentGeneralhealthBinding? = null
    private val binding get() = _binding!!
    val string = Locale.getDefault().getLanguage()
    private var _events = ArrayList<RecipesData>()
    var progressDialog: ProgressDialog? = null // Creating Progress dialog
    private lateinit var healthViewModel: HealthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        healthViewModel = ViewModelProvider(this).get(HealthViewModel::class.java)
        _binding = FragmentGeneralhealthBinding.inflate(inflater, container, false)
        val view = binding?.root
        // Inflate the layout for this fragment
        return view
    }


    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "General health information"

    }
}