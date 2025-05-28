package com.example.ch3.section1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.R
import com.example.ch3.databinding.ActivityViewModelOneBinding
import com.example.ch3.databinding.ActivityViewModelTwoBinding
import com.example.ch3.section1.viewmodel.MyViewModel2
import kotlin.getValue

class ViewModelTwoActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewModelTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelTwoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel: MyViewModel2 by viewModels()
        binding.btnIncrement.setOnClickListener {
            viewModel.count++
        }

        binding.btnGetCount.setOnClickListener {
            Toast.makeText(this, "${viewModel.count}", Toast.LENGTH_SHORT).show()
        }

    }
}