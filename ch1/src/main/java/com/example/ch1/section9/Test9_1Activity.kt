package com.example.ch1.section9

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest91Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class Test9_1Activity : AppCompatActivity(), CoroutineScope {
    val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job
    lateinit var binding: ActivityTest91Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTest91Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnDispatch.setOnClickListener {
            //코루틴을 구동시켜 특정 업무가 진행되게 하가조 한다.
            /*CoroutineScope(Dispatchers.Default).launch {
                var result = 0
                repeat(5) {
                    result += it
                    delay(500)
                }
                binding.tvResult.text = result.toString()
            }*/

            //Main에서 업무처리 > 상당히 비효율적 모든 부분이 main thread에 의해 처리
            /*CoroutineScope(Dispatchers.Main).launch {
                var result = 0
                repeat(5) {
                    result += it
                    delay(500)
                }
                binding.tvResult.text = result.toString()
            }*/

            CoroutineScope(Dispatchers.Default).launch {
                var result = 0
                repeat(5) {
                    result += it
                    delay(500)
                }
                launch(Dispatchers.Main) {
                    binding.tvResult.text = result.toString()
                }
            }
        }

        binding.btnFinish.setOnClickListener {
            finish()
        }

        binding.btnLifecycle.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                repeat(5) {
                    Log.d("EJ", "coroutine $it")
                    delay(1000)
                }
            }
        }

    }//

    override fun onDestroy() {
        super.onDestroy()
        Log.d("EJ", "onDestroy...")
    }
}//