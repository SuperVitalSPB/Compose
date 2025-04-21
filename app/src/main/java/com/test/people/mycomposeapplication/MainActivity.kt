package com.test.people.mycomposeapplication

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.test.people.mycomposeapplication.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposeApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        draList = listOf(
                            DrawerItemHome(getString(R.string.dra_item_home)),
                            DrawerItemContact(getString(R.string.dra_item_contact)),
                            DrawerItemAbout(getString(R.string.dra_item_about))
                        ),
                        textHome = getString(R.string.app_name_short),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}