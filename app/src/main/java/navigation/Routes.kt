package navigation

sealed class Routes(
    val route: String,
    val title : String,
    ) {
        object  SplashScreen: Routes("Splash Screen", title = "Load App")
        object Main : Routes("inicio","Log in")
        object Add: Routes("Add", title = "add new register")
        object Modify: Routes("Modify", title = "modify register")
        object Delete: Routes("Delete", title = "delete register")
        object Query: Routes("Query", title = "Query register")
        object QueryAll: Routes("QueryAll", title = "Query all register")

}