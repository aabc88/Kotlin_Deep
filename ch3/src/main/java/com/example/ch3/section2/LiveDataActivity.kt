package com.example.ch3.section2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.ch3.R
import com.example.ch3.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {
    lateinit var binding: ActivityLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val viewModel: MyViewModel by viewModels()g
        val myLiveData = MyLiveData()

        myLiveData.observe(this) {
            Log.d("EJ", "myLive $it")
        }

        viewModel.liveData.observe(this, object : Observer<Int> {
            override fun onChanged(value: Int) {
                binding.tvResult.text = value.toString()
            }

        })
        binding.btnChange.setOnClickListener {
            viewModel.changeCount()
            myLiveData.sayHello("hong")
        }


    }
}