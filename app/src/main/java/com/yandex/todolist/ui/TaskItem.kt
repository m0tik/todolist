package com.yandex.todolist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha
import com.yandex.todolist.R
import com.yandex.todolist.data.Importance

@Composable
fun TaskItem(isChecked: Boolean,taskName: String, priority:Importance) {
    var checkedState by remember { mutableStateOf(isChecked) }
    val unCheckedBoxColor = when(priority){
        Importance.LOW -> colorResource(R.color.Label_Disable)
        Importance.NORMAL -> colorResource(R.color.Label_Disable)
        Importance.HIGH -> colorResource(R.color.Color_Red)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(R.color.Back_Secondary))
        .padding(16.dp, 12.dp, 16.dp, 12.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Checkbox(
                checked = checkedState,
                onCheckedChange = {
                    checkedState = !checkedState
                },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = unCheckedBoxColor,
                    checkedColor = colorResource(R.color.Color_Green),
                )
        )
        if (priority == Importance.LOW && !checkedState) {
            Image(painter = painterResource(R.drawable.arrow_downward),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
        }
        if(priority==Importance.HIGH && !checkedState){
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = "!!",
                fontSize = 22.sp,
                color = colorResource(id = R.color.Color_Red)
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = buildAnnotatedString {
                if (checkedState) {
                    withStyle(style = androidx.compose.ui.text.SpanStyle(
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray
                    )) {
                        append(taskName)
                    }
                } else {
                    append(taskName)
                }
            },
            fontSize = 16.sp,
        )

        Image(
            modifier = Modifier
                .padding(start = 12.dp)
                .size(24.dp),
            painter = painterResource(id = R.drawable.info_outline),
            contentDescription = "Custom Drawable Icon",
        )

    }
}


@Preview
@Composable
private fun TaskItemPrev() {
    Column {
        TaskItem(isChecked = false, taskName = "To Do App Yaratish",Importance.LOW)
        TaskItem(isChecked = false, taskName = "To Do App Yaratish",Importance.NORMAL)
        TaskItem(isChecked = false, taskName = "To Do App Yaratish",Importance.HIGH)
        TaskItem(isChecked = true, taskName = "To Do App Yaratish",Importance.LOW)
        TaskItem(isChecked = true, taskName = "To Do App Yaratish",Importance.NORMAL)
        TaskItem(isChecked = true, taskName = "To Do App Yaratish",Importance.HIGH)
    }
}