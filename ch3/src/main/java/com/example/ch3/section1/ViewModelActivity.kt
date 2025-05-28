package com.example.ch3.section1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.R
import com.example.ch3.databinding.ActivityViewModelBinding
import com.example.ch3.section1.viewmodel.MyViewModel1

class ViewModelActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //case1 - ViewModel객체 선언
        //관심의 분리는 맞지만 ViewModel을 이용한 Activity상태유지는 불가능
        //Activity 상태 유지가 되려면 ViewModel의 lifecycle이 Activity와 다르게 되어야 하는데
        //코드에서 직접 생성함으로 매번 생성되어서...
        //val viewModel = MyViewModel1()

        //ViewModelProvider를 이용하면 직접생성하지 않고 정보만 주고 관심분리가 됨.
        //액티비티 상태 유지도 가능함.
        //매번 생성되지 않고 싱글톤으로 운영됨
        //val viewModel = ViewModelProvider(this).get(MyViewModel1::class.java)

        //deligate이용
        val viewModel: MyViewModel1 by viewModels()
        binding.btn1.setOnClickListener {
            Toast.makeText(this, "${viewModel.data}, ${viewModel.someFun()}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}