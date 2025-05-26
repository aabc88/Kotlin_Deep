package com.example.ch1.section5

import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    produce<Int> {

    }
    println()
}