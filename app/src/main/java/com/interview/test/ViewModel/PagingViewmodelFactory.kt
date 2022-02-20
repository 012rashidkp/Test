package com.jobs.taskbook.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jobs.taskbook.Repository.PagingRepository


class PagingViewmodelFactory (private val pagingRepository: PagingRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PagingViewModel(pagingRepository) as T
    }

}