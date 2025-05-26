package com.example.ch1.section5

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val myChannel1 = produce {
        var x = 1
        while (true) {
            delay(100)
            send(x++)
        }
    }
    launch {
        myChannel1.consumeEach {
            println("job1 : $it")
        }
    }
    launch {
        myChannel1.consumeEach {
            println("job2 : $it")
        }
    }
    launch {
        myChannel1.consumeEach {
            println("job3 : $it")
        }
    }
    delay(3000)
    myChannel1.cancel()


}