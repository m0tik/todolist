package com.yandex.todolist.data

import com.yandex.todolist.retrofit.TodoApiService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

fun Priority(): Importance {
    val importance = Importance.entries
    return importance[Random.nextInt(importance.size)]
}

class TodoItemsRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://beta.mrdekk.ru/todo/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: TodoApiService = retrofit.create(TodoApiService::class.java)
    suspend fun getTodoList(): List<TodoItem>{
        return api.getTodoList()
    }

    suspend fun addTodoItem(todoItem: TodoItem) {
        api.addTodoItem(todoItem)
    }

    suspend fun updateTodoItem(id:String,todoItem: TodoItem) {
        api.updateTodoItem(todoItem.id, todoItem)
    }

    suspend fun deleteTodoItem(id: String) {
        api.deleteTodoItem(id)
    }

    fun changeState(id: String, state: Boolean) {

    }
}