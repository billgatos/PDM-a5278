package com.example.helloworld
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Greet(modifier: Modifier = Modifier){
    var name = remmeber { mutableStateOf(value: "")}
    Column ( modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Arrangement.CenterHorizontally,
    ){

            TextField(value = "batatas",
                onValueChange = { newValue ->
                    print(newValue)
                })
        Button(onClick = {

        })
        {

        }
    }





}

@Preview(showBackground = true)
@Composable
fun GreetPreview(){
    Greet()
}


