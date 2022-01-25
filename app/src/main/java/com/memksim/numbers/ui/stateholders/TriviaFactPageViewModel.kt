package com.memksim.numbers.ui.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.numbers.model.Fact
import com.memksim.numbers.model.repositories.NumbersRepository
import com.memksim.numbers.ui.state.TriviaFactPageState
import kotlin.random.Random

class TriviaFactPageViewModel: ViewModel(), TriviaFactViewModelContract {

    val repository: NumbersRepository = NumbersRepository()

    private val _liveData: MutableLiveData<TriviaFactPageState> by lazy{
        MutableLiveData<TriviaFactPageState>(
            TriviaFactPageState(Random.nextInt(0, 100)
            )
        )
    }
    var liveData: LiveData<TriviaFactPageState> = _liveData

    fun updateDigit(number: Int){
        val newState = TriviaFactPageState(
            number
        )
        _liveData.value = newState
        //комментарий для тестового коммита
    }

    fun increaseDigit(){
        val digit = _liveData.value!!.digit

        val newState = TriviaFactPageState(
            digit + 1,
            _liveData.value!!.fact
        )
        _liveData.value = newState
        repository.getTriviaFactAboutSpecificNumber(digit+1, this)
    }

    fun decreaseDigit(){
        val digit = _liveData.value!!.digit
        val newState = TriviaFactPageState(
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
        val newState = TriviaFactPageState(
            data.number.toInt(),
            data.text
        )
        _liveData.value = newState
    }

}