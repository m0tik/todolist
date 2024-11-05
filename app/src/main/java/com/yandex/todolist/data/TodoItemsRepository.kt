package com.yandex.todolist.data

import java.util.Date
import kotlin.random.Random

fun Priority(): Importance {
    val fruits = Importance.entries
    return fruits[Random.nextInt(fruits.size)]
}

interface TodoItemsRepository {

    fun getTasks(): List<TodoItem>

    fun addTask(task: TodoItem)

    fun deleteTask(id: String)
}

class TodoItemsRepositoryImpl: TodoItemsRepository {
    private val list = MutableList<TodoItem>(6){ index ->
        TodoItem(
            id = index.toString(),
            text = "Купить что-то",
            importance = Priority(),
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