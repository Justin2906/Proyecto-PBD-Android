package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import navigation.Routes.*
import com.example.peticionesbbdd.views.*

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen.route) {
        composable(SplashScreen.route) { SplashScreen(navController)}
        composable(Main.route){
            options(
                navAdd = {
                    navController.navigate(Add.route)
                },
                navModify = {
                    navController.navigate(Modify.route)
                },
                navDelete = {
                    navController.navigate(Delete.route)
                },
                navQuery = {
                    navController.navigate(Query.route)
                },
                navQueryAll = {
                    navController.navigate(QueryAll.route)
                }
            )
        }

        composable(Add.route){ VistaAñadir() }
        composable(Modify.route){ VistaModificar()}
        composable(Delete.route){ VistaEliminar()}
        composable(Query.route){ VistaConsultar() }
        composable(QueryAll.route){ VistaConsultarTodo() }
    }
}