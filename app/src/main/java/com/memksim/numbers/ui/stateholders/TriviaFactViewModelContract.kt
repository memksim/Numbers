package com.memksim.numbers.ui.stateholders

import com.memksim.numbers.model.Fact

interface TriviaFactViewModelContract {

    fun notifyDataChanged(data: Fact)

}