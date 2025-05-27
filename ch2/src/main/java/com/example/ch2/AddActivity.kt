package com.example.ch2

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch2.databinding.ActivityAddBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.tb)


    }//

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (binding.etAdd.text.isNotEmpty()) {
            //저장하기 위한 데이터
            val data = mapOf(
                "email" to MyApplication.email,
                "content" to binding.etAdd.text.toString(),
                "date" to SimpleDateFormat("yyyy-MM-dd").format(Date())
            )
            //db에 저장
            MyApplication.db.collection("news")
                .add(data)
                .addOnSuccessListener {
                    Toast.makeText(this, "데이터가 추가되었습니다.", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Log.d("EJ", "data save error...$it")
                }
            return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}

