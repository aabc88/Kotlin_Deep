package com.example.ch3.section5

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import java.util.Date

//Entity의 기초타입이 아닌 데이터의 매핑 방법을 명시
//이 클래스를 Appdatabase에 등록, Room에서 개발자가 만든 매핑 규칙을 이용할 수 있게,
class Converter {
    @TypeConverter
    //annotation
    //함수명의 의미가 없다. 타입 문제임으로 매개변수타입과 리턴타입을 보고 어느 converter를 사용할지 결정.
    //List<String>형태의 데이터를 String(json)으로 변환
    //room내부에서 데이터를 json으로 만들어 분석해서 db에 저장하거나 획득
    fun fromListToString(value: List<String>?): String {

        return Gson().toJson(value)
    }
    //위는 : insert
    //아래는 : select
    @TypeConverter
    fun fromStringToList(value: String?): List<String> {
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun fromTimeToDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun fromDateToTime(value: Date?): Long? {
        return value?.time
    }

    @TypeConverter
    fun fromStringToAddress(value: String?): Address {
        return Gson().fromJson(value, Address::class.java)
    }

    @TypeConverter
    fun fromAddressToString(value: Address): String {
        return Gson().toJson(value)
    }
}