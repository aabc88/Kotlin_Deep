package com.example.ch3.section3

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.ch3.mission1.AppApplication

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //앱의 라이프 사이클 옵저버 등록
        ProcessLifecycleOwner.get().lifecycle.addObserver(MyProcessLifecycleObserver())
    }
}