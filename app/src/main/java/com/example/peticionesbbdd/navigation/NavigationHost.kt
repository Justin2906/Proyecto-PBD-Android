package com.example.peticionesbbdd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.peticionesbbdd.navigation.Routes.*
import com.example.peticionesbbdd.views.*

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SplashScreen.ruta)
    {

        composable(SplashScreen.ruta) { SplashScreen(navController)}
        composable(Inicio.ruta){ Options(navController) }
        composable(Add.ruta){ Vista_add(navController) }
        composable(Modify.ruta){ VistaModificar()}
        composable(Delete.ruta){ VistaEliminar()}
        composable(Query.ruta){ VistaConsultar() }
        composable(QueryAll.ruta){ ConsultarTodo(navController) }
    }
}