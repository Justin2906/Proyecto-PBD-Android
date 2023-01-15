package com.example.peticionesbbdd.navigation

sealed class Routes(val ruta: String) {
    object  SplashScreen: Routes("Splash Screen")
    object Inicio : Routes("Inicio")
    object Add: Routes("Add")
    object Modify: Routes("Modify")
    object Delete: Routes("Delete")
    object Query: Routes("Query")
    object QueryAll: Routes("QueryAll")

}