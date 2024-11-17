package com.yandex.todolist.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

inline fun <T> MutableList(size: Int, init: (index: Int) -> T): MutableList<T> {
    val list = ArrayList<T>(size)
    repeat(size) { index -> list.add(init(index)) }
    return list
}

fun formatDate(date: Date, pattern: String = "dd MMM yyyy", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(pattern, locale)
    return formatter.format(date)
}

fun getUniqueId(): String {
    return UUID.randomUUID().toString()
}