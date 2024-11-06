package com.yandex.todolist.ui.taskItem

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yandex.todolist.R
import com.yandex.todolist.Screen
import com.yandex.todolist.data.Importance
import com.yandex.todolist.data.TodoItemsRepositoryImpl

@Composable
fun TaskItem(
    navController: NavController,
    id: String,
    isChecked: Boolean,
    taskName: String,
    priority: Importance,
    deleteTask: (String) -> Unit,
) {
    var checkedState by remember { mutableStateOf(isChecked) }
    val unCheckedBoxColor = when (priority) {
        Importance.LOW -> colorResource(R.color.support_separator)
        Importance.NORMAL -> colorResource(R.color.support_separator)
        Importance.HIGH -> colorResource(R.color.color_red)
    }
    var offsetX by remember { mutableStateOf(0f) }
    val thresholdDelete = -200f
    val thresholdCheck = 200f
    val backgroundColor by animateColorAsState(
        targetValue = when {
            offsetX < 0 -> colorResource(R.color.color_red)
            offsetX > 0 -> colorResource(R.color.color_green)
            else -> colorResource(R.color.back_secondary)
        }
    )
    val rowHeight by animateDpAsState(
        targetValue = if (offsetX != 0f) 72.dp else 48.dp
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(rowHeight)
            .background(backgroundColor)
            .offset { IntOffset(offsetX.toInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (offsetX <= thresholdDelete) {
                            deleteTask(id)
                        }
                        else if (offsetX>=thresholdCheck){
                            checkedState = true
                        }
                        offsetX = 0f
                    }
                ) { change, dragAmount ->
                    if (dragAmount != 0f) {
                        offsetX = (offsetX + dragAmount).coerceIn(thresholdDelete * 1.2f, thresholdCheck * 1.2f)
                        change.consume()
                    }
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(rowHeight)
                .offset { IntOffset(offsetX.toInt(), 0) }
                .background(colorResource(R.color.back_secondary))
        )
        {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp, 0.dp, 16.dp, 0.dp)
            ) {
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
                        text = taskName,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f),
                        color = if (checkedState) colorResource(R.color.label_tertiary)
                        else colorResource(R.color.label_primary),
                        textDecoration = if (checkedState) TextDecoration.LineThrough else TextDecoration.None
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.chevron_right),
                    contentDescription = "Custom Drawable Icon",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            onClick = {
                                navController.navigate(Screen.AddTasksScreen.route)
                            }
                        ),
                    colorFilter = ColorFilter.tint(colorResource(R.color.label_tertiary))
                )
            }
        }
    }
}