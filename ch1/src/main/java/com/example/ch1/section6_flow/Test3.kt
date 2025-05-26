package com.example.ch1.section6_flow

import kotlinx.coroutines.flow.MutableStateFlow

//MutableStateFlow
//hot stream 어떤 특정 상태를 발행하고 그값이 변경 시에
//구독자를 움직이게 하여 업무처리가 되게 한다

val stateFlow = MutableStateFlow(0) //상태를 표현함이 목적임으로 초기값 있어야함

suspend fun changeState(date: Int) {

}