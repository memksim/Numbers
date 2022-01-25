package com.memksim.numbers.model.repositories

import android.util.Log
import com.memksim.numbers.TAG
import com.memksim.numbers.model.Fact
import com.memksim.numbers.model.datasources.network.NumbersApi
import com.memksim.numbers.model.datasources.network.NumbersClient
import com.memksim.numbers.ui.stateholders.TriviaFactViewModelContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NumbersRepository {

    fun getTriviaFactAboutRandomNumber(callback: TriviaFactViewModelContract){
        var fact: Fact?
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        val data = api.getTriviaFactAboutRandomNumber()
        data.enqueue(object : Callback<Fact>{

            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                fact = response.body()
                callback.notifyDataChanged(fact!!)
                Log.d(TAG, "onResponse: ${fact!!}")
            }

            override fun onFailure(call: Call<Fact>, t: Throwable) {
                callback.notifyDataChanged(
                    Fact(
                    t.toString(),
                    false,
                    (-1.0).toFloat()
                    )
                )
                Log.d(TAG, "onFailure: $t")
            }

        })
    }

    fun getTriviaFactAboutSpecificNumber(number: Int, callback: TriviaFactViewModelContract){
        var fact: Fact?
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        val data = api.getTriviaFactAboutSpecificNumber(number = number)
        data.enqueue(object : Callback<Fact>{

            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                fact = response.body()
                callback.notifyDataChanged(fact!!)
                Log.d(TAG, "onResponse: ${fact!!}")
            }

            override fun onFailure(call: Call<Fact>, t: Throwable) {
                callback.notifyDataChanged(
                    Fact(
                        t.toString(),
                        false,
                        (-1.0).toFloat()
                    )
                )
                Log.d(TAG, "onFailure: $t")
            }
        })
    }

}