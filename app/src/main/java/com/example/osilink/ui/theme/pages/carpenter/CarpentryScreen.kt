package com.example.osilink.ui.theme.pages.carpenter

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
import com.example.osilink.R
import com.example.osilink.data.CarpenterRepository
import com.example.osilink.models.Carpenter



@Composable
fun CarpenterScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var carpenterRepository = CarpenterRepository(navController, context)

        val emptyCarpenter = remember { mutableStateOf(Carpenter("","","","")) }
        var emptyCarpenterListState = remember { mutableStateListOf<Carpenter>() }
        var carpenters = carpenterRepository.viewCarpenter(emptyCarpenter , emptyCarpenterListState)




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
                            "Would you like to contact an carpenter? Scroll below.",
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
                items(carpenters){
                    CarpenterItem(
                        name = it.name,
                        email = it.email,
                        phoneNumber = it.phoneNumber,
                        userid =it.userId,
                        navController = navController,
                        carpenterRepository = carpenterRepository
                    )
                }
            }
        }
    }
}


@Composable
fun CarpenterItem(name: String, email: String, phoneNumber: String, userid: String, navController:NavHostController, carpenterRepository: CarpenterRepository) {
    var context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
        Text(text = name)
        Text(text = email)
        Text(text = phoneNumber)
        Button(onClick = {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber))
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context as Activity,arrayOf<String>(Manifest.permission.CALL_PHONE),1                    )
            } else {
                context.startActivity(intent)
            } },
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(text = "Call Plumber",
                color = Color.Black)
        }
    }
}


