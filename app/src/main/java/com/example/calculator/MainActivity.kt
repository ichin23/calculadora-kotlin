package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Calculator(modifier : Modifier){
    var exp by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Box(Modifier.height(250.dp).fillMaxWidth()){Text(
            text = exp,fontSize = 40.sp,
            modifier = Modifier.align(Alignment.BottomEnd),
maxLines = 1,


        )}
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(value = "C", onClick = { exp=""})
                Botao(value = "(", onClick = { exp+="("})
                Botao(value = ")", onClick = { exp+=")"})
                Botao(value = "+", onClick = { exp+="+"})
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(value = 7, onClick = { exp+=7})
                Botao(value = 8, onClick = { exp+=8})
                Botao(value = 9, onClick = { exp+=9})
                Botao(value = "-", onClick = { exp+="-"})
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(value = 4, onClick = { exp+=4})
                Botao(value = 5, onClick = { exp+=5})
                Botao(value = 6, onClick = { exp+=6})
                Botao(value = "/", onClick = { exp+="/"})
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(value = 1, onClick = { exp+=1})
                Botao(value = 2, onClick = { exp+=2})
                Botao(value = 3, onClick = { exp+=3})
                Botao(value = "*", onClick = { exp+="*"})
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(value = ".", onClick = { exp+="."})
                Botao(value = 0, onClick = { exp+=0})
                Botao(value = "", onClick = { })
                Botao(value = "=", onClick = {
                    exp = Expression(exp).calculate().toString()
                    if (exp.split(".")[1] =="0"){
                        exp = exp.split(".")[0]
                    }
                })
            }
        }
    }
}

@Composable
fun Botao(value: Any, onClick:()->Unit){
    TextButton(onClick = onClick) {
        Text(text = "$value", fontSize = 30.sp)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorTheme {
        Greeting("Android")
    }
}