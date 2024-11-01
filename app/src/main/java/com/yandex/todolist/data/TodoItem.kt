package com.yandex.todolist.data

import java.util.Date

data class TodoItem(
    val id: String,
    val text: String,
    val importance: Importance,
    val isCompleted: Boolean,
    val createdAt: Date,
    val deadline: Date?=null,
    val modifiedAt: Date?=null
)

enum class Importance{
    LOW,
    NORMAL,
    HIGH
}