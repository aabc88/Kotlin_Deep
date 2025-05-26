package com.example.ch1.section2

import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    //main start
    println("main start....${Thread.currentThread().name}")

    val job = launch {
        //코루틴을 구동시키면서 특별한 dispatcher를 지정하지 않으면
        //그 코루틴을 구동시켰던 thread에서 동작한다.
        println("coroutine start....${Thread.currentThread().name}")
        delay(300)
    }
    job.join()

    listOf("one", "two", "three").forEachIndexed { index, value ->
        launch(newSingleThreadContext("myThread $value")) {
            println("coroutine $value start... ${Thread.currentThread().name}")
            delay(100L + index * 100L) //중단함수를 만났다
            println("coroutine $value end... ${Thread.currentThread().name}")
        }
    }
    delay(3000)
}