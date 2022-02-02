package com.memksim.numbers.model.repositories

import android.util.Log
import com.memksim.numbers.TAG
import com.memksim.numbers.model.Fact
import com.memksim.numbers.model.datasources.network.NumbersApi
import com.memksim.numbers.model.datasources.network.NumbersClient
import com.memksim.numbers.ui.stateholders.DataChangedCallback
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NumbersRepository {

    private val disposableBag = CompositeDisposable()

    fun clearBag(){
        disposableBag.dispose()
        Log.d(TAG, "clearBag: cleared - ${disposableBag.size()}")
    }

    fun getTriviaFactAboutRandomNumber(callback: DataChangedCallback) {
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        disposableBag.add(api.getTriviaFactAboutRandomNumber()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.notifyDataChanged(it)
            },{
                callback.notifyDataChanged(
                    Fact(
                        it.toString(),
                        false,
                        (-1.0).toFloat()
                    )
                )
            })
        )


        /*data.enqueue(object : Callback<Fact>{

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

        })*/
    }

    fun getTriviaFactAboutSpecificNumber(number: Int, callback: DataChangedCallback){
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        disposableBag.add(api.getTriviaFactAboutSpecificNumber(number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.notifyDataChanged(it)
            },{
                callback.notifyDataChanged(
                    Fact(
                        it.toString(),
                        false,
                        (-1.0).toFloat()
                    )
                )
            })
        )
    }

    fun getMathFactAboutRandomNumber(callback: DataChangedCallback){
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        disposableBag.add(api.getMathFactAboutRandomNumber()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.notifyDataChanged(it)
            },{
                callback.notifyDataChanged(
                    Fact(
                        it.toString(),
                        false,
                        (-1.0).toFloat()
                    )
                )
            })
        )
    }

    fun getMathFactAboutSpecificNumber(number: Int, callback: DataChangedCallback){
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        disposableBag.add(api.getMathFactAboutSpecificNumber(number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.notifyDataChanged(it)
            },{
                callback.notifyDataChanged(
                    Fact(
                        it.toString(),
                        false,
                        (-1.0).toFloat()
                    )
                )
            })
        )
    }

    fun getDateFactAboutRandomDate(callback: DataChangedCallback){var fact: Fact?
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        disposableBag.add(api.getDateFactAboutRandomDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.notifyDataChanged(it)
            },{
                callback.notifyDataChanged(
                    Fact(
                        it.toString(),
                        false,
                        (-1.0).toFloat()
                    )
                )
            })
        )
    }

    fun getDateFactAboutSpecificDate(
        callback: DataChangedCallback,
        month: Int,
        day: Int
        ){
        val api = NumbersClient.getClient().create(NumbersApi::class.java)

        disposableBag.add(api.getDateFactAboutSpecificDate(month, day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.notifyDataChanged(it.text, day, month)
            },{
                callback.notifyDataChanged(
                    Fact(
                        it.toString(),
                        false,
                        (-1.0).toFloat()
                    )
                )
            })
        )

    }

    //треш
    /*private fun parseDate(date: Int): Array<Int>{
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
*/
}