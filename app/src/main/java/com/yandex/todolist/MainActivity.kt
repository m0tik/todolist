package com.yandex.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.content.pm.ShortcutInfoCompat.Surface
import com.yandex.todolist.data.Importance
import com.yandex.todolist.data.TodoItemsRepository
import com.yandex.todolist.data.TodoItemsRepositoryImpl
import com.yandex.todolist.ui.AddTaskScreen
import com.yandex.todolist.ui.MyTasksListScreen
import com.yandex.todolist.ui.TaskItem
import com.yandex.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rep: TodoItemsRepository = TodoItemsRepositoryImpl()
        setContent {
            ToDoListTheme {
                Surface(modifier = Modifier.background(color = colorResource(R.color.Back_Primary))){
                    MyTasksListScreen(rep = rep)
                }
            }
        }
    }
}