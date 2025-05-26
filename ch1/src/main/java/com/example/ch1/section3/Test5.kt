package com.example.ch1.section3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//async, await

fun someOne() = GlobalScope.async(Dispatchers.IO) {
    println("코루틴 one")
    delay(300)
    "hello world"//실행시킨곳에 전달할 결과 데이터
}

fun someTwo() = GlobalScope.async(Dispatchers.Default) {
    println("코루틴 two")
    delay(200)
    10
}

fun main() = runBlocking {
    println("step1")
    val oneDeferred = someOne()
    println("step2")
    val twoDeferred = someTwo()
    println("step3")
    println("result : ${oneDeferred.await() + twoDeferred.await()}")

}