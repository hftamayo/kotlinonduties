package com.hftamayo.onduties.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hftamayo.onduties.feature_todo.domain.model.TodoItem


data class TodoItemColors (
    val backgroundColor: Color,
    val textColor: Color,
    val archiveIconColor: Color,
    val checkColor: Color
)

@Composable
fun getTodoColors(todo: TodoItem): TodoItemColors {
    return if (todo.archived) {
        TodoItemColors(
            backgroundColor = Color(0xFFE0E0E0),
            textColor = Color(0xFF9E9E9E),
            archiveIconColor = Color(0xFF9E9E9E),
            checkColor = Color(0xFF9E9E9E)
        )
    } else {
        TodoItemColors(
            backgroundColor = Color(0xFFFFFFFF),
            textColor = Color(0xFF000000),
            archiveIconColor = Color(0xFF000000),
            checkColor = Color(0xFF000000)
        )
    }
}