package com.example.peticionesbbdd.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.navigation.NavHostController
import com.example.peticionesbbdd.R
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Vista_add(navController: NavHostController){

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "jugadores"

    val back = painterResource(id = R.drawable.fondo4)
    val logo = painterResource(id = R.drawable.logo)

    var name by remember { mutableStateOf("") }
    var dorsal by remember { mutableStateOf("") }
    var division by remember { mutableStateOf("") }
    var posicion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = back, contentScale = ContentScale.FillBounds)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Image(painter = logo, contentDescription = null)

            Text(text = "Guardar nuevo jugador",
                color = Color.White,
                fontSize = 35.sp)

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre  del jugador") },
                modifier = Modifier.background(Color.White, shape = CutCornerShape(12.dp)),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = dorsal,
                onValueChange = { dorsal = it },
                label = { Text("Dorsal del jugador") },
                modifier = Modifier.background(Color.White, shape = CutCornerShape(12.dp)),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = division,
                onValueChange = { division = it },
                label = { Text("Division del jugador") },
                modifier = Modifier.background(Color.White, shape = CutCornerShape(12.dp)),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = posicion,
                onValueChange = { posicion = it },
                label = { Text("Posición  del jugador") },
                modifier = Modifier.background(Color.White, shape = CutCornerShape(12.dp)),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(15.dp))

            val dato = hashMapOf(
                "nombre" to name.toString(),
                "dorsal" to dorsal.toString(),
                "division" to division.toString(),
                "posicion" to posicion.toString(),
            )

            var mensaje by remember { mutableStateOf("") }

            Button(
                onClick = {
                    db.collection(nombre_coleccion)
                        .document(dorsal)
                        .set(dato)
                        .addOnSuccessListener {
                            mensaje ="Datos guardados correctamente"
                            name = ""
                            dorsal = ""
                            division = ""
                            posicion = ""
                        }
                        .addOnFailureListener {
                            mensaje ="No se ha podido guardar"
                            name = ""
                            dorsal = ""
                            division = ""
                            posicion = ""
                        }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = CutCornerShape(12.dp),
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Añadir",
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.size(15.dp))

            Text(
                text = mensaje,
                color = Color.White
            )

        }
    }
}
