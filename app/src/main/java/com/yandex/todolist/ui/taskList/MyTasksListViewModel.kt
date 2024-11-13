package com.yandex.todolist.ui.taskList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.todolist.data.TodoItem
import com.yandex.todolist.data.TodoItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyTasksListViewModel : ViewModel() {
    private val _todoListState = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoListState: StateFlow<List<TodoItem>> = _todoListState

    fun loadTodoList() {
        viewModelScope.launch {
            try {
                val todoList = repository?.getTodoList()
                if (todoList != null) {
                    _todoListState.value = todoList
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private val _uiState =
        MutableStateFlow(MyTasksListUiState())
    val uiState: StateFlow<MyTasksListUiState> = _uiState.asStateFlow()

    private var repository: TodoItemsRepository? = null
    fun deleteTask(id: String) {
        viewModelScope.launch {
            repository?.deleteTodoItem(id)
            _uiState.value.list.removeIf {
                it.id == id
            }
        }
    }

    fun initViewModel(rep: TodoItemsRepository) {
        if (repository == null) {
            repository = rep
        }
    }

    suspend fun getData() {
        _uiState.value.list.clear()
        _uiState.value.list.addAll(repository?.getTodoList(_uiState.value.selected).orEmpty())
    }

    suspend fun setVisible() {
        _uiState.update {
            it.copy(selected = it.selected.not())
        }
        _uiState.value.list.clear()
        _uiState.value.list.addAll(repository?.getTodoList(_uiState.value.selected).orEmpty())
    }

    fun done(id: String, state: Boolean) {
        repository?.changeState(id,state)
        getData()
    }
}