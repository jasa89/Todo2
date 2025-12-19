package com.example.todo2.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo2.model.Todo
import com.example.todo2.model.TodosApi
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface TodoUiState {
    data class Success(val todos: List<Todo>) : TodoUiState
    object Error : TodoUiState
    object Loading : TodoUiState
}

class TodoViewModel : ViewModel() {

    var todoUiState: TodoUiState by mutableStateOf(TodoUiState.Loading)
        private set

    init {
        getTodosList()
    }



    fun getTodosList() {
        viewModelScope.launch {

            todoUiState = TodoUiState.Loading
            todoUiState = try {

                val todoListFromApi = TodosApi.getInstance().getTodos()
                TodoUiState.Success(todoListFromApi)
            } catch (e: IOException) {

                Log.e("TODO_VIEWMODEL_ERROR", "Network error fetching todos: ${e.message}", e)
                TodoUiState.Error
            } catch (e: Exception) {

                Log.e("TODO_VIEWMODEL_ERROR", "Failed to fetch todo list: ${e.message}", e)
                TodoUiState.Error
            }
        }
    }
}
