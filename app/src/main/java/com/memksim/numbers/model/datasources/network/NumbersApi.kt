package com.memksim.numbers.model.datasources.network

import com.memksim.numbers.model.Fact
import retrofit2.Call
import retrofit2.http.GET

interface NumbersApi {

    @GET("random/trivia?json")
    fun getRandomFact(): Call<Fact>
}