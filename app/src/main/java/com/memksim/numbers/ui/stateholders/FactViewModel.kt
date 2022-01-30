package com.memksim.numbers.ui.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.numbers.model.Fact
import com.memksim.numbers.model.repositories.NumbersRepository
import com.memksim.numbers.ui.state.FactPageState
import kotlin.random.Random

class FactViewModel: ViewModel(), DataChangedCallback {

    val repository: NumbersRepository = NumbersRepository()

    private val _liveData: MutableLiveData<FactPageState> by lazy{
        MutableLiveData<FactPageState>(
            FactPageState(
                digit = Random.nextInt(0, 100)
            )
        )
    }
    var liveData: LiveData<FactPageState> = _liveData

    fun updateDigit(number: Int){
        val newState = FactPageState(
            digit = number
        )
        _liveData.value = newState
        //комментарий для тестового коммита
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

    fun decreaseDigit(){
        val digit = _liveData.value!!.digit
        val newState = FactPageState(
            digit = digit - 1,
            fact = _liveData.value!!.fact
        )
        _liveData.value = newState
        repository.getTriviaFactAboutSpecificNumber(digit-1, this)
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
                //TODO
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
                //TODO
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

}