package com.example.peticionesbbdd.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peticionesbbdd.R
import com.example.peticionesbbdd.ui.theme.PeticionesBBDDTheme

@Composable
fun VistaAñadir(){
    val name = remember { mutableStateOf(TextFieldValue()) }
    val dorsal = remember { mutableStateOf(TextFieldValue()) }
    val division = remember { mutableStateOf(TextFieldValue()) }
    val posicion = remember { mutableStateOf(TextFieldValue()) }

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
            Image(painter = logo, contentDescription = null)

            Text(text = "Datos del jugador", color = Color.White, fontSize = 35.sp)

            TextField(
                label ={ Text(text = "Nombre del jugador")},
                value = name.value,
                onValueChange = {name.value = it},
                modifier = Modifier.background(Color.White,CutCornerShape(12.dp)),
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                label ={ Text(text = "Dorsal del jugador")},
                value = dorsal.value,
                onValueChange = {dorsal.value = it},
                modifier = Modifier.background(Color.White,CutCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                label ={ Text(text = "Division del jugador")},
                value = division.value,
                onValueChange = {division.value = it},
                modifier = Modifier.background(Color.White,CutCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                label ={ Text(text = "Posicion del jugador")},
                value = posicion.value,
                onValueChange = {posicion.value = it},
                modifier = Modifier.background(Color.White,CutCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { /*TODO*/ },
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


        }
    }
}
