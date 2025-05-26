package com.example.ch1.section4

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun subJob1(): String {
    delay(1000)
    println("subJob1")
    return "hello"
}

suspend fun subJob2(): String {
    delay(1000)
    println("subJob2")
    return "world"
}

suspend fun something(): String = coroutineScope {
    //scoping function 내에서 연달아서 여러 업무를 코루틴이 구동 시키고
    //모든 코루틴이 종료될 때 까지 someThing()을 호출한 코루틴의 코드 진행이 안되게 하고싶다
    val result1 = async { subJob1() }
    val result2 = async { subJob2() }
    "${result1.await()} ${result2.await()}"
}

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        println("step1")
        val result = something()
        println("step2")
        println("result = $result")
    }
    delay(2000)
}