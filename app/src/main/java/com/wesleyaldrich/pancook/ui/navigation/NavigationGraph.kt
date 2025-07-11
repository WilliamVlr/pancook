package com.wesleyaldrich.pancook.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wesleyaldrich.pancook.ui.screens.HomeScreen
import com.wesleyaldrich.pancook.ui.screens.MyRecipeScreen
import com.wesleyaldrich.pancook.ui.screens.PlannerScreen
import com.wesleyaldrich.pancook.ui.screens.ProfileScreen

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.MyRecipe.route) { MyRecipeScreen() }
        composable(Screen.Planner.route) { PlannerScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}