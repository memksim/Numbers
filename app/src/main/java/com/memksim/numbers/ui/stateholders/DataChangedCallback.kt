package com.memksim.numbers.ui.stateholders

import com.memksim.numbers.model.Fact

interface DataChangedCallback {

    fun notifyDataChanged(data: Fact)

}