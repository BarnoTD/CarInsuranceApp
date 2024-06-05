package com.example.insuranceapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FirestoreRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val policiesCollection = firestore.collection("policies")

    fun getPurchasedPolicies(userId: String): StateFlow<List<Policy>> {
        val policiesFlow = MutableStateFlow<List<Policy>>(emptyList())
        policiesCollection.whereEqualTo("userId", userId).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("FirestoreRepository", "Listen failed.", exception)
                return@addSnapshotListener
            }

            val policies = snapshot?.mapNotNull { document ->
                document.toObject(Policy::class.java)
            } ?: emptyList()
            policiesFlow.value = policies
            Log.d("FirestoreRepository", "Fetched policies: $policies")
        }
        return policiesFlow
    }

    fun purchasePolicy(userId: String, policy: Policy) {
        val policyMap = mapOf(
            "userId" to userId,
            "name" to policy.name,
            "premium" to policy.premium
        )
        policiesCollection.add(policyMap)
            .addOnSuccessListener {
                Log.d("FirestoreRepository", "Policy added successfully: $policyMap")
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreRepository", "Error adding policy", e)
            }
    }
}

