package com.example.ch1.section5

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<Int>()

    launch {
        repeat(3) {
            delay(100)
            channel.send(it)
            println("send $it")
        }
        channel.close()
    }

    for(data in channel) {
        println("receiver.. $data")
        
    }





    println()
}