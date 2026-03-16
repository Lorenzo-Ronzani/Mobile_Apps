

package com.example.mathgameapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.mathgameapp.data.GameHelper
import com.example.mathgameapp.model.Question
import com.example.mathgameapp.pages.QuestionScreen
import com.example.mathgameapp.pages.ResultScreen
import com.example.mathgameapp.pages.StartScreen

enum class ScreenState {
    START,
    QUESTION,
    RESULT
}

@Composable
fun MathGameApp() {
    var currentScreen by remember { mutableStateOf(ScreenState.START) }

    var questionAmount by remember { mutableStateOf("") }
    var questions by remember { mutableStateOf(listOf<Question>()) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var userAnswer by remember { mutableStateOf("") }
    var correctAnswers by remember { mutableStateOf(0) }
    var wrongAnswers by remember { mutableStateOf(0) }

    fun resetGame() {
        questionAmount = ""
        questions = emptyList()
        currentQuestionIndex = 0
        userAnswer = ""
        correctAnswers = 0
        wrongAnswers = 0
        currentScreen = ScreenState.START
    }

    fun startGame() {
        val totalQuestions = questionAmount.toIntOrNull()

        if (totalQuestions != null && totalQuestions > 0) {
            questions = GameHelper.generateQuestions(totalQuestions)
            currentQuestionIndex = 0
            userAnswer = ""
            correctAnswers = 0
            wrongAnswers = 0
            currentScreen = ScreenState.QUESTION
        }
    }

    fun checkAnswerAndContinue() {
        val currentQuestion = questions[currentQuestionIndex]
        val answer = userAnswer.toIntOrNull()

        if (answer == currentQuestion.getCorrectAnswer()) {
            correctAnswers++
        } else {
            wrongAnswers++
        }

        if (currentQuestionIndex < questions.lastIndex) {
            currentQuestionIndex++
            userAnswer = ""
        } else {
            currentScreen = ScreenState.RESULT
        }
    }

    when (currentScreen) {
        ScreenState.START -> {
            StartScreen(
                questionAmount = questionAmount,
                onQuestionAmountChange = { questionAmount = it },
                onStartClick = { startGame() }
            )
        }

        ScreenState.QUESTION -> {
            QuestionScreen(
                question = questions[currentQuestionIndex],
                currentQuestionNumber = currentQuestionIndex + 1,
                totalQuestions = questions.size,
                userAnswer = userAnswer,
                onUserAnswerChange = { userAnswer = it },
                correctAnswers = correctAnswers,
                wrongAnswers = wrongAnswers,
                onNextClick = {
                    if (userAnswer.isNotBlank()) {
                        checkAnswerAndContinue()
                    }
                },
                onCancelClick = {
                    resetGame()
                }
            )
        }

        ScreenState.RESULT -> {
            ResultScreen(
                totalQuestions = questions.size,
                correctAnswers = correctAnswers,
                wrongAnswers = wrongAnswers,
                onPlayAgainClick = {
                    resetGame()
                }
            )
        }
    }
}