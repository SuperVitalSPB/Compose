package com.test.people.mycomposeapplication

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

const val TAG = "Lesson15Screen"

@Composable
fun UserListScreen(
    onUser1Click: () -> Unit,
    onUser2Click: () -> Unit,
) {
    Column (modifier = Modifier.padding(top=80.dp)){
        Text(text = "Users screen")
        Text(
            text = "User 1",
            modifier = Modifier.clickable(onClick = onUser1Click)
        )
        Text(
            text = "User 2",
            modifier = Modifier.clickable(onClick = onUser2Click)
        )
    }
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun UserScreen(
    id: String?,
    userViewModel: UserViewModel = viewModel()
) {
    Column (modifier = Modifier.padding(top=80.dp)) {
        Text(text = "User $id")
        Log.d(TAG, "user $id")
        Log.d(TAG, "viewModel ${userViewModel.hashCode().toHexString()}")
        Log.d(TAG,"viewModelStoreOwner ${LocalViewModelStoreOwner.current?.javaClass?.simpleName}")
    }
}