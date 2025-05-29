package com.example.ch3.section3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.R
import com.example.ch3.databinding.ActivityLifecycleAwareBinding

class LifecycleAwareActivity : AppCompatActivity() {
    lateinit var binding: ActivityLifecycleAwareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifecycleAwareBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGoSome.setOnClickListener {
            startActivity(Intent(this, SomeActivity::class.java))
        }

        val lifecycleObserver = MyActivityLifecycleObserver()
        lifecycleObserver.observe(this) {
            binding.tvResult.text = it.toString()
        }
        lifecycle.addObserver(lifecycleObserver)
    }
}