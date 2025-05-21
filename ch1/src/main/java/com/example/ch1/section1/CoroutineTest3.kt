package com.example.ch1.section1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf("one", "two", "three", "four").forEachIndexed { index, value ->
        //코루틴을 구동시키면서 어느 thread pool에서 구동될지를 지정할 수 있다.
        launch(Dispatchers.Default) {
            //이 부분을 실행시켰던 thread
            println("coroutine... $value start : ${Thread.currentThread().name}")
            Thread.sleep(100L + index * 100L)
            println("coroutine... $value end : ${Thread.currentThread().name}")
        }
    }
    Thread.sleep(2000)
}