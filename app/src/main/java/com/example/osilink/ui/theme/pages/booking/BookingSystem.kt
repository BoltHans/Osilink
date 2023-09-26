package com.example.osilink.ui.theme.pages.booking

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.osilink.R
import com.example.osilink.data.BookingRepository
import com.example.osilink.navigation.ROUTE_RENT
import com.example.osilink.ui.theme.OsilinkTheme



@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController) {


    val context = LocalContext.current

    var houseName by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }




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
                            "Would you like to book and appointment?",
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
        OutlinedTextField(
            value = houseName,
            onValueChange = { houseName = it },
            label = { Text(text = "House Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text(text = "Date") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = { Text(text = "Time") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                var BookingRepository = BookingRepository(navController,context)
                BookingRepository.saveBooking(houseName.trim(), time.trim(), date.trim())
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Book Appointment", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun BookingScreenPreview() {
    OsilinkTheme {
        BookingScreen(navController = rememberNavController())
    }
}
