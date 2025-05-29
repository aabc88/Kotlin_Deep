package com.example.ch3.section5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.ch3.R
import com.example.ch3.databinding.ActivityFragmentTestBinding
import com.example.ch3.databinding.ActivityRoomTestBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class RoomTestActivity : AppCompatActivity(), CoroutineScope {
    lateinit var dao: UserDAO
    lateinit var job: Job
    lateinit var binding: ActivityRoomTestBinding

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRoomTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        job = Job()

        //database초기화 앱 내에서 한번 원래는 application 클래스
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-name"
        )
            //room을 이용한 dbms작업은 가급적 background thread에 의해 실행을 권장
            //main Thread에 의해 실행됨을 허용하라.
            .allowMainThreadQueries()
            .build()

        //필요한곳에서 dao획득하여 함수호출해서 원하는 dbms실행
        dao = db.userDao()

        binding.btn1.setOnClickListener {
            val user1 = User(0, "ej", "hong")
            val user2 = User(0, "sc", "lee")
            val user3 = User(0, "gs", "kim")

            dao.insertUser(user1)
            dao.insertUser(user2)
            dao.insertUser(user3)

            var resultTxt = ""
            dao.getAll().forEach {
                resultTxt+="$it \n"
            }
            binding.tvResult.text = resultTxt
        }
    }
}