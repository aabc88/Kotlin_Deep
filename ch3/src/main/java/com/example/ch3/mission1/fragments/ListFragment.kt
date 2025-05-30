package com.example.ch3.mission1.fragments

import com.example.ch3.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch3.databinding.FragmentListBinding
import com.example.ch3.mission1.model.Item
import com.example.ch3.mission1.recyclerview.ItemAdapter
import com.example.ch3.mission1.util.getDate
import com.example.ch3.mission1.viewmodel.NewsViewModel
import retrofit2.http.Query

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    lateinit var searchView: SearchView
    val viewModel: NewsViewModel by viewModels()
    val datas = mutableListOf<Item>()
    lateinit var itemAdapter: ItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        itemAdapter = ItemAdapter(activity as Context, datas)
        binding.recycler.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = itemAdapter
            getDate("travel")
        }
        return binding.root
    }

    private fun getData(query: String) {
        //B/L이 실행되어야 한다. viewmodel에 일만 시키자
        viewModel.getNews(query).observe(viewLifecycleOwner) {
            datas.clear()
            datas.addAll(it)
            itemAdapter.notifyDataSetChanged()
        }
    }

    //onCreateView()가 호출되어 fragment의 화면이 결정 된 후 자동호출
    //매개변수 (view)가 onCreateView 에러 리턴시킨 객체
    //view에 대한 디테일 작업을 보통 이 함수에서 구현함
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //activity에서 작성했던 메뉴 구성을 fragment에서 한다.
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(
                menu: Menu,
                menuInflater: MenuInflater
            ) {
                menuInflater.inflate(R.menu.list_menu, menu)
                searchView = menu.findItem(R.id.menu_search).actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        getData(query ?: "travel")
                        return false
                    }

                    override fun onQueryTextChange(query: String?): Boolean {
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        })
    }
}