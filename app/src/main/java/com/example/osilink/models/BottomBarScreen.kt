package com.example.osilink.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock

import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.osilink.navigation.ROUTE_BUY
import com.example.osilink.navigation.ROUTE_HOME
import com.example.osilink.navigation.ROUTE_MYHOME
import com.example.osilink.navigation.ROUTE_RENT
import com.example.osilink.navigation.ROUTE_SIGNUP

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = ROUTE_HOME,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Buy : BottomBarScreen(
        route = ROUTE_BUY,
        title = "Buy",
        icon = Icons.Default.ShoppingCart
    )

    object Rent : BottomBarScreen(
        route = ROUTE_RENT,
        title = "Rent",
        icon = Icons.Default.Lock
    )
    object SignUp : BottomBarScreen(
        route = ROUTE_SIGNUP,
        title = "Sign Up",
        icon = Icons.Default.AddCircle
    )
    object MyHome : BottomBarScreen(
        route = ROUTE_MYHOME,
        title = "My Home",
        icon = Icons.Default.Build
    )
}