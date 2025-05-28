package com.example.ch3.section2

import android.util.Log
import androidx.lifecycle.LiveData

//이 livedata를 이용해 데이터를 발행하긴 하나 api를 직접 정의하기 위해
class MyLiveData: LiveData<String>() {
    fun sayHello(name: String) {
        postValue("hello $name")
    }

    override fun onActive() {
        super.onActive()
        Log.d("EJ", "onActive: ")
    }

    override fun onInactive() {
        super.onInactive()
        Log.d("EJ", "onInactive: ")
    }
}