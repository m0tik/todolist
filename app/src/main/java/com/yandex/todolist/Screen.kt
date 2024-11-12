package com.yandex.todolist

sealed class Screen(val route: String) {
    data object MyTasksListsScreen: Screen("TasksListsScreen")
    data object AddTasksScreen: Screen("AddTasksScreen")
}