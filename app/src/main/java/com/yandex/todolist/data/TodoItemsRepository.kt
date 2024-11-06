package com.yandex.todolist.data

import java.util.Date
import kotlin.random.Random

fun Priority(): Importance {
    val importance = Importance.entries
    return importance[Random.nextInt(importance.size)]
}

interface TodoItemsRepository {

    fun getTasks(): List<TodoItem>

    fun addTask(task: TodoItem)

    fun deleteTask(id: String)
}

class TodoItemsRepositoryImpl: TodoItemsRepository {
    private val list = MutableList<TodoItem>(25){ index ->
        TodoItem(
            id = getUniqueId(),
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