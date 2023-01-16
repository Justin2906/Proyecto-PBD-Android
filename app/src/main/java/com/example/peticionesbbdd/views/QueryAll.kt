package com.example.peticionesbbdd.views

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.peticionesbbdd.R
import com.google.firebase.firestore.FirebaseFirestore
import model.Jugadores

@Composable
fun PlayerCard(jugadores: Jugadores, navController: NavController) {
    Surface(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        color = Color(0xFF373960)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = "Nombre: " +  jugadores.Nombre,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Text(
                text = "Dorsal: " + jugadores.Dorsal,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Text(
                text = "Division: " + jugadores.Division,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Text(
                text = "Posicion: " + jugadores.Posicion,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 5.dp)
            ) {
                Text(
                    text = "Editar",
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun ConsultarTodo(navController: NavController){
    val db = FirebaseFirestore.getInstance()
    var nombre_coleccion = "jugadores"

    val back = painterResource(id = R.drawable.fondo4)
    val logo = painterResource(id = R.drawable.logo)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = back, contentScale = ContentScale.FillBounds)
    )
    {


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Image(
                painter = logo,
                contentDescription = null
            )

            Text(
                text = "Consultar jugadores",
                color = Color.White,
                fontSize = 35.sp
            )

            var listaPlayers by remember { mutableStateOf(listOf<Jugadores>())}
            var datosJugadores by remember { mutableStateOf("") }

            Button(
                onClick = {
                    db.collection(nombre_coleccion)
                        .get()
                        .addOnSuccessListener { search ->
                            for (encontrado in search) {
                                //datosJugadores += "${document.id}: ${document.data}\n\n"
                                listaPlayers += Jugadores(
                                    encontrado["nombre"].toString(),
                                    encontrado["dorsal"].toString(),
                                    encontrado["division"].toString(),
                                    encontrado["posicion"].toString()
                                )
                                //Log.d("Datos", lista.listaJugadores.toString())
                            }
                            datosJugadores += listaPlayers.toString()
                            if (datosJugadores.isEmpty()){
                                datosJugadores = "No existen registros"
                            }
                        }
                        .addOnFailureListener { exception ->
                            datosJugadores = "No se a podido recoger los datos"
                            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                        }


                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = CutCornerShape(12.dp),
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Cargar Jugadores",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Player(navController = navController, listaPlayers)
        }
    }
}

@Composable
fun PlayerList(lista: List<Jugadores>, navController: NavController){
    LazyColumn(
    ){
        items(lista){jugador ->
            PlayerCard(jugador, navController = navController)
        }
    }
}

@Composable
fun Player(navController: NavController, lista: List<Jugadores>){
    Column(
    ) {
        PlayerList(lista =lista, navController = navController)
        Log.d("Datos", lista.toString())
    }
}










