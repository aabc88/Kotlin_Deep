package com.example.ch1.section6_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun something(): Flow<Int> = flow {
    repeat(2) {
        delay(100)
        println("emit $it")
        emit(it)
    }
}

fun main() = runBlocking {
    val myFlow = something()
    println("main1")
    myFlow.collect {
        println("collect1 : $it")
    }
    println("--------------main2")

    //비동기로 하고싶다면 launch로 실행
    launch {
        myFlow.collect {
            println("collect2 : $it")
        }
    }

    launch {
        myFlow.collect {
            println("collect3 : $it")
        }
    }
    println("--------------main3")
    delay(200)
}