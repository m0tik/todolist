package com.yandex.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yandex.todolist.data.TodoItemsRepository
import com.yandex.todolist.data.TodoItemsRepositoryImpl
import com.yandex.todolist.ui.addTask.AddTaskScreen
import com.yandex.todolist.ui.taskList.MyTasksListScreen
import com.yandex.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rep: TodoItemsRepository = TodoItemsRepositoryImpl()
        setContent {
            val navController = rememberNavController()
            ToDoListTheme {
                NavHost(navController = navController, startDestination = Screen.MyTasksListsScreen.route) {
                    composable(route = Screen.MyTasksListsScreen.route) {
                        MyTasksListScreen(navController = navController,rep = rep)
                    }
                    composable(route = Screen.AddTasksScreen.route) {
                        AddTaskScreen(navController,rep = rep)
                    }
                }
            }
        }
    }
}