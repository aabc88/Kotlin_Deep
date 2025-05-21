package com.example.ch1.section1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//코루틴은 비동기를 목적으로 함

//runBlocking은 main 함수 혹은 테스트 함수에서 coroutine api를 사용할 목적으로 설계

fun main() = runBlocking {
    //main thread에 의해 실행 시작
    println("Step1...")
    //코루틴을 실행..
    //비동기를 목적으로 한다 코루틴을 실행시킨 스레드픞 멈추게 하지 않는다
    //launch coroutine builder 코루틴을 하나 만들고 실행
    //리턴값은 코루틴에 의해 실행되는 업무를 지칭
    val job = launch {
        var sum = 0;
        for (i in 1..10) {
            delay(100)
            sum += i
            println(i)
        }
        println("coroutine... $sum")
    }
    println("Step2...")
    job.join()
    println("main end")
}

//==>코루틴을 구동시켰던 main thread가 coroutine이 끝날때 까지 대기하지 않고 같이 움직임
//비동기로 코루틴이 실행되었음.