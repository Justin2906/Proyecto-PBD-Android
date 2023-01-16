package model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.firestore.FirebaseFirestore

data class Jugadores(
    val Nombre: String,
    val Dorsal: String,
    val Division: String,
    val Posicion: String
)



