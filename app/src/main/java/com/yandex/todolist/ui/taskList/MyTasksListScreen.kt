package com.yandex.todolist.ui.taskList

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yandex.todolist.R
import com.yandex.todolist.Screen
import com.yandex.todolist.data.TodoItemsRepository
import com.yandex.todolist.data.TodoItemsRepositoryImpl
import com.yandex.todolist.ui.taskItem.TaskItem

@Composable
fun MyTasksListScreen(navController: NavController, rep: TodoItemsRepository) {
    var isVisible by remember { mutableStateOf(true) }
    var tasks by remember { mutableStateOf(rep.getTasks()) }
    val filteredTasks = if (isVisible) {
        tasks
    } else {
        tasks.filter { !it.isCompleted }
    }
    val deleteTask: (String) -> Unit = { id ->
        tasks = tasks.filter { it.id != id }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.back_primary))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(124.dp)
                .padding(start = 74.dp, top = 82.dp, bottom = 4.dp),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Мои дела",
                fontSize = 32.sp,
                fontWeight = W500,
                color = colorResource(R.color.label_primary)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .padding(start = 60.dp, end = 24.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                color = colorResource(R.color.label_tertiary),
                text = "Выполнено - 5"
            )
            Icon(
                painter = painterResource(if (isVisible) R.drawable.visibility else R.drawable.visibility_off),
                contentDescription = if (isVisible) "Visibility Icon" else "Invisibility Icon",
                tint = colorResource(R.color.color_blue),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { isVisible = !isVisible }
            )

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 8.dp)
                .shadow(8.dp, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .background(color = colorResource(R.color.back_secondary))
        ) {
            items(filteredTasks) { item ->
                TaskItem(
                    navController,
                    id = item.id,
                    isChecked = item.isCompleted,
                    taskName = item.text,
                    priority = item.importance,
                    deleteTask = deleteTask
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 40.dp,end= 16.dp)
    ) {
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(56.dp)
                .shadow(6.dp, shape = FloatingActionButtonDefaults.largeShape),
            shape = FloatingActionButtonDefaults.largeShape,
            containerColor = colorResource(R.color.color_blue),
            onClick = {
                navController.navigate(Screen.AddTasksScreen.route)
            },
        ) {
            Icon(
                painter = painterResource(R.drawable.add),
                contentDescription = "Floating action button.",
                tint = colorResource(R.color.color_white)
            )
        }
    }
}

@Preview
@Composable
private fun MyTasksListScreenPrev() {
    val nav = rememberNavController()
    MyTasksListScreen(navController = nav, rep = TodoItemsRepositoryImpl())
}