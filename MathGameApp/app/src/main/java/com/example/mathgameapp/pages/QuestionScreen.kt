package com.example.mathgameapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mathgameapp.model.Question

@Composable
fun QuestionScreen(
    question: Question,
    currentQuestionNumber: Int,
    totalQuestions: Int,
    userAnswer: String,
    onUserAnswerChange: (String) -> Unit,
    correctAnswers: Int,
    wrongAnswers: Int,
    onNextClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Correct: $correctAnswers")
            Text(text = "Wrong: $wrongAnswers")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Question $currentQuestionNumber of $totalQuestions",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "${question.number1} + ${question.number2} = ?",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = userAnswer,
            onValueChange = { value ->
                if (value.all { it.isDigit() }) {
                    onUserAnswerChange(value)
                }
            },
            label = { Text("Your answer") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onNextClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onCancelClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}