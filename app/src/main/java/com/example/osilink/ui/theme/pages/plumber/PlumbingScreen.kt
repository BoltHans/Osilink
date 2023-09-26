package com.example.osilink.ui.theme.pages.plumber

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.example.osilink.data.PlumberRepository
import com.example.osilink.models.Plumber
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.osilink.R




@Composable
fun PlumbingScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var plumberRepository = PlumberRepository(navController, context)

        val emptyPlumber = remember { mutableStateOf(Plumber("","","","")) }
        var emptyPlumberListState = remember { mutableStateListOf<Plumber>() }
        var plumbers = plumberRepository.viewPlumber(emptyPlumber , emptyPlumberListState)




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
                            "Would you like to contact an plumber? Scroll below.",
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
                items(plumbers){
                    PlumberItem(
                        name = it.name,
                        email = it.email,
                        phoneNumber = it.phoneNumber,
                        userid =it.userId,
                        navController = navController,
                        plumberRepository = plumberRepository
                    )
                }
            }
        }
    }
}


@Composable
fun PlumberItem(name: String, email: String, phoneNumber: String, userid: String, navController:NavHostController, plumberRepository: PlumberRepository) {
    var context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth()) {
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
            )
        {
            Text(text = "Call Plumber",
                color = Color.Black)
        }
    }
}


