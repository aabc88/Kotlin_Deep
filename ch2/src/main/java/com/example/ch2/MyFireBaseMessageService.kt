package com.example.ch2

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

//fv0tKZ_lSq-lLoZuQGDOYS:APA91bEOTyHx-q96ifB9dpIV8vfiHaG-7rxZK4rSOe5t7WH3768Ajy7z01Gl_yq39lz1AozxFpD74aKDvDjQ4CfGWJebGpTRKYhBNzd2MZKchVNhbkpw1w4

class MyFireBaseMessageService : FirebaseMessagingService() {
    //앱 인스톨 되자마자 fcm 서버가 전달하는 토큰을 전달하기 위해 자동 호출.
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //적정하게 back-end에 토큰 전달
        Log.d("EJ", "fcm token : $token")

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("EJ", "fcm message : ${message.data}")
    }
}