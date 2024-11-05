package com.yandex.todolist.ui.taskItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yandex.todolist.R
import com.yandex.todolist.data.Importance

@Composable
fun TaskItem(isChecked: Boolean, taskName: String, priority: Importance) {
    var checkedState by remember { mutableStateOf(isChecked) }
    val unCheckedBoxColor = when (priority) {
        Importance.LOW -> colorResource(R.color.support_separator)
        Importance.NORMAL -> colorResource(R.color.support_separator)
        Importance.HIGH -> colorResource(R.color.color_red)
    }
    Column(
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .background(colorResource(R.color.back_secondary))
                .padding(16.dp, 12.dp, 16.dp, 12.dp)
        )
        {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = {
                        checkedState = !checkedState
                    },
                    colors = CheckboxColors(
                        checkedCheckmarkColor = colorResource(R.color.back_secondary),
                        uncheckedCheckmarkColor = colorResource(R.color.back_secondary),
                        checkedBoxColor = colorResource(R.color.color_green),
                        uncheckedBoxColor = if (priority == Importance.HIGH) colorResource(R.color.color_red).copy(
                            alpha = 0.3f
                        ) else colorResource(R.color.back_secondary),
                        disabledCheckedBoxColor = unCheckedBoxColor,
                        disabledUncheckedBoxColor = unCheckedBoxColor,
                        disabledIndeterminateBoxColor = unCheckedBoxColor,
                        checkedBorderColor = colorResource(R.color.color_green),
                        uncheckedBorderColor = unCheckedBoxColor,
                        disabledBorderColor = unCheckedBoxColor,
                        disabledUncheckedBorderColor = unCheckedBoxColor,
                        disabledIndeterminateBorderColor = unCheckedBoxColor
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    if (priority == Importance.LOW && !checkedState) {
                        Image(
                            painter = painterResource(R.drawable.priority_low),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            colorFilter = ColorFilter.tint(colorResource(R.color.color_gray))
                        )
                    }
                    if (priority == Importance.HIGH && !checkedState) {
                        Image(
                            painter = painterResource(R.drawable.priority_high),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            colorFilter = ColorFilter.tint(colorResource(R.color.color_red))
                        )
                    }
                    Text(
                        color = colorResource(R.color.label_primary),
                        text = buildAnnotatedString {
                            if (checkedState) {
                                withStyle(
                                    style = androidx.compose.ui.text.SpanStyle(
                                        textDecoration = TextDecoration.LineThrough,
                                        color = colorResource(R.color.label_tertiary)
                                    )
                                ) {
                                    append(taskName)
                                }
                            } else {
                                append(taskName)
                            }
                        },
                        fontSize = 16.sp,
                    )
                }
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.info_outline),
                    contentDescription = "Custom Drawable Icon",
                )
            }
        }
        Divider(
            color = colorResource(R.color.support_separator),
            modifier = Modifier
                .height(0.5.dp)
                .fillMaxWidth()
                .padding(start = 52.dp)
        )
    }
}


@Preview
@Composable
private fun TaskItemPrev() {
    Column {
        TaskItem(isChecked = false, taskName = "To Do App Yaratish", Importance.LOW)
        TaskItem(isChecked = false, taskName = "To Do App Yaratish", Importance.NORMAL)
        TaskItem(isChecked = false, taskName = "To Do App Yaratish", Importance.HIGH)
        TaskItem(isChecked = true, taskName = "To Do App Yaratish", Importance.LOW)
        TaskItem(isChecked = true, taskName = "To Do App Yaratish", Importance.NORMAL)
        TaskItem(isChecked = true, taskName = "To Do App Yaratish", Importance.HIGH)
    }
}