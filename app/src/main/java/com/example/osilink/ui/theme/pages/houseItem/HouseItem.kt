package com.example.osilink.ui.theme.pages.houseItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.osilink.data.HouseRepository

@Composable
fun HouseItem(
    name: String,
    email: String,
    phoneNumber: String,
    valuation: String,
    imageUrl: String,
    id: String,
    navController: NavHostController,
    houseRepository: HouseRepository
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Image
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "House Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            // Details
            Text("Name: $name", fontSize = 18.sp, color = Color.Black)
            Text("Email: $email", fontSize = 14.sp, color = Color.Gray)
            Text("Phone: $phoneNumber", fontSize = 14.sp, color = Color.Gray)
            Text("Valuation: $valuation", fontSize = 14.sp, color = Color.Black)

            // Action Button
            Button(
                onClick = { /* Navigate or perform actions here */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("View Details")
            }
        }
    }
}
