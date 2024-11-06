package com.yandex.todolist.ui.taskList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.todolist.data.TodoItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyTasksListViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(MyTasksListUiState())
    val uiState: StateFlow<MyTasksListUiState> = _uiState.asStateFlow()

    private var repository: TodoItemsRepository? = null
    fun deleteTask(id: String) {
        viewModelScope.launch {
            repository?.deleteTask(id)
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

    fun getData() {
        _uiState.value.list.clear()
        _uiState.value.list.addAll(repository?.getTasks(_uiState.value.selected).orEmpty())
    }

    fun setVisible() {
        _uiState.update {
            it.copy(selected = it.selected.not())
        }
        _uiState.value.list.clear()
        _uiState.value.list.addAll(repository?.getTasks(_uiState.value.selected).orEmpty())
    }

    fun isComplate(id: String, state: Boolean) {
        repository?.changeState(id,state)
        getData()
    }
}