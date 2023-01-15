package com.example.peticionesbbdd.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peticionesbbdd.R
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun VistaEliminar(){
    val dataBase = FirebaseFirestore.getInstance()

    var nombre_coleccion = "jugadores"

    val back = painterResource(id = R.drawable.fondo4)
    val logo = painterResource(id = R.drawable.logo)

    var dorsal by remember { mutableStateOf("") }

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

            Text(text = "Eliminar jugador", color = Color.White, fontSize = 35.sp)

        }

        OutlinedTextField(
            value = dorsal,
            onValueChange = { dorsal = it },
            label = { Text("Introduce el NIF a borrar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        var mensaje by remember { mutableStateOf("") }

        Button(

            onClick = {
                if (dorsal.isNotBlank()) {
                    dataBase.collection(nombre_coleccion)
                        .document(dorsal)
                        .delete()
                        .addOnSuccessListener {
                            mensaje = "Datos borrados correctamente"
                            dorsal = ""
                        }
                        .addOnFailureListener {
                            mensaje = "No se ha podido borrar"
                            dorsal = ""
                        }
                }
            },

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {

            Text(text = "Borrar")


        }
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = mensaje)
    }
}