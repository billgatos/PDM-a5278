package com.example.lojasocialfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lojasocialfirebase.ui.theme.LojaSocialFireBaseTheme
import com.google.firebase.FirebaseApp

import androidx.activity.compose.setContent
import com.example.lojasocialfirebase.navigation.AppNavHost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AppNavHost()
//            FirebaseApp.initializeApp(this) // Inicializa o Firebase
//        }
//    }
//}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeFirebaseCollections { success ->
            if (success) {
                setContent {
                    AppNavHost()
                    FirebaseApp.initializeApp(this) // Inicializa o Firebase
                }
            } else {
                Toast.makeText(this, "Falha na inicialização do banco de dados", Toast.LENGTH_LONG).show()
        }
    }
}

fun initializeFirebaseCollections(onComplete: (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    // Definindo a coleção de inicialização e o usuário padrão
    val defaultUserEmail = "robe@gmail.com"
    val defaultUserPassword = "password123"  // Defina uma senha segura para o usuário padrão

    // Função para criar uma coleção vazia
    fun createEmptyCollection(collectionName: String) {
        db.collection(collectionName).document("dummy").set(emptyMap<String, Any>()).addOnSuccessListener {
            // Remove o documento "dummy" após a criação para deixar a coleção realmente vazia
            db.collection(collectionName).document("dummy").delete()
        }
    }

    // Cria coleções vazias, se necessário
    listOf("tarefas", "pais", "familias", "pessoas", "voluntarios", "agenda", "visitas", "lideres_espirituais").forEach { collectionName ->
        createEmptyCollection(collectionName)
    }

    // Verifica se o usuário padrão existe no Firebase Authentication
    auth.fetchSignInMethodsForEmail(defaultUserEmail).addOnCompleteListener { task ->
        if (task.isSuccessful && task.result?.signInMethods.isNullOrEmpty()) {
            // Cria o usuário padrão no Authentication
            auth.createUserWithEmailAndPassword(defaultUserEmail, defaultUserPassword).addOnCompleteListener { createUserTask ->
                if (createUserTask.isSuccessful) {
                    val userId = createUserTask.result?.user?.uid ?: return@addOnCompleteListener

                    // Adiciona o usuário padrão na coleção "users" com tipo "adm"
                    db.collection("users").document(userId).set(mapOf("type" to "adm"))
                        .addOnSuccessListener {
                            onComplete(true)  // Indica sucesso ao completar a inicialização
                        }
                        .addOnFailureListener {
                            onComplete(false)
                        }
                } else {
                    onComplete(false)
                }
            }
        } else {
            // O usuário padrão já existe, então a inicialização está completa
            onComplete(true)
        }
    }.addOnFailureListener {
        onComplete(false)
    }
}
}