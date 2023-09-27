package com.example.osilink.ui.theme.pages.buy

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.osilink.R
import com.example.osilink.data.BookingRepository
import com.example.osilink.data.HouseRepository
import com.example.osilink.models.House
import com.example.osilink.navigation.ROUTE_SIGNUP
import com.example.osilink.navigation.ROUTE_THANK_YOU


@Composable
fun BuyScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var houseRepository = HouseRepository(navController, context)

        val emptyHouseState = remember { mutableStateOf(House("","","","","","")) }
        var emptyHouseListState = remember { mutableStateListOf<House>() }
        var houses = houseRepository.viewHouseSell(emptyHouseState , emptyHouseListState)




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
                            "Would you like to purchase your dream home? Scroll below.",
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
                items(houses){
                    HouseItem(
                        name = it.name,
                        email = it.email,
                        phoneNumber = it.phoneNumber,
                        valuation = it.valuation,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        navController = navController,
                        houseRepository = houseRepository
                    )
                }
            }
        }
    }
}


@Composable
fun HouseItem(name: String, email: String, phoneNumber: String, imageUrl: String,valuation: String,id: String,
              navController:NavHostController, houseRepository: HouseRepository) {
    var context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
        Text(text = name)
        Text(text = email)
        Text(text = phoneNumber)
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Text(text = valuation)
        Button(onClick = {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+25402336371"))
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context as Activity,arrayOf<String>(Manifest.permission.CALL_PHONE),1                    )
                } else {
                    context.startActivity(intent)
                }},
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(text = "Call Agent",
                color = Color.Black)
        }
        Button(onClick = {
            //HouseRepository.deleteHouseSell(id)
            navController.navigate(ROUTE_THANK_YOU)
        },
            colors = ButtonDefaults.buttonColors(Color.White)) {
            Text(text = "Rent",
                color = Color.Black)
        }
    }
}
