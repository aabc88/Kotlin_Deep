package com.example.ch1.section1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun suspendFun(no: Int): Int {
    var sum = 0
    for (i in 1..no) {
        delay(i * 10L)
        sum += i
    }
    return sum
}

fun noSuspendFun(no: Int): Int {
    //suspendFun(10) //error suspend함수는 꼭 suspend함수에서 호출
    return 1
}

fun main() = runBlocking{
    for (i in 1..3) {
        launch(Dispatchers.Default) {
            println("coroutine..$i start : ${Thread.currentThread().name}")
            suspendFun(10)
            println("coroutine..$i start : ${Thread.currentThread().name}")
        }
    }
    Thread.sleep(2000)
}