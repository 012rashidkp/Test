package com.interview.test.Interface

import com.interview.test.Models.Result

interface PagingInterface {
    suspend fun getpagingdatas(page:Int): Result
}