package com.example.ch1.section5

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Channel buffer이용
//발행과 구독이 별개임.. 둘간의 속도차이는 발생할수없음
//기본은 발행해야 구독, 구독해야 발행 , 미리 알고 움직일 수 없음
//buffer 개념을 적용해서 구독이 완료되지 않았다고 하더라도 미리 발행은 가능

fun main() = runBlocking {
    //val channel = Channel<Int>()
    val channel = Channel<Int>(3)

    launch {
        repeat(5) {
            channel.send(it)
            println("send $it")
            delay(200)
        }
        channel.close()
    }

    for (data in channel) {
        println("receive $data")
        delay(300)
    }

    println()
}