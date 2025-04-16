package com.test.people.mycomposeapplication

import ClickCounter
import HomeScreen
import HomeScreenCheck
import HomeScreenImage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.test.people.mycomposeapplication.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val counter = mutableStateOf(0)

        val checked = mutableStateOf(true)
        val text = mutableStateOf("some text")

        setContent {
            HomeScreenCheck()
/*
            ClickCounter (
                counterValue = counter.value,
                onCounterClick = {counter.value++}
            )
*/
/*
            HomeScreen(
                text = text,
                onValueChange = { newText ->
                    text.value = newText
                }
            )
*/
/*            HomeScreen(
                checked = checked,
                onCheckedChange = { newCheckedValue ->
                    checked.value = newCheckedValue
                }
            )*/
            /*HomeScreen(
                counter = counter,
                onCounterClick = {
                    counter.value++
                }
            )*/
            // HomeScreenImage()
            // HomeScreen()
            // HomeScreen(listOf("one", "two", "three"))
            // HomeScreen(emptyList())
/*
            MyComposeApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
*/
        }
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
    MyComposeApplicationTheme {
        Greeting("Android")
    }
}