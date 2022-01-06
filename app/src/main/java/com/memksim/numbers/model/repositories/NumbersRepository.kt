package com.memksim.numbers.model.repositories

import android.util.Log
import com.memksim.numbers.TAG
import com.memksim.numbers.model.Fact
import com.memksim.numbers.model.datasources.network.NumbersApi
import com.memksim.numbers.model.datasources.network.NumbersClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class NumbersRepository {

    fun getRandomFact(): Fact? {
        var fact: Fact? = null
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        val data = api.getRandomFact()
        data.enqueue(object : Callback<Fact>{

            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                fact = response.body()

                Log.d(TAG, "onResponse: ${fact!!.text}")
            }

            override fun onFailure(call: Call<Fact>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })

        return fact

    }

}