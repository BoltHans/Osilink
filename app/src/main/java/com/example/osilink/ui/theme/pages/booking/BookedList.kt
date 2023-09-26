package com.example.osilink.ui.theme.pages.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.osilink.data.BookingRepository
import com.example.osilink.models.Booking
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.osilink.navigation.ROUTE_BUY
import com.example.osilink.navigation.ROUTE_SELL


@Composable
fun BookedListScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var BookingRepository = BookingRepository(navController,context)


        val emptyBookingState = remember { mutableStateOf(Booking("","","","")) }
        var emptyBookingsListState = remember { mutableStateListOf<Booking>() }

        var bookings = BookingRepository.viewBookings(emptyBookingState, emptyBookingsListState)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "LIST OF ALL BOOKINGS",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.White)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {
                items(bookings){
                    BookingItem(
                        houseName = it.houseName,
                        date = it.date,
                        time = it.time,
                        id = it.id,
                        navController = navController,
                        BookingRepository = BookingRepository
                    )
                }
            }
        }
    }
}


@Composable
fun BookingItem(houseName:String, date:String, time:String, id:String,
                navController:NavHostController, BookingRepository:BookingRepository) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = houseName)
        Text(text = date)
        Text(text = time)
        Button(onClick = {
            BookingRepository.deleteBookings(id)
        },
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(text = "Delete Booking",
                 color = Color.Black
            )
        }
        Button(onClick = {
            navController.navigate(ROUTE_SELL)
        },
            colors = ButtonDefaults.buttonColors(Color.White)
            ) {
            Text(text = "Enter House Details",
                 color = Color.Black
            )
        }
    }
}