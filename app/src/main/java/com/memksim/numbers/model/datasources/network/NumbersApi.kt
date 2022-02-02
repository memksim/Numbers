package com.memksim.numbers.model.datasources.network

import com.memksim.numbers.model.Fact
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NumbersApi {

    @GET("random/trivia?json")
    fun getTriviaFactAboutRandomNumber(): Single<Fact>

    @GET( "{number}/trivia?json")
    fun getTriviaFactAboutSpecificNumber(@Path("number" ) number: Int): Single<Fact>

    @GET("random/math?json")
    fun getMathFactAboutRandomNumber(): Single<Fact>

    @GET("{number}/math?json")
    fun getMathFactAboutSpecificNumber(@Path("number") number: Int): Single<Fact>

    @GET("random/date?json")
    fun getDateFactAboutRandomDate(): Single<Fact>

    @GET("{month}/{day}/date?json")
    fun getDateFactAboutSpecificDate(@Path("month") month: Int, @Path("day") day: Int): Single<Fact>
}