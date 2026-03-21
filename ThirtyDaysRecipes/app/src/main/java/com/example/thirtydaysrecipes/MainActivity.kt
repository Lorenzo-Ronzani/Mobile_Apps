package com.example.thirtydaysrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.thirtydaysrecipes.pages.RecipeApp
import com.example.thirtydaysrecipes.ui.theme.ThirtyDaysRecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysRecipesTheme {
                RecipeApp()
            }
        }
    }
}