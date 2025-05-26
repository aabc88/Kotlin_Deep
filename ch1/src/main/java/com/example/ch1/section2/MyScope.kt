package com.example.ch1.section2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class MyScope: CoroutineScope {
    val scopeJob: Job = Job()

    //이 scope안에서 동작하는 모든 코루틴을 위한 공통 정보설정
    //Dispatcher.Default : 이 스코프의 코루틴은 해당 스레드 풀에서 동작한다.
    //scopeJob: 스코프잡을 선언 이렇게 되면 코루틴들의 잡이 이 Job의 하위 Job이 됨
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + scopeJob
}

fun main() = runBlocking {
    val myScope = MyScope()
    val job1 = myScope.launch {
        repeat(3) {
            delay(300)
            println("first coroutine $it")
        }
    }

    val job2 = myScope.launch {
        repeat(3) {
            delay(200)
            println("second coroutine $it")
        }
    }
    delay(500)
    job1.join()
    job2.join()
    myScope.scopeJob.cancel()
    delay(500)

}