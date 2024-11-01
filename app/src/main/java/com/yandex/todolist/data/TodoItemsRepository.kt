package com.yandex.todolist.data

import java.util.Date

interface TodoItemsRepository {

    fun getTasks(): List<TodoItem>

    fun addTask(task: TodoItem)

    fun deleteTask(id: String)
}

class TodoItemsRepositoryImpl: TodoItemsRepository {
    private val list = MutableList<TodoItem>(20){ index ->
        TodoItem(
            id = index.toString(),
            text = "text",
            importance = Importance.LOW,
            isCompleted = false,
            createdAt = Date()
        )
    }

    override fun getTasks(): List<TodoItem> {
        return list
    }

    override fun addTask(task: TodoItem) {
        list.add(task)
    }

    override fun deleteTask(id: String) {
        list.removeIf { item ->
            item.id == id
        }
    }
}