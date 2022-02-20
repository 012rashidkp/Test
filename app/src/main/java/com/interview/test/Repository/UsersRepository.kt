package com.interview.test.Repository

import com.interview.test.Network.Apihelper

class UsersRepository(private val apihelper: Apihelper){
   suspend fun getapi(page:Int)=apihelper.getusers(page)

}