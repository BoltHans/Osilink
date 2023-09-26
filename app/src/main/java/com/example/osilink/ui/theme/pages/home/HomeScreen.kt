package com.example.osilink.ui.theme.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.osilink.R
import com.example.osilink.ui.theme.OsilinkTheme

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val imageOffset by remember { mutableStateOf(0.dp) }

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Osilink",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .offset(x = imageOffset),
            alignment = Alignment.Center
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            Alignment.BottomCenter
        ) {
            Text(
                text = "Welcome to Osilink Real Estate. " +
                        "Which dream house would you like to purchase?",
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

        Column(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Why Osilink Real Estate is the best:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "- We have the largest selection of properties in the area.",
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = "- Our agents are highly experienced and knowledgeable.",
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = "- We offer competitive prices and flexible financing options.",
                fontSize = 14.sp,
                color = Color.White
            )

            val anniversaryDate = "November 27"
            val anniversaryDescription = "Join us in celebrating our 10th Anniversary on $anniversaryDate! Exciting events and offers await you."

            Text(
                text = "Upcoming 10th Anniversary Project:",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 22.dp, bottom = 22.dp)
            )
            Text(
                text = anniversaryDescription,
                fontSize = 16.sp,
                color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.realestate),
                contentDescription = "Osilink",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .offset(x = imageOffset),
                alignment = Alignment.Center
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    OsilinkTheme {
        HomeScreen(rememberNavController())

    }
}





