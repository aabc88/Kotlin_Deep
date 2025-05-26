package com.example.ch1.section4

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("exceptionHandler..${throwable.message}")
}

suspend fun something1() = coroutineScope {
    println(
        "thread1 name: ${Thread.currentThread().name}" + " ${coroutineContext[CoroutineName]}"
    )

    launch {
        repeat(3) {
            delay(300)
            println("coroutine 1.. $it")
        }
    }
    launch {
        repeat(3) {
            delay(300)
            println("coroutine 2..$it")
            if (it == 1) throw Exception("coroutine1 exception")
        }
    }
}

suspend fun something2() = coroutineScope {
    println(
        "thread2 name: ${Thread.currentThread().name}" + " ${coroutineContext[CoroutineName]}"
    )

    launch {
        repeat(3) {
            delay(300)
            println("coroutine 3.. $it")
        }
    }
    launch {
        repeat(3) {
            delay(300)
            println("coroutine 4..$it")
            if (it == 1) throw Exception("coroutine2 exception")
        }
    }
}

fun main() = runBlocking {
    val scope1 =
        CoroutineScope(newSingleThreadContext("myThread1") + CoroutineName("myCoroutine1") + exceptionHandler)

    scope1.launch { something1() }.join()

    println()

    val scope2 =
        CoroutineScope(newSingleThreadContext("myThread2") + CoroutineName("myCoroutine2") + exceptionHandler)
    scope2.launch { something2() }.join()

    println()
}