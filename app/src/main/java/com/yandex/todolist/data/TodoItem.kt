package com.yandex.todolist.data

import com.google.gson.annotations.SerializedName
import java.util.Date

data class TodoItem(
    @SerializedName("id")
    val id: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("importance")
    val importance: Importance,

    @SerializedName("done")
    var done: Boolean,

    @SerializedName("created_at")
    val createdAt: Date,

    @SerializedName("deadline")
    val deadline: Date? = null,

    @SerializedName("modified_at")
    val modifiedAt: Date? = null,

    @SerializedName("color")
    val color: String? = null,

    @SerializedName("tags")
    val tags: List<String>? = emptyList()
)

enum class Importance(val text: String) {
    @SerializedName("normal")
    NORMAL("Нет"),

    @SerializedName("low")
    LOW("Низкий"),

    @SerializedName("high")
    HIGH("!! Высокий")
}