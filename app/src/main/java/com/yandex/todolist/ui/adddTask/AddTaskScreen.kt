package com.yandex.todolist.ui.addTask

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yandex.todolist.R
import com.yandex.todolist.Screen
import com.yandex.todolist.data.Importance
import com.yandex.todolist.data.TodoItem
import com.yandex.todolist.data.TodoItemsRepository
import com.yandex.todolist.data.TodoItemsRepositoryImpl
import com.yandex.todolist.data.formatDate
import com.yandex.todolist.data.getUniqueId
import java.util.Date

@Composable
fun AddTaskScreen(navController: NavController, rep: TodoItemsRepository) {
    val id: String? = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<String>(Screen.AddTasksScreen.route)
    val task = remember {
        mutableStateOf<TodoItem?>(null)
    }
    if (id != null) {
        task.value = rep.getTask(id)
    }
    val descriptionState = remember { mutableStateOf(TextFieldValue(task.value?.text.orEmpty())) }
    var selectedPriority by remember { mutableStateOf(task.value?.importance ?: Importance.NORMAL) }
    var expanded by remember { mutableStateOf(false) }
    var showDateChooserDialog by remember { mutableStateOf(false) }
    var dateTime by remember { mutableStateOf<Date?>(task.value?.deadline) }

    if (showDateChooserDialog) {
        DatePickerModal(onDateSelected = {
            it?.let {
                dateTime = Date(it)
            }
        }, onDismiss = {
            showDateChooserDialog = false
        })
    }

    Column(
        modifier = Modifier
            .background(colorResource(R.color.back_primary))
            .fillMaxSize()
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "Back",
                    tint = colorResource(R.color.label_primary)
                )
            }
            Text(
                text = "СОХРАНИТЬ",
                color = colorResource(R.color.color_blue),
                fontWeight = W500,
                fontSize = 14.sp,
                modifier = Modifier
                    .clickable {
                        if (descriptionState.value.text.isNotBlank()) {
                            rep.addTask(
                                TodoItem(
                                    id = getUniqueId(),
                                    text = descriptionState.value.text,
                                    importance = selectedPriority,
                                    isCompleted = false,
                                    createdAt = dateTime ?: Date()
                                )
                            )
                            navController.popBackStack()
                        }
                    },
            )
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 104.dp, max = 428.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            value = descriptionState.value,
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                fontWeight = W400,
                fontSize = 16.sp,
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = colorResource(R.color.label_primary),
                unfocusedTextColor = colorResource(R.color.label_primary),
                focusedContainerColor = colorResource(R.color.back_secondary),
                unfocusedContainerColor = colorResource(R.color.back_secondary),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { descriptionState.value = it },
            placeholder = {
                Text(
                    fontSize = 16.sp,
                    fontWeight = W400,
                    text = "Что надо сделать?",
                    color = colorResource(R.color.label_tertiary)
                )
            }
        )
        Column(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(12.dp),
                    color = colorResource(R.color.back_primary)
                )
                .padding(top = 12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(horizontal = 16.dp)
                    .clickable { expanded = true },
            ) {
                Text(
                    text = "Важность",
                    color = colorResource(R.color.label_primary),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp
                )
                Text(
                    text = selectedPriority.text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (selectedPriority == Importance.entries[2]) colorResource(R.color.color_red) else colorResource(
                            R.color.label_tertiary
                        )
                    ),
                    fontSize = 14.sp
                )
                DropdownMenu(
                    modifier = Modifier
                        .width(164.dp)
                        .height(144.dp)
                        .shadow(
                            elevation = 3.dp,
                            shape = RoundedCornerShape(topStart = 2.dp)
                        )
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(topStart = 2.dp),
                            ambientColor = Color(0x1F000000),
                            spotColor = Color(0x1F000000)
                        )
                        .shadow(
                            elevation = 1.dp,
                            shape = RoundedCornerShape(topStart = 2.dp),
                            ambientColor = Color(0x24000000),
                            spotColor = Color(0x24000000)
                        )
                        .background(
                            color = colorResource(R.color.back_elevated),
                            shape = RoundedCornerShape(2.dp)
                        ),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    Importance.entries.forEachIndexed { index, priority ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    modifier = Modifier.height(48.dp),
                                    text = priority.text,
                                    color = if (index == 2) colorResource(R.color.color_red) else colorResource(
                                        R.color.label_primary
                                    ),
                                    fontSize = 16.sp,
                                    lineHeight = 16.sp
                                )
                            },
                            contentPadding = PaddingValues(16.dp, 0.dp, 16.dp, 0.dp),
                            onClick = {
                                selectedPriority = priority
                                expanded = false
                            }
                        )
                    }
                }
            }
            Divider(
                color = colorResource(R.color.support_separator),
                modifier = Modifier
                    .height(0.5.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Сделать до",
                        fontSize = 16.sp,
                        color = colorResource(R.color.label_primary)
                    )
                    dateTime?.let {
                        Text(
                            text = formatDate(it),
                            fontSize = 12.sp,
                            color = colorResource(R.color.color_blue)
                        )
                    }
                }
                Switch(
                    checked = (dateTime != null),
                    onCheckedChange = {
                        if (dateTime == null) {
                            showDateChooserDialog = true
                        } else dateTime = null
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(R.color.color_blue),
                        checkedTrackColor = colorResource(R.color.color_blue).copy(alpha = 0.3f),
                        uncheckedThumbColor = colorResource(R.color.back_elevated),
                        uncheckedTrackColor = colorResource(R.color.support_overlay),
                        uncheckedBorderColor = colorResource(R.color.back_primary),
                        checkedBorderColor = colorResource(R.color.back_primary)
                    ),
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        Divider(
            color = colorResource(R.color.support_separator),
            modifier = Modifier
                .height(0.5.dp)
                .fillMaxWidth()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp)
                .background(
                    shape = RoundedCornerShape(12.dp),
                    color = colorResource(R.color.back_secondary)
                )
                .clickable(id !=  "") {
                    rep.deleteTask(id.orEmpty())
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Удалить",
                fontSize = 16.sp,
                color = if (task.value == null) colorResource(R.color.label_disable) else colorResource(R.color.color_red)
            )
        }
    }
}

@Preview
@Composable
private fun AdddTaskScreenPrev() {
    val nav = rememberNavController()
    AddTaskScreen(navController = nav, rep = TodoItemsRepositoryImpl())
}