package com.example.ch1.section3

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//CompletableDeferred 활용
fun something(): Deferred<String> {
    val deferred = CompletableDeferred<String>()

    GlobalScope.launch {
        delay(500)
        deferred.complete("hello world")
    }
    return deferred
}

fun main() = runBlocking {
    println("step1")
    val deferred = something()
    println("step2")
    println("result : ${deferred.await()}")

    val deferred2 = CompletableDeferred<String>()
    deferred2.invokeOnCompletion { e ->
        if (e == null) {
            println("callback...${deferred2.getCompleted()}")
        }
    }
    launch {
        delay(1000)
        deferred2.complete("hello....")

    }
    println()
}