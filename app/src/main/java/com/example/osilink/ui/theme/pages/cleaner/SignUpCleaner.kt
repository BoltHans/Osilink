package com.example.osilink.ui.theme.pages.cleaner

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
import com.example.osilink.data.CleanerRepository
import com.example.osilink.ui.theme.OsilinkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpCleanersScreen(navController: NavController) {


    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }




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
                        "Would you like sign up as an Cleaner?",
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
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Cleaner's Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(text = "Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                var CleanerRepository = CleanerRepository(navController,context)
                CleanerRepository.saveCleaner(name.trim(), email.trim(), phoneNumber.trim())
            },

            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Save Details", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpCleanerScreenPreview() {
    OsilinkTheme {
        SignUpCleanersScreen(navController = rememberNavController())
    }
}
