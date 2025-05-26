package com.example.ch1.section6_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

val helloFlow1 = flow {
    repeat(3) {
        delay(100)
        emit("hello $it")
    }
}

val helloFlow2 = flow {
    repeat(3) {
        delay(100)
        emit("world $it")
    }
}

fun <T> Flow<T>.flowMerge(other: Flow<T>): Flow<T> = flow {

}

fun main() = runBlocking {
    //데이터가 몇개 있는데 이걸 flow로 발행하고 싶을 때
    flowOf(1, 2, "hello")
        .collect {
            println("flowOf : $it")
        }
}