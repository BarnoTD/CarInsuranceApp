package com.example.insuranceapp

import AuthViewModel
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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

    Column (modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "Purchased Policies:")
            Button(colors = ButtonDefaults.buttonColors(Color.Red),
                onClick = {
                    authViewModel.logout()
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Policies.route) { inclusive = true }
                    }
                }) {
                Text(text = "Logout")
            }
        }
        LazyRow {
            items(purchasedPolicies) { policy ->
                PolicyCard(policy.name)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //make card a Rectangular background colored in blue
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "Available Policies",
                textAlign = TextAlign.Center
            )
        }


        LazyColumn {
            items(availablePolicies) { policy ->
                Button(onClick = {
                    policiesViewModel.purchasePolicy(policy)
                },
                    modifier = Modifier.padding(end = 8.dp)

                ) {
                    Card(
                        modifier = Modifier
                    ) {
                        Column(
                            modifier = Modifier
                                .animateContentSize(
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioNoBouncy,
                                        stiffness = Spring.StiffnessMedium
                                    )
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(dimensionResource(R.dimen.padding_small))
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(dimensionResource(R.dimen.image_size))
                                        .padding(dimensionResource(R.dimen.padding_small))
                                        .clip(MaterialTheme.shapes.small),
                                    contentScale = ContentScale.Crop,
                                    painter = painterResource(policy.imageResId),
                                    contentDescription = null // Image is decorative
                                )
                                Column(modifier = Modifier) {
                                    Text(
                                        text = policy.name,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
                                    )
                                    Text(
                                        text = "${policy.premium} TND",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                                Spacer(Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))


    }
}
