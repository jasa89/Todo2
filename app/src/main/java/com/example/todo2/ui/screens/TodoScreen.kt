package com.example.todo2.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo2.model.Todo
import com.example.todo2.ui.theme.Todo2Theme
import com.example.todo2.viewmodel.TodoUiState
import com.example.todo2.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(modifier: Modifier = Modifier, todoViewModel: TodoViewModel = viewModel()) {
    Scaffold(

        topBar = { TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            title = { Text("Todos") }) }
    ) { innerPadding ->
        TodoScreen(
            modifier = modifier.padding(innerPadding),
            uiState = todoViewModel.todoUiState
        )
    }
}

@Composable
fun TodoScreen(modifier: Modifier = Modifier, uiState: TodoUiState) {
    when (uiState) {
        is TodoUiState.Loading -> LoadingScreen(modifier = modifier)
        is TodoUiState.Success -> TodoList(modifier = modifier, todos = uiState.todos)
        is TodoUiState.Error -> ErrorScreen(modifier = modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    // 3. Improved error UI
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Error retrieving data from API.")
    }
}

@Composable
fun TodoList(
    modifier: Modifier = Modifier,
    todos: List<Todo> // 4. FIX: Changed parameter from List<String> to List<Todo>
) {
    LazyColumn(
        modifier = modifier.padding(8.dp)
    ) {
        items(todos) { todo ->

            Column {
                Text(

                    text = todo.title,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                )
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    Todo2Theme {
        val sampleTodos = listOf(
            Todo(1, 1, "Buy groceries", false),
            Todo(1, 2, "Finish Compose tutorial", true)
        )
        TodoList(todos = sampleTodos)
    }
}

