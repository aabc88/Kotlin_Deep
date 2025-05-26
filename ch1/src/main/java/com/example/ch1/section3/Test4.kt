package com.example.ch1.section3

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    //test1 lifecycle
    val job1 = launch(start = CoroutineStart.LAZY) {
        println("job1..1")
        delay(300)
        println("job1..2")
    }
    println(
        "main step1: isActive : ${job1.isActive}, isCompleted : ${job1.isCompleted}" +
                "isCancelled : ${job1.isCancelled}"
    )
    delay(200)
    job1.start()
    println(
        "main step1: isActive : ${job1.isActive}, isCompleted : ${job1.isCompleted}" +
                "isCancelled : ${job1.isCancelled}"
    )
    job1.join()
    println(
        "main step1: isActive : ${job1.isActive}, isCompleted : ${job1.isCompleted}" +
                "isCancelled : ${job1.isCancelled}"
    )


    println()
    val job2 = launch(Dispatchers.IO) {
        var start = System.currentTimeMillis()
        var i = 0
        while (i < 5) {
            if (System.currentTimeMillis() >= start) {
                i++
                println("job2..$i")
                start += 500
            }
        }
    }
    delay(1000)
    println("main cancel")
    job2.cancelAndJoin()
    println("main cancel.. end")
}