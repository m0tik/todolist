package com.yandex.todolist.data

import android.util.Log
import java.util.Date
import kotlin.random.Random

fun Priority(): Importance {
    val importance = Importance.entries
    return importance[Random.nextInt(importance.size)]
}

interface TodoItemsRepository {

    fun getTasks(filtered: Boolean): List<TodoItem>

    fun getTask(id: String): TodoItem?

    fun addTask(task: TodoItem)

    fun changeState(id: String, isComplete: Boolean)

    fun deleteTask(id: String)
}

class TodoItemsRepositoryImpl: TodoItemsRepository {
    private val list = MutableList<TodoItem>(5){ index ->
        TodoItem(
            id = getUniqueId(),
            text = "Купить что-то",
            importance = Priority(),
            isCompleted = false,
            createdAt = Date()
        )
    }
    override fun getTasks(filtered: Boolean): List<TodoItem> {
        val result = if(filtered.not()) list.filter { it.isCompleted.not() } else  list
        Log.d("error",result.toString())
        return result
    }

    override fun getTask(id: String): TodoItem? {
        return list.firstOrNull { it.id == id }
    }

    override fun addTask(task: TodoItem) {
        list.add(task)
    }

    override fun changeState(id: String, isComplete: Boolean) {
        list.firstOrNull{it.id == id}?.isCompleted  = isComplete
    }

    override fun deleteTask(id: String) {
//        list.clear()
        list.removeIf { item ->
            item.id == id
        }
    }
}