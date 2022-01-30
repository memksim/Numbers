package com.memksim.numbers.model.repositories

import android.util.Log
import com.memksim.numbers.TAG
import com.memksim.numbers.model.Fact
import com.memksim.numbers.model.datasources.network.NumbersApi
import com.memksim.numbers.model.datasources.network.NumbersClient
import com.memksim.numbers.ui.stateholders.DataChangedCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NumbersRepository {

    fun getTriviaFactAboutRandomNumber(callback: DataChangedCallback){
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

    fun getTriviaFactAboutSpecificNumber(number: Int, callback: DataChangedCallback){
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

    fun getMathFactAboutRandomNumber(callback: DataChangedCallback){
        var fact: Fact?
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        val data = api.getMathFactAboutRandomNumber()
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

    fun getMathFactAboutSpecificNumber(number: Int, callback: DataChangedCallback){
        var fact: Fact?
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        val data = api.getMathFactAboutSpecificNumber(number = number)
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

    fun getDateFactAboutRandomDate(callback: DataChangedCallback){
        var fact: Fact?
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        val data = api.getDateFactAboutRandomDate()
        data.enqueue(object : Callback<Fact>{

            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                fact = response.body()

                val date = parseDate(fact!!.number.toInt())

                callback.notifyDataChanged(fact!!.text, date[1], date[0])
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

    fun getDateFactAboutSpecificDate(
        callback: DataChangedCallback,
        month: Int,
        day: Int
        ){
        var fact: Fact?
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        val data = api.getDateFactAboutSpecificDate(
            month = month,
            day = day
        )
        data.enqueue(object : Callback<Fact>{

            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                fact = response.body()
                callback.notifyDataChanged(fact!!.text, day, month)
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

    private fun parseDate(date: Int): Array<Int>{
        var month = 1
        var day = date

        //Jan
        if((day-31)>0){
            day -= 31
            month++

            //Feb
            if((day-29)>0){
                day -= 29
                month++

                //March
                if((day - 31)>0){
                    day -= 31
                    month++

                    //Apr
                    if((day - 30)>0){
                        day -= 30
                        month++

                        //May
                        if((day - 31)>0){
                            day -= 31
                            month++

                            //Jun
                            if((day - 30)>0){
                                day -= 30
                                month++

                                //Jul
                                if((day - 31)>0){
                                    day -= 31
                                    month++

                                    //Aug
                                    if((day - 31)>0){
                                        day -= 31
                                        month++

                                        //Sep
                                        if((day - 30)>0){
                                            day -= 30
                                            month++

                                            //Oct
                                            if((day - 31)>0){
                                                day -= 31
                                                month++

                                                //Nov
                                                if((day - 30)>0){
                                                    day -= 30
                                                    month++


                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return arrayOf(month, day)
    }

}