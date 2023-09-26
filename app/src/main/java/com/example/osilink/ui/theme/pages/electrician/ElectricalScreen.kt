package com.example.osilink.ui.theme.pages.electrician

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.osilink.R
import com.example.osilink.data.ElectricianRepository
import com.example.osilink.models.Electrician


@Composable
fun ElectricalScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var electricianRepository = ElectricianRepository(navController, context)

        val emptyElectrician = remember { mutableStateOf(Electrician("","","","")) }
        var emptyElectricianListState = remember { mutableStateListOf<Electrician>() }
        var electricians = electricianRepository.viewElectrician(emptyElectrician , emptyElectricianListState)




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
                            "Would you like to contact an electrician? Scroll below.",
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
            LazyColumn {
                items(electricians){
                    ElectricianItem(
                        name = it.name,
                        email = it.email,
                        phoneNumber = it.phoneNumber,
                        userid =it.userId,
                        navController = navController,
                        electricianRepository = electricianRepository
                    )
                }
            }
        }
    }
}


@Composable
fun ElectricianItem(name: String, email: String, phoneNumber: String, userid: String, navController:NavHostController, electricianRepository: ElectricianRepository) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = email)
        Text(text = phoneNumber)
        Button(onClick = {
            //CALL INTENT
        },
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(text = "Call Electrician",
                color = Color.Black)
        }
    }
}



