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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    var resultado by remember { mutableStateOf(false) }

    var exp by remember { mutableStateOf("") }

    fun add(str:String){
        println(resultado)
        if (resultado) {
            exp=str
            resultado=false
        }else{
            exp+=str
        }

    }

    Column(modifier = modifier.padding(16.dp)) {
        Box(
            Modifier
                .height(250.dp)
                .padding(vertical = 8.dp)
                .fillMaxWidth()){
            Text(
            text = exp, fontSize = 55.sp, fontWeight = FontWeight.W600,
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
                Botao(value = "C", color = Color(0xfff77e7e), onClick = { add("")})
                Botao(value = "(", onClick = {

                    add("(")
                })
                Botao(value = ")", onClick = { add(")")})
                Botao(value = "+",color=Color(0xff84acf0), onClick = { exp+="+"})
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(value = 7, onClick = { add("7")})
                Botao(value = 8, onClick = { add("8")})
                Botao(value = 9, onClick = { add("9")})
                Botao(value = "-",color=Color(0xff84acf0), onClick = { exp+="-"})
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(value = 4, onClick = { add("4")})
                Botao(value = 5, onClick = { add("5")})
                Botao(value = 6, onClick = { add("6")})
                Botao(value = "/",color=Color(0xff84acf0), onClick = { exp+="/"})
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(value = 1, onClick = { add("1")})
                Botao(value = 2, onClick = { add("2")})
                Botao(value = 3, onClick = { add("3")})
                Botao(value = "*", color=Color(0xff84acf0), onClick = { exp+="*"})
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(value = ".", onClick = { add(".")})
                Botao(value = 0, onClick = { add("0")})
                Botao(value = "<-",color=Color(0xff84acf0), onClick = {exp=exp.dropLast(1) })
                Botao(value = "=", color=Color(0xff366ecf), onClick = {
                    resultado=true
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
fun Botao(value: Any, onClick:()->Unit, color: Color=Color(0xffcad7ed)){
    TextButton(onClick = onClick,
        colors= ButtonDefaults.textButtonColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(50)) {
        Text(text = "$value", fontSize = 30.sp, color = Color.Black)
    }
}
