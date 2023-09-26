package com.example.osilink.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.osilink.models.BottomBarScreen
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
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(rememberNavController())
        }
        composable(route = BottomBarScreen.Buy.route) {
            BuyScreen(rememberNavController())
        }
        composable(route = BottomBarScreen.Rent.route) {
            RentScreen(rememberNavController())
        }
        composable(route = BottomBarScreen.SignUp.route) {
            SignUpScreen(rememberNavController())
        }
        composable(route = BottomBarScreen.MyHome.route) {
            MyHomeScreen(rememberNavController())
        }
        composable(ROUTE_HOME) {
            HomeScreen(rememberNavController())
        }
        composable(ROUTE_RENT) {
            RentScreen(rememberNavController())
        }
        composable(ROUTE_SELL) {
            SellScreen(rememberNavController())
        }
        composable(ROUTE_MYHOME) {
            MyHomeScreen(rememberNavController())
        }
        composable(ROUTE_BOOKING) {
            BookingScreen(rememberNavController())
        }
        composable(ROUTE_LOGIN){
            LogInScreen(rememberNavController())
        }
        composable(ROUTE_SIGNUP){
            SignUpScreen(rememberNavController())
        }
        composable(ROUTE_CARPENTRY) {
            CarpenterScreen(rememberNavController())
        }
        composable(ROUTE_CLEANING) {
            CleanerServicesScreen(rememberNavController())
        }
        composable(ROUTE_ELECTRICAL) {
            ElectricalScreen(rememberNavController())
        }
        composable(ROUTE_PLUMBING) {
            PlumbingScreen(rememberNavController())
        }
        composable(ROUTE_SIGNUP_ELECTRICIAN) {
            SignUpElectriciansScreen(rememberNavController())
        }
        composable(ROUTE_SIGNUP_CLEANER) {
            SignUpCleanersScreen(rememberNavController())
        }
        composable(ROUTE_SIGNUP_CARPENTER) {
            SignUpCarpentersScreen(rememberNavController())
        }
        composable(ROUTE_SIGNUP_PLUMBER) {
            SignUpPlumbersScreen(rememberNavController())
        }
        composable(ROUTE_USER_ELECTRICIAN) {
            UserElectricianScreen(rememberNavController())
        }
        composable(ROUTE_USER_PLUMBER) {
            UserPlumberScreen(rememberNavController())
        }
        composable(ROUTE_USER_CLEANER) {
            UserCleanerScreen(rememberNavController())
        }
        composable(ROUTE_USER_CARPENTER) {
            UserCarpenterScreen(rememberNavController())
        }
        composable(ROUTE_THANK_YOU) {
            ThankNoteScreen(rememberNavController())
        }
    }
}