package com.example.todo2.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun TodoScreen() {

}

@Composable
fun TodoList(todos: List<String>) {

LazyColumn(
    modifier = Modifier.padding(8.dp)

){
    items(todos) { todo->
        Text(
            text = todo,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }

}