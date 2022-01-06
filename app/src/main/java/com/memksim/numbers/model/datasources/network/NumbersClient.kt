package com.memksim.numbers.model.datasources.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NumbersClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl("http://numbersapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!
    }

}