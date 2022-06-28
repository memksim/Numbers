package com.memksim.numbers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.numbers.model.Fact
import com.memksim.numbers.model.repositories.NumbersRepository
import com.memksim.numbers.viewmodel.FactPageState
import kotlin.random.Random

interface DataChangedCallback {

    fun notifyDataChanged(data: Fact)

    fun notifyDataChanged(
        fact: String,
        day: Int,
        month: Int
    )

}

class FactViewModel: ViewModel(), DataChangedCallback {

    val repository: NumbersRepository = NumbersRepository()

    private val _liveData: MutableLiveData<FactPageState> by lazy{
        MutableLiveData<FactPageState>(
            FactPageState(
                digit = Random.nextInt(0, 100),
                month = Random.nextInt(1, 12),
                day = Random.nextInt(1, 28)
            )
        )
    }
    var liveData: LiveData<FactPageState> = _liveData

    fun updateDigit(number: Int){
        val newState = FactPageState(
            digit = number
        )
        _liveData.value = newState
    }

    fun updateDate(
        month: Int = _liveData.value!!.month,
        day: Int = _liveData.value!!.day
    ){
        val newState = FactPageState(
            month = month,
            day = day
        )
        _liveData.value = newState
    }

    fun increaseDigit(){
        val digit = _liveData.value!!.digit

        val newState = FactPageState(
            digit = digit + 1,
            fact = _liveData.value!!.fact
        )
        _liveData.value = newState
        repository.getTriviaFactAboutSpecificNumber(digit+1, this)
    }

    fun increaseDay(){
        var day = _liveData.value!!.day
        var month = _liveData.value!!.month

        when(month){
            1 ->{
                if(day == 31){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            2 ->{
                if(day == 29){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            3 ->{
                if(day == 31){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            4 ->{
                if(day == 30){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            5 ->{
                if(day == 31){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            6 ->{
                if(day == 30){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            7 ->{
                if(day == 31){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            8 ->{
                if(day == 31){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            9 ->{
                if(day == 30){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            10 ->{
                if(day == 31){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            11 ->{
                if(day == 30){
                    month++
                    day = 1
                }else{
                    day++
                }
            }
            12 ->{
                if(day == 31){
                    month = 1
                    day = 1
                }else{
                    day++
                }
            }

        }

        val newState = FactPageState(
            day = day,
            month = month
        )

        _liveData.value = newState
        getSpecificFact(2)
    }

    fun decreaseDigit(){
        val digit = _liveData.value!!.digit
        val newState = FactPageState(
            digit = digit - 1,
            fact = _liveData.value!!.fact
        )
        _liveData.value = newState
        repository.getTriviaFactAboutSpecificNumber(digit-1, this)
    }

    fun decreaseDay(){
        var day = _liveData.value!!.day
        var month = _liveData.value!!.month

        when(month){
            1 ->{
                if(day == 1){
                    month = 12
                    day = 31
                }else{
                    day--
                }
            }
            2 ->{
                if(day == 1){
                    month--
                    day = 31
                }else{
                    day--
                }
            }
            3 ->{
                if(day == 1){
                    month--
                    day = 29
                }else{
                    day--
                }
            }
            4 ->{
                if(day == 1){
                    month--
                    day = 31
                }else{
                    day--
                }
            }
            5 ->{
                if(day == 1){
                    month--
                    day = 30
                }else{
                    day--
                }
            }
            6 ->{
                if(day == 1){
                    month--
                    day = 31
                }else{
                    day--
                }
            }
            7 ->{
                if(day == 1){
                    month--
                    day = 30
                }else{
                    day--
                }
            }
            8 ->{
                if(day == 1){
                    month--
                    day = 31
                }else{
                    day--
                }
            }
            9 ->{
                if(day == 1){
                    month--
                    day = 31
                }else{
                    day--
                }
            }
            10 ->{
                if(day == 1){
                    month--
                    day = 30
                }else{
                    day--
                }
            }
            11 ->{
                if(day == 1){
                    month--
                    day = 31
                }else{
                    day--
                }
            }
            12 ->{
                if(day == 1){
                    month--
                    day = 30
                }else{
                    day--
                }
            }

        }

        val newState = FactPageState(
            day = day,
            month = month
        )

        _liveData.value = newState
        getSpecificFact(2)
    }

    /*0 - random fact
    * 1 - math fact
    * 2 - date fact*/
    fun getRandomFact(
        requestCode: Int,
    ){
        when(requestCode){
            0 ->{
                repository.getTriviaFactAboutRandomNumber(this)
            }
            1 ->{
                repository.getMathFactAboutRandomNumber(this)
            }
            2 ->{
                repository.getDateFactAboutRandomDate(this)
            }
        }

    }

    fun getSpecificFact(
        requestCode: Int,
        month: Int = _liveData.value?.month ?: 1,
        day: Int = _liveData.value?.day ?: 1,
        number: Int = _liveData.value?.digit ?: 0
    ){
        when(requestCode){
            0 ->{
                repository.getTriviaFactAboutSpecificNumber(number, this)
            }
            1 ->{
                repository.getMathFactAboutSpecificNumber(number, this)
            }
            2 ->{
                repository.getDateFactAboutSpecificDate(this, month, day)
            }
        }

    }



    override fun notifyDataChanged(data: Fact) {
        val newState = FactPageState(
            digit = data.number.toInt(),
            fact = data.text,
        )
        _liveData.value = newState
    }

    override fun notifyDataChanged(fact: String, day: Int, month: Int) {
        val newState = FactPageState(
            fact = fact,
            day = day,
            month = month
        )
        _liveData.value = newState
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearBag()
    }

}