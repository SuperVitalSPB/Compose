package com.test.people.mycomposeapplication

import HomeScreenMain
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.test.people.mycomposeapplication.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

/*
        val counter = mutableStateOf(0)

        val checked = mutableStateOf(true)
        val text = mutableStateOf("some text")
*/
        setContent {
            HomeScreenMain()
        }

/*        setContent {
            HomeScreen18()
        }*/
/*
            Column(modifier = Modifier.fillMaxSize()) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "userList",
                    modifier = Modifier.weight(1f)
                ) {
                    composable("userList") { UserListScreen(
                        onUser1Click = { navController.navigate("user/1") },
                        onUser2Click = { navController.navigate("user/2") }
                    ) }
                    composable(
                        route = "user/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.StringType })
                    ) {
                        val userId = it.arguments?.getString("id")
                        UserScreen(userId)
                    }
                }

                Text(
                    text = "Users",
                    modifier = Modifier.padding(bottom = 650.dp)
                        .clickable { navController.navigate("userList") }
                )
            }


*/

/*
            Column(modifier = Modifier.fillMaxSize()) {
                var route by remember { mutableStateOf("userList") }

                Box(modifier = Modifier.weight(1f)) {
                    when (route) {
                        "userList" -> UserListScreen(
                            onUser1Click = { route = "user/1" },
                            onUser2Click = { route = "user/2" }
                        )
                        "user/1" -> UserScreen("1")
                        "user/2" -> UserScreen("2")
                    }
                }

                Text(
                    text = "Users",
                    modifier = Modifier.clickable { route = "userList" }
                )
            }
*/



            // HomeScreenViewModel12()
            // HomeScreenLazyColumn11()
            // HomeScreen()
            // SomeItem("xxxxxxxxxxxxxxxxxx")
/*            HomeScreenCheck()*/
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
//   }
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