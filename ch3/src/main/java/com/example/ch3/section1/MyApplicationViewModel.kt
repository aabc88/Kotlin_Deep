package com.example.ch3.section1

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

//ViewModel생성시 매개변수로 Application객체를 전달하고 싶을 때
//직접 생성하지 않고 ViewModelProvider등으로 생성
class MyApplicationViewModel(application: Application) : AndroidViewModel(application) {
    var count = 0

    init {
        Log.d("EJ", "MyApplicationViewModel 생성 ")
    }

    fun someFun() {
        //관심분리이며 viewmodel이 view는 아니고 application등을 이용해 화면제어가 가능하지만 권장X

        Log.d("EJ", "MyApplicationViewModel someFun")
        Toast.makeText(getApplication(), "viewmodel toast", Toast.LENGTH_SHORT).show()
    }
}