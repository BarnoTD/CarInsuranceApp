package com.example.insuranceapp

import androidx.annotation.DrawableRes

data class Policy(
    val imageResId: Int = 0,
    val name: String = "",
    val premium: Double = 0.0
)
