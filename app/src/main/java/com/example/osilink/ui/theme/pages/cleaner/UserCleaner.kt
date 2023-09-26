package com.example.osilink.ui.theme.pages.cleaner

import com.example.osilink.navigation.ROUTE_CLEANING
import com.example.osilink.navigation.ROUTE_SIGNUP_CLEANER
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.osilink.R
import com.example.osilink.navigation.ROUTE_ELECTRICAL
import com.example.osilink.navigation.ROUTE_SIGNUP_ELECTRICIAN
import com.example.osilink.ui.theme.OsilinkTheme


@SuppressLint("MutableCollectionMutableState")
@Composable
fun UserCleanerScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Osilink",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            alignment = Alignment.Center
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome to Osilink Real Estate. " +
                        "Are you an app user looking for an cleaner or do you want to sign up as one?",
                fontSize = 13.sp,
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(10f, 10f),
                        blurRadius = 10f
                    )
                )
            )
        }
        Button(
            onClick = {
                navController.navigate(ROUTE_CLEANING)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "User", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                navController.navigate(ROUTE_SIGNUP_CLEANER)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Electrician", color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCleanerPreview() {
    OsilinkTheme {
        UserCleanerScreen(navController = rememberNavController())
    }
}
