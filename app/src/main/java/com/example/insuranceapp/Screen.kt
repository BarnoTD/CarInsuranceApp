package com.example.insuranceapp

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Policies : Screen("policies")
    object Purchase : Screen("purchase")
}