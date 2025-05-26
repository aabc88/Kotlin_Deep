package com.example.ch1.section2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

//GlobalScope, CoroutineScope()

fun main() = runBlocking {
    GlobalScope.launch {
        repeat(3) {
            println("step1... ${Thread.currentThread().name}")
        }
    }

    println()

    GlobalScope.launch(newSingleThreadContext("myThread")) {
        repeat(3) {
            println("step2... ${Thread.currentThread().name}")
        }
    }

    println()

    //코루틴 구조화
    //동일 스코프 내 여러 코루틴이 구조화
    val job1 = launch {
        launch {
            repeat(3) {
                println("step3... $it")
                delay(200)
            }
        }
        repeat(5) {
            println("super step3... $it")
            delay(200)
        }
    }
    delay(500)
    job1.cancel()

    val job3 = Job()
    val scope1 = CoroutineScope(Dispatchers.IO + job3)
    scope1.launch {
        repeat(5) {
            println("step5... $it")
            delay(200)
        }
    }
    delay(300)

    scope1.launch {
        repeat(5) {
            println("step5... $it")
            delay(200)
        }
    }
    delay(300)
    job3.cancel()
    delay(2000)
}