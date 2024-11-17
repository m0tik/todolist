package com.yandex.todolist.ui.taskList

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.yandex.todolist.data.TodoItem

data class MyTasksListUiState(
    var list: SnapshotStateList<TodoItem> = mutableStateListOf(),
    val selected: Boolean = true
)