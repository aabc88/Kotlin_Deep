package com.example.ch3.section1.viewmodel

import androidx.lifecycle.ViewModel

class MyViewModel1: ViewModel() {
    var data = "hello"

    fun someFun(): String {
        return "someFun result"
    }
}