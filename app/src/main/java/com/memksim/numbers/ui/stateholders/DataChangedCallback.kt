package com.memksim.numbers.ui.stateholders

import com.memksim.numbers.model.Fact

interface DataChangedCallback {

    fun notifyDataChanged(data: Fact)

    fun notifyDataChanged(
        fact: String,
        day: Int,
        month: Int
    )

}