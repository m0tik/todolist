package com.yandex.todolist.retrofit

import com.yandex.todolist.data.TodoItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoApiService {
    @GET("list")
    suspend fun getTodoList(): List<TodoItem>
    @POST("list")
    suspend fun addTodoItem(@Body newItem: TodoItem):Response<TodoItem>
    @PUT("list/{id}")
    suspend fun updateTodoItem(@Path("id") id: String, todoItem: TodoItem):Response<TodoItem>
    @DELETE("list/{id}")
    suspend fun deleteTodoItem(@Path("id") id: String):Response<Unit>
}