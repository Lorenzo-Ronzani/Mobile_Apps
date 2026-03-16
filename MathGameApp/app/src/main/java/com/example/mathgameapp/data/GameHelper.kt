
package com.example.mathgameapp.data

import com.example.mathgameapp.model.Question

object GameHelper {

    fun generateQuestions(totalQuestions: Int): List<Question> {
        val questions = mutableListOf<Question>()

        repeat(totalQuestions) {
            val firstNumber = (1..20).random()
            val secondNumber = (1..20).random()

            questions.add(
                Question(
                    number1 = firstNumber,
                    number2 = secondNumber
                )
            )
        }

        return questions
    }
}