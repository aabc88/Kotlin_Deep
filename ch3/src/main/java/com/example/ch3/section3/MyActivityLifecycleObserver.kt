package com.example.ch3.section3

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class MyActivityLifecycleObserver: LiveData<Int>(), DefaultLifecycleObserver {
    var isRunning = false
    var count = 0
    inner class ThreadClass: Thread() {
        override fun run() {
            while (isRunning) {
                SystemClock.sleep(1000)
                postValue(count++)
            }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d("EJ", "onStart: MyObserver start")
        isRunning = true
        ThreadClass().start()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d("EJ", "onStop: MYObserver stop")
        isRunning = false
    }
}