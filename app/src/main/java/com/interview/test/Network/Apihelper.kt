package com.interview.test.Network

import com.interview.test.Interface.ApiInterface

class Apihelper (private val apiInterface: ApiInterface){
    suspend fun getusers(page:Int)=apiInterface.getpageitems(page)
}