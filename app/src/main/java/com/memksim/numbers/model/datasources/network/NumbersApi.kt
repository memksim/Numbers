package com.memksim.numbers.model.datasources.network

import com.memksim.numbers.model.Fact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NumbersApi {

    @GET("random/trivia?json")
    fun getTriviaFactAboutRandomNumber(): Call<Fact>

    @GET( "{number}/trivia?json")
    fun getTriviaFactAboutSpecificNumber(@Path("number" ) number: Int): Call<Fact>

    @GET("random/math?json")
    fun getMathFactAboutRandomNumber(): Call<Fact>

    @GET("{number}/math?json")
    fun getMathFactAboutSpecificNumber(@Path("number") number: Int): Call<Fact>
}