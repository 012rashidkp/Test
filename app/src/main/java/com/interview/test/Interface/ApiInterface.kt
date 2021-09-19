package com.interview.test.Interface

import com.interview.test.Models.Result
import com.interview.test.Network.ServerUrls.users
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(users)
    suspend  fun getusersfromApi():Result

}