package com.example.ch1.section9

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest91Binding
import com.example.ch1.databinding.ActivityTest92Binding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Test9_2Activity : AppCompatActivity() {
    lateinit var binding: ActivityTest92Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTest92Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btn3.setOnClickListener {
            finish()
        }

        binding.btn2.setOnClickListener {
            //코루틴을 실행 시키려면 먼저 scope가 선언되어 있어야함.
            //activity, fragment에서 CoroutineScope를 구현하여 자체가 코루틴 스코프가 되거나
            //CoroutinScope()함수를 이용해 스코프를 만들어 사용
            //액티비티, 프래그먼트
            lifecycleScope.launch {
                repeat(5) {
                    Log.d("EJ", "coroutine $it")
                    delay(1000)
                }
            }
        }


    }//

    override fun onDestroy() {
        super.onDestroy()
        Log.d("EJ", "onDestroy: ")
    }
}//