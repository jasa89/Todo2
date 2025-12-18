package com.example.todo2.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo2.model.Todo // Import your data class
import com.example.todo2.model.TodosApi
import kotlinx.coroutines.launch


class TodoViewModel: ViewModel() {
    private val _todos = mutableStateOf<List<Todo>>(emptyList())

    val todos: State<List<Todo>> = _todos
    init {
        getTodosList()
    }

    private fun getTodosList() {
        viewModelScope.launch {
            try {

                val todoListFromApi = TodosApi.getInstance().getTodos()


                _todos.value = todoListFromApi

            } catch (e: Exception) {

                Log.e("TODO_VIEWMODEL_ERROR", "Failed to fetch todo list: ${e.message}", e)

            }
        }
    }
}


