package com.memksim.numbers.ui.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.numbers.model.Fact
import com.memksim.numbers.model.repositories.NumbersRepository
import com.memksim.numbers.ui.state.MainPageState
import kotlin.random.Random

class MainPageViewModel: ViewModel(), MainViewModelContract {

    val repository: NumbersRepository = NumbersRepository()

    private val _liveData: MutableLiveData<MainPageState> by lazy{
        MutableLiveData<MainPageState>(
            MainPageState(Random.nextInt(0, 100)
            )
        )
    }
    var liveData: LiveData<MainPageState> = _liveData

    fun updateDigit(number: Int){
        val newState = MainPageState(
            number
        )
        _liveData.value = newState
    }

    fun increaseDigit(){
        val digit = _liveData.value!!.digit

        val newState = MainPageState(
            digit + 1,
            _liveData.value!!.fact
        )
        _liveData.value = newState
        repository.getTriviaFactAboutSpecificNumber(digit+1, this)
    }

    fun decreaseDigit(){
        val digit = _liveData.value!!.digit
        val newState = MainPageState(
            digit - 1,
            _liveData.value!!.fact
        )
        _liveData.value = newState
        repository.getTriviaFactAboutSpecificNumber(digit-1, this)
    }

    fun getRandomFact(){
        repository.getTriviaFactAboutRandomNumber(this)
    }

    fun getSpecificFact(number: Int){
        repository.getTriviaFactAboutSpecificNumber(number, this)
    }



    override fun notifyDataChanged(data: Fact) {
        val newState = MainPageState(
            data.number.toInt(),
            data.text
        )
        _liveData.value = newState
    }

}