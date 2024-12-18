package com.example.osilink.ui.theme.pages.rent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.osilink.R
import com.example.osilink.data.HouseRepository
import com.example.osilink.models.House
import com.example.osilink.ui.theme.pages.houseItem.HouseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

@Composable
fun RentScreen(navController: NavHostController) {
    val context = LocalContext.current
    val houseRepository = HouseRepository(navController, context)

    val houses = remember { mutableStateListOf<House>() }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        houseRepository.viewHouseRent()
            .flowOn(Dispatchers.IO)
            .collect { fetchedHouses ->
                houses.clear()
                houses.addAll(fetchedHouses)
                isLoading.value = false
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        if (isLoading.value) {
            Text("Loading...", color = Color.White, fontSize = 18.sp)
        } else if (houses.isEmpty()) {
            Text("No houses available.", color = Color.White, fontSize = 18.sp)
        } else {
            LazyColumn {
                items(houses) { house ->
                    HouseItem(
                        name = house.name,
                        email = house.email,
                        phoneNumber = house.phoneNumber,
                        valuation = house.valuation,
                        imageUrl = house.imageUrl,
                        id = house.id,
                        navController = navController,
                        houseRepository = houseRepository
                    )
                }
            }
        }
    }
}
