package com.ikado.myapplication

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikado.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfigChangeExample()
        }
    }
}

@Composable
fun ConfigChangeExample() {
    val configuration = LocalConfiguration.current

    MyApplicationTheme {


        val context = LocalContext.current

        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Landscape(context)
            }
            else -> {
                Portrait(context)
            }
        }
    }
}

@Composable
fun Landscape(context:Context){
    var number by remember {
        mutableStateOf(0)
    }

    Row {
        Column() {

            btnToast(context = context , number = number , modifier = Modifier
                .padding(16.dp)
                .weight(1f,true)
            )
            btnCountUp(number = number, countUp = {number=it} , modifier = Modifier
                .padding(16.dp)
                .weight(1f,true))
            btnCountDown(number = number, countDown = {number=it} , modifier = Modifier
                .padding(16.dp)
                .weight(1f,true))
        }

        numberText(
            Modifier
                .weight(1f)
                .background(Color.Yellow)
                .padding(16.dp)
                .fillMaxHeight()
            ,number)
    }
}



@Composable
fun Portrait(context:Context) {

    var number by remember {
        mutableStateOf(0)
    }

    Column(Modifier.padding(16.dp)) {
        btnToast(context = context,number = number, modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth())
        numberText(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
            .background(Color.Yellow)
            ,number = number
        )
        botBar(number= number,
            countUp = {number=it},
            countDown = {number=it},
            modUp = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            modDown = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun btnToast(context:Context,number: Int,modifier: Modifier){
    Button(onClick = { Toast.makeText(context, "$number", Toast.LENGTH_LONG).show() },
        modifier = modifier) {
        Text(text = stringResource(id = R.string.toast))
    }
}



@Composable
fun numberText(modifier: Modifier,number:Int){
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "$number",
            fontSize = 160.sp,
            textAlign = TextAlign.Center,
            color = Color.Cyan
        )
    }
}

@Composable
fun btnCountUp(number:Int,countUp:(Int)->Unit,modifier: Modifier){
    Button(onClick = {countUp(number+1)},
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.countUp))
    }
}

@Composable
fun btnCountDown(number:Int,countDown:(Int)->Unit,modifier: Modifier){
    Button(onClick = {countDown(number-1)},
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.countUp))
    }
}

@Composable
fun botBar(number:Int,countUp:(Int)->Unit,countDown:(Int)->Unit,modUp:Modifier,modDown:Modifier){
    Row() {
        Box(modifier = Modifier.weight(1f)
        ){
            btnCountUp(number,countUp,modUp)
        }
        Box(modifier = Modifier.weight(1f)
        ){
            btnCountDown(number,countDown,modDown)
        }

    }
}
