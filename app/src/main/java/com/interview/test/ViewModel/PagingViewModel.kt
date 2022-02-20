package com.jobs.taskbook.ViewModels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.jobs.taskbook.Network.PageSource

import com.jobs.taskbook.Repository.PagingRepository
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.Flow

class PagingViewModel( private val pagingRepository: PagingRepository):ViewModel() {



    val passengers = Pager(PagingConfig(pageSize = 6)) {
        PageSource(pagingRepository)
    }.flow.cachedIn(viewModelScope)





}