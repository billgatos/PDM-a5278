package com.example.lojasocialfb.visita

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Date
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun RegisterVisitaScreen(visitaViewModel: VisitaViewModel) {
    var nome by remember { mutableStateOf("") }
    var numeroPessoas by remember { mutableStateOf("") }
    var familiaRef by remember { mutableStateOf("") }
    var voluntarioRef by remember { mutableStateOf("") }

    //esquecer a inicialização do FirebaseCollections
        //    fun initializeFirebaseCollections(onComplete: (Boolean) -> Unit) {
        //        //val db = FirebaseFirestore.getInstance()
        //        val db = Firebase.firestore
        //        val auth = FirebaseAuth.getInstance()
        //
        //        // Definindo a coleção de inicialização e o usuário padrão
        //        //val defaultUserEmail = "robe@gmail.com"
        //        //val defaultUserPassword = "password123"  // Defina uma senha segura para o usuário padrão
        //
        //        // Função para criar uma coleção vazia
        //        fun createEmptyCollection(collectionName: String) {
        //            db.collection(collectionName).document("ola").set(emptyMap<String, Any>()).addOnSuccessListener {
        //                // Remove o documento "dummy" após a criação para deixar a coleção realmente vazia
        //                db.collection(collectionName).document("ola").delete()
        //            }
        //        }
        //
        //        // Cria coleções vazias, se necessário
        //        listOf("tarefas", "pais", "familias", "pessoas", "voluntarios", "agenda", "visitas", "lideres_espirituais").forEach { collectionName ->
        //            createEmptyCollection(collectionName)
        //        }
        //    }

        //initializeFirebaseCollections { }


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        TextField(
            value = numeroPessoas,
            onValueChange = { numeroPessoas = it },
            label = { Text("Número de Pessoas") }
        )
        TextField(value = familiaRef, onValueChange = { familiaRef = it }, label = { Text("Família (ID)") })
        TextField(value = voluntarioRef, onValueChange = { voluntarioRef = it }, label = { Text("Voluntário (ID)") })

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val visita = Visita(
                data_hora = Date(),
                numero_pessoas = numeroPessoas.toInt(),
                nome = nome,
                familia_ref = familiaRef,
                voluntario_ref = voluntarioRef
            )
            visitaViewModel.registerVisita(visita) { success ->
                if (success) {
                    // Ação para sucesso no registro
                    //Text("Visita registrada com sucesso!", color = androidx.compose.ui.graphics.Color.Green)
                }
            }

        }) {
            Text("Registrar Visita")
        }
    }


}
