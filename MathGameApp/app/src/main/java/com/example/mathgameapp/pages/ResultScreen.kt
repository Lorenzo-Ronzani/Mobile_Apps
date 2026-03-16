package com.example.mathgameapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    totalQuestions: Int,
    correctAnswers: Int,
    wrongAnswers: Int,
    onPlayAgainClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Game Result",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Total questions: $totalQuestions")
        Text(text = "Correct answers: $correctAnswers")
        Text(text = "Wrong answers: $wrongAnswers")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onPlayAgainClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Play Again")
        }
    }
}