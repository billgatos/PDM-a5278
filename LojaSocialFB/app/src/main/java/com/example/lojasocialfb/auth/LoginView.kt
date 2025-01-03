package com.example.lojasocialfb.auth


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lojasocialfb.auth.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lojasocialfb.ui.theme.LojaSocialFBTheme

@Composable
fun LoginView(modifier: Modifier = Modifier,
              onLoginSuccess: () -> Unit = {}
) {

    val viewModel : LoginViewModel = viewModel()
    val state = viewModel.state

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value = state.value.username,
                onValueChange = viewModel::onUsernameChange,
                placeholder = {
                    Text(text = "email")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = state.value.password,
                onValueChange = viewModel::onPasswordChange,
                placeholder = {
                    Text(text = "password")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.login(onLoginSuccess = onLoginSuccess)
            }) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = state.value.error ?: "")
            if (state.value.isLoading)
                CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    LojaSocialFBTheme {
        LoginView()
    }
}