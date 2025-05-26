package com.example.ch1.section2

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class MyScope2 : CoroutineScope {
    val superJob = Job()

    //여러곳에서 발생하는 에러를 한 곳에서 일관되게 처리할 수 있다. 큰 이점
    val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        println("coroutine exception .... ${context[CoroutineName.Key]} : $exception")
    }
    override val coroutineContext: CoroutineContext
        get() = CoroutineName("my-scope") + Dispatchers.Default + superJob + exceptionHandler
}

fun main() = runBlocking {
    val scope = MyScope2()
    scope.launch {
        println("${coroutineContext[CoroutineName.Key]} is on thread : ${Thread.currentThread().name}")
        throw Exception("error coroutine1")
    }
    scope.launch(CoroutineName("my-coroutine") + newSingleThreadContext("my one thread")) {
        println("${coroutineContext[CoroutineName.Key]} is on thread : ${Thread.currentThread().name}")

    }
    delay(1000)
}