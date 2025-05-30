package com.example.ch3.mission2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ch3.mission2.AppApplication
import com.example.ch3.mission2.repository.SearchRepository
import com.example.ch3.mission2.room.Search
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val appApplication = application as AppApplication
    val searchRepository: SearchRepository = SearchRepository(appApplication.db)

    //데이터 발행을 flow로 하고 싶다.
    val searchFlow = MutableStateFlow(listOf<Search>())

    fun insertSearch(search: Search) {
        viewModelScope.launch {
            searchRepository.insertSearch(search)
        }
    }

    fun getAllSearch() {
        viewModelScope.launch {
            //이 위치는 main Thread
            val results = withContext(Dispatchers.IO) {
                searchRepository.getAllSearch()
            }

            searchFlow.value = results
        }
    }

}