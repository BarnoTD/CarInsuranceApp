package com.example.insuranceapp

object PoliciesRepository {
    val availablePolicies = listOf(
        Policy(
            imageResId = R.drawable.ic_car,
            name = "Basic Car Coverage",
            premium = 450.00,
        ),
        Policy(
            imageResId = R.drawable.ic_car,
            name = "Car Theft Coverage",
            premium = 2000.00,
        ),
        Policy(
            imageResId = R.drawable.ic_car,
            name = "Mild Car Accident Coverage",
            premium = 800.00,
        ),
        Policy(
            imageResId = R.drawable.ic_car,
            name = "Small Car Accident Coverage",
            premium = 550.00,
        ),
        Policy(
            imageResId = R.drawable.ic_truck,
            name = "Basic Truck Coverage",
            premium = 800.00,
        ),
        Policy(
            imageResId = R.drawable.ic_truck,
            name = "Truck Theft Coverage",
            premium = 5000.00,
        ),
        Policy(
            imageResId = R.drawable.ic_truck,
            name = "Mild Truck Accident Coverage",
            premium = 2500.00,
        ),
        Policy(
            imageResId = R.drawable.ic_truck,
            name = "Small Motorcycle Accident Coverage",
            premium = 800.00,
        ),
        Policy(
            imageResId = R.drawable.ic_motorcycle,
            name = "Basic Motorcycle Coverage",
            premium = 200.00,
        ),
        Policy(
            imageResId = R.drawable.ic_motorcycle,
            name = "Motorcycle Theft Coverage",
            premium = 600.00,
        ),
        Policy(
            imageResId = R.drawable.ic_motorcycle,
            name = "Mild Motorcycle Accident Coverage",
            premium = 700.00,
        ),
        Policy(
            imageResId = R.drawable.ic_motorcycle,
            name = "Small Motorcycle Accident Coverage",
            premium = 300.00,
        ),

        )
}