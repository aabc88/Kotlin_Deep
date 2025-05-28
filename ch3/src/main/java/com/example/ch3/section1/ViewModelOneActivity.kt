package com.example.ch3.section1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.R
import com.example.ch3.databinding.ActivityViewModelOneBinding
import com.example.ch3.section1.viewmodel.MyViewModel2

class ViewModelOneActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewModelOneBinding
    var activityCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelOneBinding.inflate(layoutInflater)
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
            activityCount++
        }

        binding.btnGetCount.setOnClickListener {
            Toast.makeText(this, "one : $activityCount, two : ${viewModel.count}", Toast.LENGTH_SHORT).show()
        }

        binding.btnGoTwoActivity.setOnClickListener {
            startActivity(Intent(this, ViewModelTwoActivity::class.java))
        }
    }
}