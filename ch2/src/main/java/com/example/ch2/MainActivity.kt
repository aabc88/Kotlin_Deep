package com.example.ch2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.tbMain)

        binding.fabAdd.setOnClickListener {
            if (MyApplication.checkAuth()) {
                startActivity(Intent(this, AddActivity::class.java))
            } else {
                Toast.makeText(this, "로그인을 먼저 진행해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }//

    override fun onStart() {
        super.onStart()
        if (!MyApplication.checkAuth()) {
            binding.tvLogin.visibility = View.VISIBLE
            binding.rcMain.visibility = View.GONE
        } else {
            binding.tvLogin.visibility = View.GONE
            binding.rcMain.visibility = View.VISIBLE
            makeRecyclerView()
        }
    }

    private fun makeRecyclerView() {
        MyApplication.db.collection("news")
            .get()
            .addOnSuccessListener { result ->
                val itemList = mutableListOf<ItemData>()
                for (document in result) {
                    //문서 하나를 개발자 객체로
                    val item = document.toObject(ItemData::class.java)
                    //문서의 id는 자동 할당 되어있다.
                    item.docId = document.id
                    itemList.add(item)
                }
                binding.rcMain.layoutManager = LinearLayoutManager(this)
                binding.rcMain.adapter = MyAdapter(this, itemList)
                binding.rcMain.addItemDecoration(
                    DividerItemDecoration(
                        this,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
            .addOnFailureListener { exception ->
                Log.d("EJ", "error $exception")
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, AuthActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
}//