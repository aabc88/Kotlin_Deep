package com.example.ch1.section3

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking{
    val job1: Job = launch {
        repeat(2) {
            delay(200)
            println("job1..$it")
        }
    }

    val job3: CompletableJob = Job()
    launch(job3) {
        kotlin.repeat(5) {
            delay(200)
            println("job3..$it")
        }
    }
    delay(200)
    job3.complete()


    println()
}