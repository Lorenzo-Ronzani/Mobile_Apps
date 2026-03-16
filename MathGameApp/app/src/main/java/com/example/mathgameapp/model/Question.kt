
package com.example.mathgameapp.model

data class Question(
    val number1: Int,
    val number2: Int
) {
    fun getCorrectAnswer(): Int {
        return number1 + number2
    }
}