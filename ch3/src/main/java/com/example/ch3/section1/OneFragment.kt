package com.example.ch3.section1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.ch3.databinding.FragmentOneBinding
import kotlin.getValue

class OneFragment: Fragment() {
    lateinit var binding: FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        //val viewModel: MyApplicationViewModel by viewModels()
        val viewModel: MyApplicationViewModel by activityViewModels()

        binding.btnIncrement.setOnClickListener {
            viewModel.count++
        }

        binding.btnGet.setOnClickListener {
            Toast.makeText(activity, "count : ${viewModel.count}", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}