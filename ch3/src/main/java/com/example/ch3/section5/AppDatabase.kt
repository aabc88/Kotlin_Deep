package com.example.ch3.section5

import androidx.room.Database
import androidx.room.RoomDatabase


//Entity, Dao등은 필요에 의해 여러개 선언
//DataBase 클래스 선언은 한번만
@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO
}