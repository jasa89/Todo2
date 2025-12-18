package com.example.todo2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo2.ui.screens.TodoScreen
import com.example.todo2.ui.theme.Todo2Theme
import com.example.todo2.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Todo2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoScreen(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TodoScreen(modifier: Modifier = Modifier, todoViewModel: TodoViewModel = viewModel()) {
    TodoList(todos = todoViewModel.todos)
}

@Composable
fun TodoList(modifier: Modifier = Modifier, todos: List<String>) {
    LazyColumn (
    modifier = modifier.padding((8.dp))
    ) {
    items(todos) {
    todo ->
    Text(
        text = todo,
        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
    )
}

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Todo2Theme {
        TodoScreen()
    }
}