package com.yandex.todolist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.yandex.todolist.R
import com.yandex.todolist.data.TodoItemsRepository
import com.yandex.todolist.data.TodoItemsRepositoryImpl

@Composable
fun MyTasksListScreen(rep: TodoItemsRepository) {
    Column(modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.Back_Secondary))) {
        LazyColumn {
            items(rep.getTasks()){ item ->
                TaskItem(
                   isChecked = item.isCompleted,
                    taskName = item.text,
                    priority = item.importance
                )
            }
        }
    }
}

@Preview
@Composable
private fun MyTasksListScreenPrev() {
    MyTasksListScreen(rep = TodoItemsRepositoryImpl())
}