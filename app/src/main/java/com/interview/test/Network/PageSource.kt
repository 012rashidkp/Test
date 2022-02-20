package com.jobs.taskbook.Network

import androidx.paging.PagingSource
import com.interview.test.Models.Users
import com.jobs.taskbook.Repository.PagingRepository


class PageSource (private val pagingRepository: PagingRepository) : PagingSource<Int, Users>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Users> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = pagingRepository.getpagingdatas(nextPageNumber)
            System.out.println("yessss "+response)
            LoadResult.Page(
                    data = response.data,
                    prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                    nextKey = if (nextPageNumber < response.total) nextPageNumber + 1 else null
            )

        } catch (e: Exception) {
            System.out.println("error..."+e)
            LoadResult.Error(e)
        }
    }


}