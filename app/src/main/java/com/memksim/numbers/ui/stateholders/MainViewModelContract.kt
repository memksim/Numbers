package com.memksim.numbers.ui.stateholders

import com.memksim.numbers.model.Fact

interface MainViewModelContract {

    fun notifyDataChanged(data: Fact)

}