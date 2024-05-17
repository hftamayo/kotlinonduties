package com.hftamayo.onduties.feature_todo.presentation.todo_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hftamayo.onduties.core.presentation.components.CompleteButton
import com.hftamayo.onduties.core.presentation.components.getTodoColors
import com.hftamayo.onduties.feature_todo.domain.model.TodoItem
import com.hftamayo.onduties.ui.theme.OnDutiesTheme

@Composable
fun TodoItemCard (
    todo: TodoItem,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onCompleteClick: () -> Unit,
    onArchiveClick: () -> Unit,
    onCardClick: () -> Unit
){
    val todoColors = getTodoColors(todo = todo)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        onClick = onCardClick,
        colors = CardDefaults.cardColors(containerColor = todoColors.backgroundColor)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            CompleteButton(onCompleteClick, todoColors.checkColor, todo.completed)

        }

    }
}

@Preview
@Composable
fun TodoItemCardPreview() {
    OnDutiesTheme {
        TodoItemCard(
            todo = TodoItem(
                title = "Kotlin Language",
                description = "Keep Learning Kotlin Language",
                timestamp = System.currentTimeMillis(),
                completed = true,
                archived = false,
                id = 1
            ),
            onDeleteClick = {},
            onCompleteClick = {},
            onArchiveClick = {},
            onCardClick = {}
        )
    }
}