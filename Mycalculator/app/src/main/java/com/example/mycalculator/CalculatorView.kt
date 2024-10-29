package com.example.mycalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycalculator.ui.components.CalcButton


fun getDisplay() : Double {
    return getDisplay().toDouble()
}




@Composable
fun CalculatorView(modifier: Modifier = Modifier ){

    var display by remember { mutableStateOf("0") }
    var operand by remember { mutableDoubleStateOf(0.0) }
    var operation by remember { mutableStateOf("") }

    val onClear: () -> Unit = {
        display = "0" // Reset the display to "0"
    }

    fun setDisplay(value: Double) {
        if ( value % 1 == 0.0) {
            display = value.toInt().toString()
        } else {
            display = value.toString()
        }
    }

    var userIsInTheMiddleOfTyping by remember {
        mutableStateOf(false)
    }

    val onNumPress : (String) -> Unit = { num ->
        if(userIsInTheMiddleOfTyping) {
            if (display == "0") {
                if (num == ".") {
                    display = "0."
                } else {
                    display = num
                }
            } else {
                if (num == ".") {
                    if (!display.contains(".")) {
                        display += num
                    }
                } else {
                    display += num
                }
            }
        }else{
            display = num
        }

        userIsInTheMiddleOfTyping = true
    }

    val onOperationPressed : (String) -> Unit = { op ->
        userIsInTheMiddleOfTyping = false
        if (operation.isNotEmpty()) {
            val result = when (operation) {
                "+" -> operand + getDisplay()
                "-" -> operand - getDisplay()
                "*" -> operand * getDisplay()
                "/" -> operand / getDisplay()
                else -> {
                    operation = ""
                    getDisplay()
                }
            }
            setDisplay(result)
        }

        operand = display.toDouble()
        operation = op

    }


    Column (modifier = modifier){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = display,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.displayLarge)
        // cria um vazio propositado
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.displayLarge)

        Row{
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "AC",
                onButtonPress = { onClear() } // Call onClear when AC is pressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+/-",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "%",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "/",
                isOperation = true,
                onButtonPress = {  }
            )

        }
        Row{
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "7",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "8",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "9",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "x",
                isOperation = true,
                onButtonPress = { CalculatorBrain.Operation.MULT() }
            )

        }
        Row{
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "4",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "5",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "6",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "-",
                isOperation = true,
                onButtonPress = {  }
            )

        }
        Row{
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "1",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "2",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "3",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+",
                isOperation = true,
                onButtonPress = {  }
            )

        }
        Row{
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "CALC",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "0",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = ",",
                onButtonPress = onNumPress
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "=",
                isOperation = true,
                onButtonPress = {  }
            )

        }
    }

}


@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorView()
}