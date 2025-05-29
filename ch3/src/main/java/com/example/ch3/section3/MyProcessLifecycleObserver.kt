package com.example.ch3.section3

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyProcessLifecycleObserver: DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d("EJ", "onStart: MyObserver start")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d("EJ", "onStop: MyObserver stop")
    }
}