package com.yandex.todolist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yandex.todolist.R

fun onClick() {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen() {
    val descriptionState = remember { mutableStateOf(TextFieldValue()) }
    val priorityOptions = listOf("Low", "Normal", "High")
    var selectedPriority by remember { mutableStateOf("Normal") }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(56.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "Back",
                )
            }
            Text(
                text = "Сохранить",
                color = Color.Blue,
                fontWeight = Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .clickable { onClick() },
            )
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 104.dp)
                .padding(16.dp),
            value = descriptionState.value,
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                fontSize = 16.sp,
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = colorResource(R.color.label_primary),
                unfocusedTextColor = colorResource(R.color.label_primary),
                focusedContainerColor = colorResource(R.color.Back_Secondary),
                unfocusedContainerColor = colorResource(R.color.Back_Secondary),
                focusedIndicatorColor = Color.Transparent, // Hides focused underline
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { descriptionState.value = it },
            placeholder = {
                Text(
                    "Add your comment",
                    color = colorResource(R.color.Color_Gray)
                )
            }
        )
        Column() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
                    .clickable { expanded = true }
            ) {
                Text(
                    text = "Importance",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp
                )
                Text(
                    text = "$selectedPriority",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp
                )


            }
            DropdownMenu(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(start = 16.dp),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                priorityOptions.forEach { priority ->
                    DropdownMenuItem(
                        text = { Text(priority) },
                        onClick = {
                            selectedPriority = priority
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AdddTaskScreenPrev() {
    AddTaskScreen()
}