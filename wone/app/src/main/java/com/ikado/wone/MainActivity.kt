package com.ikado.wone

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikado.wone.ui.theme.WoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Composable
fun TopBar(context: Context,text:Int) {
    Button(onClick = {
        Toast.makeText(context, "$text", Toast.LENGTH_LONG).show()
    }, modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Text(text = "Toast")
    }
}

@Composable
fun BotBar(updateCount:(Int)->Unit, count:Int) {
    Button(onClick = {
        updateCount(count+1)
    }, modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Text(text = "Count")
    }
}

@Composable
fun MidBar(text:Int,modifier: Modifier){
    Box(modifier = modifier){
        Text(text = "$text",
            textAlign = TextAlign.Center,
            fontSize = 240.sp,
            modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WoneTheme {
        val context = LocalContext.current
        var count by remember {
            mutableStateOf(0)
        }
        Column() {
            TopBar(context,count)
            Column(Modifier.weight(1f)
                .background(Color.Gray)
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                MidBar(text = count, modifier = Modifier)
            }
            BotBar(updateCount = {
                count = it
            },count = count)
        }
    }
}


