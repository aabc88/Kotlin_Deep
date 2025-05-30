package com.example.ch3.section5

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

//db에 저장되는 회원정보를 추상화 시킨 Entity클래스
//@Entity를 추가하는 것으로도 이 클래스의 멤버들을 저장하기 위한 테이블이 자동으로 준비된다.
//테이블 명은 클래스명으로
//Entity 클래스의 멤버 변수가 테이블의 column
//@PrimaryKey가 추가된 멤버의 column이 primary key가 되며 중복 x
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)//자동증가
    var id: Int = 0,
    var lastName: String,
    var firstName: String
)
@Entity
data class Address(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var street: String,
    var state: String,
    var city: String
)

@Entity
data class User2(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var lastName: String,
    var firstName: String,
    var address: Address,
    var datas: List<String>,
    var regDate: Date
)