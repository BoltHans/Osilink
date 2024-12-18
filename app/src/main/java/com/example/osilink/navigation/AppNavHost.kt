package com.example.osilink.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.osilink.ui.theme.pages.booking.BookedListScreen
import com.example.osilink.ui.theme.pages.booking.BookingScreen
import com.example.osilink.ui.theme.pages.buy.BuyScreen
import com.example.osilink.ui.theme.pages.carpenter.CarpenterScreen
import com.example.osilink.ui.theme.pages.home.HomeScreen
import com.example.osilink.ui.theme.pages.login.LogInScreen
import com.example.osilink.ui.theme.pages.myHome.MyHomeScreen
import com.example.osilink.ui.theme.pages.rent.RentScreen
import com.example.osilink.ui.theme.pages.sell.SellScreen
import com.example.osilink.ui.theme.pages.carpenter.SignUpCarpentersScreen
import com.example.osilink.ui.theme.pages.carpenter.UserCarpenterScreen
import com.example.osilink.ui.theme.pages.cleaner.CleanerServicesScreen
import com.example.osilink.ui.theme.pages.cleaner.SignUpCleanersScreen
import com.example.osilink.ui.theme.pages.cleaner.UserCleanerScreen
import com.example.osilink.ui.theme.pages.electrician.ElectricalScreen
import com.example.osilink.ui.theme.pages.electrician.SignUpElectriciansScreen
import com.example.osilink.ui.theme.pages.electrician.UserElectricianScreen
import com.example.osilink.ui.theme.pages.home.ThankNoteScreen
import com.example.osilink.ui.theme.pages.plumber.PlumbingScreen
import com.example.osilink.ui.theme.pages.plumber.SignUpPlumbersScreen
import com.example.osilink.ui.theme.pages.plumber.UserPlumberScreen
import com.example.osilink.ui.theme.pages.signup.SignUpScreen


@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination:String = ROUTE_HOME) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(ROUTE_BUY) {
            BuyScreen(navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_RENT) {
            RentScreen(navController)
        }
        composable(ROUTE_SELL) {
            SellScreen(navController)
        }
        composable(ROUTE_MYHOME) {
            MyHomeScreen(navController)
        }
        composable(ROUTE_BOOKING) {
            BookingScreen(navController)
        }
        composable(ROUTE_BOOKED_LIST) {
            BookedListScreen(navController)
        }
        composable(ROUTE_LOGIN){
            LogInScreen(navController)
        }
        composable(ROUTE_SIGNUP){
            SignUpScreen(navController)
        }
        composable(ROUTE_CARPENTRY) {
            CarpenterScreen(navController)
        }
        composable(ROUTE_CLEANING) {
            CleanerServicesScreen(navController)
        }
        composable(ROUTE_ELECTRICAL) {
            ElectricalScreen(navController)
        }
        composable(ROUTE_PLUMBING) {
            PlumbingScreen(navController)
        }
        composable(ROUTE_SIGNUP_ELECTRICIAN) {
            SignUpElectriciansScreen(navController)
        }
        composable(ROUTE_SIGNUP_CLEANER) {
            SignUpCleanersScreen(navController)
        }
        composable(ROUTE_SIGNUP_CARPENTER) {
            SignUpCarpentersScreen(navController)
        }
        composable(ROUTE_SIGNUP_PLUMBER) {
            SignUpPlumbersScreen(navController)
        }
        composable(ROUTE_USER_ELECTRICIAN) {
            UserElectricianScreen(navController)
        }
        composable(ROUTE_USER_PLUMBER) {
            UserPlumberScreen(navController)
        }
        composable(ROUTE_USER_CLEANER) {
            UserCleanerScreen(navController)
        }
        composable(ROUTE_USER_CARPENTER) {
            UserCarpenterScreen(navController)
        }
        composable(ROUTE_THANK_YOU) {
            ThankNoteScreen(navController)
        }
    }
}