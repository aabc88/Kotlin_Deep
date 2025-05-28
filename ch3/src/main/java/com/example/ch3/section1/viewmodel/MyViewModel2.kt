package com.example.ch3.section1.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class MyViewModel2: ViewModel() {
    var count = 0

    init {
        Log.d("EJ", "ViewModel2 ")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("EJ", "ViewModel2 Clear")
    }
}