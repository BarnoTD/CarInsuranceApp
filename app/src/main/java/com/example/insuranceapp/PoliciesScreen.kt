package com.example.insuranceapp

import AuthViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson

@Composable
fun PolicyCard(policy: String) {
    Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = policy)
        }
    }
}

@Composable
fun PoliciesScreen(navController: NavController, policiesViewModel: PoliciesViewModel, authViewModel: AuthViewModel) {
    val purchasedPolicies by policiesViewModel.purchasedPolicies.collectAsState()
    val availablePolicies = PoliciesRepository.availablePolicies

    Column {
        Text(text = "Purchased Policies:")
        LazyColumn {
            items(purchasedPolicies) { policy ->
                PolicyCard(policy.name)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Available Policies:")
        availablePolicies.forEach { policy ->
            Button(onClick = {
                policiesViewModel.purchasePolicy(policy)
            }) {
                Text(text = policy.name)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            authViewModel.logout()
            navController.navigate(Screen.Auth.route) {
                popUpTo(Screen.Policies.route) { inclusive = true }
            }
        }) {
            Text(text = "Logout")
        }
    }
}
