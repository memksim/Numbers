package com.memksim.numbers.ui.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.numbers.model.Fact
import com.memksim.numbers.model.repositories.NumbersRepository

class MainPageViewModel: ViewModel() {

    val repository: NumbersRepository = NumbersRepository()

    private val _liveData: MutableLiveData<Fact> = MutableLiveData()
    var liveData: LiveData<Fact> = _liveData

    fun getRandomFact(){
        _liveData.value = repository.getRandomFact()
    }

}