package com.example.thirtydaysrecipes.model

import androidx.annotation.DrawableRes

data class Recipe(
    val day: Int,
    val title: String,
    val shortDescription: String,
    val fullDescription: String,
    @DrawableRes val imageRes: Int
)