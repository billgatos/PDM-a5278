package com.example.lojasocialfb.visita

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.CollectionReference
import java.util.Date

data class Visita(
    val data_hora: Date,
    val numero_pessoas: Int,
    val nome: String,
    val familia_ref: String,
    val voluntario_ref: String
)

class VisitaViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val visitasCollection: CollectionReference = db.collection("visitas")

    fun registerVisita(visita: Visita, onComplete: (Boolean) -> Unit) {
        db.collection("visitas").add(visita).addOnSuccessListener {
            onComplete(true)
        }.addOnFailureListener {
            onComplete(false)
        }
    }
}
