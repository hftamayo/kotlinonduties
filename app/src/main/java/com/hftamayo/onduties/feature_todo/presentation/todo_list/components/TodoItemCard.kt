package com.hftamayo.onduties.feature_todo.presentation.todo_list.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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