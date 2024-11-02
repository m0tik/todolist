package com.yandex.todolist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yandex.todolist.R

@Composable
fun TaskItem(isChecked: Boolean,taskName: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp,12.dp,16.dp,12.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {

            }
        )

        Text(modifier = Modifier.weight(1f),
            text = taskName,
            overflow = TextOverflow.Ellipsis,
            )

        Image(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "Custom Drawable Icon",
        )

    }
}

@Preview
@Composable
private fun TaskItemPrev() {
    TaskItem(isChecked = true, taskName = "Test")
}