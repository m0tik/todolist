package com.yandex.todolist.data

import java.util.Date

data class TodoItem(
    val id: String,
    val text: String,
    val importance: Importance,
    var isCompleted: Boolean,
    val createdAt: Date,
    val deadline: Date?=null,
    val modifiedAt: Date?=null
)

enum class Importance(val text: String){
    NORMAL("Нет"),
    LOW("Низкий"),
    HIGH( "!! Высокий")
}