package com.example.ch2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch2.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding

    fun changeVisibility(mode: String) {
        if (mode == "login") {
            binding.run {
                tvAuthMain.text = "${MyApplication.email} 님 환영합니다."
                btnLogout.visibility = View.VISIBLE
                btnGoSignUp.visibility = View.GONE
                etAuthEmail.visibility = View.GONE
                etAuthPassword.visibility = View.GONE
                btnSignUp.visibility = View.GONE
                btnSignIn.visibility = View.GONE
            }
        } else if (mode == "logout") {
            binding.run {
                tvAuthMain.text = "로그인 하거나 회원가입 해주세요."
                btnLogout.visibility = View.GONE
                btnGoSignUp.visibility = View.VISIBLE
                etAuthEmail.visibility = View.VISIBLE
                etAuthPassword.visibility = View.VISIBLE
                btnSignUp.visibility = View.GONE
                btnSignIn.visibility = View.VISIBLE

            }
        } else if (mode == "signup") {
            binding.run {
                btnLogout.visibility = View.GONE
                btnGoSignUp.visibility = View.GONE
                etAuthEmail.visibility = View.VISIBLE
                etAuthPassword.visibility = View.VISIBLE
                btnSignUp.visibility = View.VISIBLE
                btnSignIn.visibility = View.GONE

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.tbAuth)
        //이액티비티의 초기상태
        if (MyApplication.checkAuth()) {
            changeVisibility("login")
        } else changeVisibility("logout")
        binding.btnLogout.setOnClickListener {
            MyApplication.auth.signOut()
            MyApplication.email = null
            changeVisibility("logout")
        }

        binding.btnGoSignUp.setOnClickListener {
            changeVisibility("signup")

        }

        binding.btnSignUp.setOnClickListener {
            val email: String = binding.etAuthEmail.text.toString()
            val password: String = binding.etAuthPassword.text.toString()
            MyApplication.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    //화면에 입력 된 데이터 지우고
                    binding.etAuthEmail.text.clear()
                    binding.etAuthPassword.text.clear()

                    if (task.isSuccessful) {
                        //서버 등록이 됐다면
                        MyApplication.auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener { sendTask ->
                                if (sendTask.isSuccessful) {
                                    Toast.makeText(
                                        baseContext,
                                        "회원가입이 성공했습니다. 전송된 이메일을 확인해주세요.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    changeVisibility("logout")
                                } else {
                                    Toast.makeText(baseContext, "메일 전송 실패", Toast.LENGTH_SHORT)
                                        .show()
                                    changeVisibility("logout")
                                }
                            }
                    } else {
                        Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        changeVisibility("logout")
                    }
                }
        }

        binding.btnSignIn.setOnClickListener {
            val email: String = binding.etAuthEmail.text.toString()
            val password: String = binding.etAuthPassword.text.toString()
            MyApplication.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    binding.etAuthPassword.text.clear()
                    binding.etAuthEmail.text.clear()
                    if (task.isSuccessful) {
                        if(MyApplication.checkAuth()) {
                            MyApplication.email = email
                            changeVisibility("login")
                        } else{
                            Toast.makeText(baseContext, "이메일 인증 실패", Toast.LENGTH_SHORT).show()
                        }
                    } else{
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }//
}//