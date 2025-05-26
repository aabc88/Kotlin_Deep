package com.example.ch1.section2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.system.measureTimeMillis

suspend fun networkRequest(requestURL: String): String {
    val url = URL(requestURL)
    (url.openConnection() as? HttpURLConnection)?.run {
        requestMethod = "GET"
        setRequestProperty("Content-Type", "application/json;utf-8")
        setRequestProperty("Accept", "application/json")
        setRequestProperty("x-api-key", "request-free-v1")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val buffer = StringBuffer()
        reader.lines().forEach { buffer.append(it) }
        return buffer.toString()
    }
    return " "
}

fun main() = runBlocking {
    val jobs = mutableListOf<Job>()
    repeat(50) {
        //jobs.add(launch(Dispatchers.Default) {
        jobs.add(launch(Dispatchers.IO) {
            val result = networkRequest("https://reqres.in/api/users/1")
            println(result)
        })
    }
    val time = measureTimeMillis {
        jobs.forEach { it.join() }
    }
    println("time : $time")
}