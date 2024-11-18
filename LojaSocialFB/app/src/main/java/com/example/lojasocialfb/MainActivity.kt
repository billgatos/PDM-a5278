package com.example.lojasocialfb

import com.example.lojasocialfb.auth.LoginView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfb.ui.theme.LojaSocialFBTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.example.lojasocialfb.auth.AuthViewModel
import com.example.lojasocialfb.visita.RegisterVisitaScreen
import com.example.lojasocialfb.visita.VisitaViewModel


const val TAG = "LojaSocialFB"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var navController = rememberNavController()
            val authViewModel = AuthViewModel()
            val visitaViewModel = VisitaViewModel()

            LojaSocialFBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") {
                            LoginView {
                                navController.navigate("home")
                            }
                        }
                        composable("home") {
                            //AddListTypesView()
                            RegisterVisitaScreen(visitaViewModel)
                        }
                    }
                }

                LaunchedEffect(Unit) {

                    val auth = Firebase.auth

                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        navController.navigate("home")
                    }
                }
            }
        }
    }
}
