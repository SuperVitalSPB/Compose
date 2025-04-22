package com.test.people.mycomposeapplication.main

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.people.mycomposeapplication.R
import com.test.people.mycomposeapplication.ui.theme.MyComposeApplicationTheme
import com.test.people.mycomposeapplication.model.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposeApplicationTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                                label = { Text("Home") },
                                selected = true,
                                onClick = { }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Call, contentDescription = null) },
                                label = { Text("Call") },
                                selected = false,
                                onClick = { }
                            )
                        }
                    }
                    ){ innerPadding ->
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