package com.example.osilink.ui.theme.pages.sell

import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.osilink.R
import com.example.osilink.data.HouseRepository
import com.example.osilink.ui.theme.OsilinkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellScreen(navController: NavHostController) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var valuation by remember { mutableStateOf("") }

    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .verticalScroll(enabled = true, state = rememberScrollState())
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Osilink",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            alignment = Alignment.Center
        )

        Text(
            text = "Welcome to Osilink Real Estate. " +
                    "Do you want to sell your home? Enter the details below," +
                    "The phone number sent should be active on whatsapp so as to send the house location",
            fontSize = 13.sp,
            color = Color.White,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Gray,
                    offset = Offset(10f, 10f),
                    blurRadius = 10f
                )
            ),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
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

        OutlinedTextField(
            value = valuation,
            onValueChange = { valuation = it },
            label = { Text(text = "Valuation") },
            modifier = Modifier.fillMaxWidth()
        )
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Row {
            IconButton(
                onClick = {
                    imagePicker.launch("image/*")
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent
                )
            ) {
                Icon(imageVector = Icons.Default.AddAPhoto, contentDescription = "")
            }
            Text(text = "Add your house image", color = Color.White)
        }


        Button(
            onClick = {
                var houseRepository = HouseRepository(navController, context)
                houseRepository.saveHouseRentWithImage(
                    name.trim(),
                    email.trim(),
                    phoneNumber.trim(),
                    valuation.trim(),
                    imageUri!!
                )
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Rental",
                color = Color.Black
            )
        }


        Button(
            onClick = {
                var houseRepository = HouseRepository(navController, context)
                houseRepository.saveHouseSellWithImage(
                    name.trim(),
                    email.trim(),
                    phoneNumber.trim(),
                    valuation.trim(),
                    imageUri!!
                )
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Full Sale",
                color = Color.Black
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SellScreenPreview() {
    OsilinkTheme {
        SellScreen(rememberNavController())
    }
}


