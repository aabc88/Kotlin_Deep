package com.example.ch1.section3

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    //start..지정하지 않은 경우 default가 지정된 경우
    //만들자마자 구동.. 구동되기 전에 취소하면 실행 안됨
    val job1 = launch {
        println("job1..")
    }
    job1.cancel()

    val job2 = launch(start = CoroutineStart.ATOMIC) {
        println("job2..1")
        delay(200)
        println("job2..2")
    }
    job2.cancel()

    val job3 = launch(start = CoroutineStart.UNDISPATCHED, context = Dispatchers.IO) {
        println("job3...1..${Thread.currentThread().name}")
        delay(200)
        println("job3...2..${Thread.currentThread().name}")
    }
    job3.join()

    val job4 = launch(start = CoroutineStart.LAZY) {
        println("job4")
    }
    delay(200)
    println("main1")
    job4.start()
    job4.join()
    delay(200)
}