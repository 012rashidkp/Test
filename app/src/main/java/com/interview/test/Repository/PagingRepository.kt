package com.jobs.taskbook.Repository

import androidx.paging.PagingSource
import com.interview.test.Interface.ApiInterface
import com.interview.test.Interface.PagingInterface
import com.interview.test.Models.Result


class PagingRepository(private val apiInterface: ApiInterface):PagingInterface{
    override suspend fun getpagingdatas(page: Int): Result {
        return apiInterface.getpageitems(page)
    }

}