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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.osilink.R
import com.example.osilink.navigation.ROUTE_HOME
import com.example.osilink.ui.theme.OsilinkTheme

@Composable
fun ThankNoteScreen(navController: NavHostController) {
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
                .padding(32.dp),
            Alignment.BottomCenter
        ) {
            Text(
                text = "Thank you for working with Osilink Real Estate. " +
                        "Hope we do more business with you, visit our headquarters within 2 weeks for the giving over of the key to your new home and the sign some of documents for the handing over.",
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
            onClick = { navController.navigate(ROUTE_HOME) },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Back To Home Screen", color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThankyouNoteScreenPreview() {
    OsilinkTheme {
    ThankNoteScreen(rememberNavController())
        }
    }

