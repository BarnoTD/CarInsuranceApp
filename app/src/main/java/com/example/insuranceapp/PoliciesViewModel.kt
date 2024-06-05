package com.example.insuranceapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PoliciesViewModel : ViewModel() {
    private val repository = FirestoreRepository()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _purchasedPolicies = MutableStateFlow<List<Policy>>(emptyList())
    val purchasedPolicies: StateFlow<List<Policy>> = _purchasedPolicies

    init {
        fetchPurchasedPolicies()
    }

    private fun fetchPurchasedPolicies() {
        val userId = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            repository.getPurchasedPolicies(userId).collect { policies ->
                _purchasedPolicies.value = policies
                Log.d("PoliciesViewModel", "Updated purchased policies: $policies")
            }
        }
    }

    fun purchasePolicy(policy: Policy) {
        val userId = auth.currentUser?.uid ?: return
        repository.purchasePolicy(userId, policy)
    }

    fun logout(navController: NavController) {
        viewModelScope.launch {
            auth.signOut()
            navController.navigate(Screen.Auth.route) {
                popUpTo(Screen.Policies.route) { inclusive = true }
            }
        }
    }
}


