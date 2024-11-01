package com.yandex.todolist.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskItem(isChecked: Boolean,taskName: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {

            }
        )

        Text(text = taskName)
    }
}

@Preview
@Composable
private fun TaskItemPrev() {
    TaskItem(isChecked = true, taskName = "Test")
}