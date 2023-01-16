package com.example.peticionesbbdd.views

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peticionesbbdd.R
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun VistaConsultar(){
    var nombre_coleccion = "jugadores"
    val db = FirebaseFirestore.getInstance()

    //imagenes usadas
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
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Image(
                painter = logo,
                contentDescription = null
            )

            Text(
                text = "Consultar jugador",
                color = Color.White,
                fontSize = 35.sp
            )

            var datos by remember { mutableStateOf("") }
            var nombre_jugador by remember { mutableStateOf("") }
            var dorsal_jugador by remember { mutableStateOf("") }
            var division_jugador by remember { mutableStateOf("") }
            var posicion_jugador by remember { mutableStateOf("") }
            val field_busqueda = "dorsal"

            OutlinedTextField(
                value = dorsal_jugador,
                onValueChange = { dorsal_jugador = it },
                label = { Text("Introduce el dorsal del jugador") },
                modifier = Modifier.background(Color.White, shape = CutCornerShape(12.dp)),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            Button(
                onClick = {
                    // HACEMOS LA CONSULTA A LA COLECCION CON GET
                    db.collection(nombre_coleccion)
                        .document(dorsal_jugador)
                        .get()

                        //SI SE CONECTA CORRECTAMENTE
                        // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENO EN DATOS
                        .addOnSuccessListener { encontrado ->

                                //Para crear un HashMap con todos los datos
                                datos += " ${encontrado.data}\n"

                                //Para crear un HashMap con todos los datos
                                nombre_jugador += encontrado["nombre"].toString()
                                dorsal_jugador += encontrado["dorsal"].toString()
                                division_jugador += encontrado["division"].toString()
                                posicion_jugador += encontrado["posicion"].toString()
                                Log.i("DATOS:", datos)


                            if (datos.isEmpty()) {
                                datos = "No existen datos"
                            }
                        }
                        //SI NO CONECTA CORRECTAMENTE
                        .addOnFailureListener { resultado ->
                            datos = "La conexión a FireStore no se ha podido completar"
                        }

                    // VACIAMOS VARIABLE AL DAR AL BOTON
                    datos = ""
                    dorsal_jugador = ""
                },

                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = CutCornerShape(12.dp),
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)
            )
            {
                Text(text = "Cargar Datos")
            }

            Spacer(modifier = Modifier.size(10.dp))

            // PINTAMOS EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS
            //Text (text = datos)
            Text(text = "Nombre: " + nombre_jugador, color = Color.White,fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Text(text = "Dorsal: " + dorsal_jugador, color = Color.White,fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Text(text = "Division: " + division_jugador, color = Color.White,fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Text(text = "Posicion: " + posicion_jugador, color = Color.White,fontWeight = FontWeight.Bold, fontSize = 30.sp)
        }
    }
}