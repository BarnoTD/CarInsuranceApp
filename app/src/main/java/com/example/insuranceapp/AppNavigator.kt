package com.example.insuranceapp

import AuthViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun AppNavigator(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Auth.route) {
        composable(Screen.Auth.route) {
            AuthScreen(authViewModel, navController)
        }
        composable(Screen.Policies.route) {
            val policiesViewModel: PoliciesViewModel = viewModel()
            PoliciesScreen(navController, policiesViewModel, authViewModel)
        }
        composable(
            route = "${Screen.Purchase.route}/{policyJsonString}",
            arguments = listOf(navArgument("policyJsonString") { type = NavType.StringType })
        ) { backStackEntry ->
            val policyJsonString = backStackEntry.arguments?.getString("policyJsonString")
            if (policyJsonString != null) {
                val policiesViewModel: PoliciesViewModel = viewModel()
                PurchaseScreen(navController, policyJsonString, policiesViewModel)
            }
        }
    }
}