package com.memksim.numbers.viewmodel

data class FactPageState(
    val month: Int = 1,
    val day: Int = 1,
    val digit: Int = 0,
    val fact: String = ""
)
