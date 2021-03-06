package jp.cordea.notar.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.cordea.notar.ui.home.Home

object Destination {
    const val HOME = "home"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Destination.HOME) {
        composable(route = Destination.HOME) {
            Home(hiltViewModel())
        }
    }
}
