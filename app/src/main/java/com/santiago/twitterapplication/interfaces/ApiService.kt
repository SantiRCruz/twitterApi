package com.santiago.twitterapplication.interfaces

import com.santiago.twitterapplication.models.Create
import com.santiago.twitterapplication.models.users.Users
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getListUsers(@Query("page")page:Int): Call<Users>

    @POST("users")
    fun create(@Body create: Create):Call<Create>


}