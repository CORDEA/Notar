package jp.cordea.notar.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.cordea.notar.ui.login.Login

object Destination {
    const val LOGIN = "login"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Destination.LOGIN) {
        composable(route = Destination.LOGIN) {
            Login()
        }
    }
}
