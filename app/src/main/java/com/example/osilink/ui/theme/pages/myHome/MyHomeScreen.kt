package com.example.osilink.ui.theme.pages.myHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.osilink.R
import com.example.osilink.navigation.ROUTE_CARPENTRY
import com.example.osilink.navigation.ROUTE_CLEANING
import com.example.osilink.navigation.ROUTE_ELECTRICAL
import com.example.osilink.navigation.ROUTE_PLUMBING
import com.example.osilink.ui.theme.OsilinkTheme


@Composable
fun MyHomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
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
                        "Would you like to rent our homes?",
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cleaning),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
                Text(
                    text = "Cleaning Services",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Button(onClick = {navController.navigate(ROUTE_CLEANING)},
                    colors = ButtonDefaults.buttonColors(Color.White)) {
                    Text(
                        text = "Show More",
                        color = Color.Black
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plumber),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
                Text(
                    text = "Plumbing Services",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Button(onClick = {navController.navigate(ROUTE_PLUMBING)},
                    colors = ButtonDefaults.buttonColors(Color.White)) {
                    Text(
                        text = "Show More",
                        color = Color.Black
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.electrician),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
                Text(
                    text = "Electrical Services",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Button(onClick = {navController.navigate(ROUTE_ELECTRICAL)},
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(
                        text = "Show More",
                        color = Color.Black
                    )
                }
            }

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.carpenter),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
                Text(
                    text = "Carpentry Services",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Button(onClick = {navController.navigate(ROUTE_CARPENTRY)},
                    colors = ButtonDefaults.buttonColors(Color.White)
                    ) {
                    Text(
                        text = "Show More",
                        color = Color.Black
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun MyHomeScreenPreview() {
    OsilinkTheme {
        MyHomeScreen(rememberNavController())
    }
}



