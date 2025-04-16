package com.test.people.mycomposeapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class HomeViewModel: ViewModel() {

    val counter = mutableStateOf(0)

}