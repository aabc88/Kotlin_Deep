package com.example.ch1.section1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    //coroutine vs thread 성능 테스트
    val count = 10_00
    var time = measureTimeMillis {
        //thread를 10000개 구동 시켜 각각의 업무 처리 되게
        val threadJob = List(count) {
            thread {
                Thread.sleep(1000)
                print(".")
            }
        }
        threadJob.forEach { it.join() }
    }
    println()
    println("thread $count, total time : $time")


    //동일 업무를 코투린으로
    time = measureTimeMillis {
        val coroutineJob = List(count) {
            launch {
                delay(1000)
                print(".")
            }
        }
        coroutineJob.forEach { it.join() }
    }
    println()
    println("coroutine $count, total time : $time")
}