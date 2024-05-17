package com.hftamayo.onduties.feature_todo.presentation.todo_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hftamayo.onduties.core.presentation.components.CompleteButton
import com.hftamayo.onduties.core.presentation.components.getTodoColors
import com.hftamayo.onduties.feature_todo.domain.model.TodoItem
import com.hftamayo.onduties.ui.theme.OnDutiesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemCard(
    todo: TodoItem,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onCompleteClick: () -> Unit,
    onArchiveClick: () -> Unit,
    onCardClick: () -> Unit
) {
    val todoColors = getTodoColors(todo = todo)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        onClick = onCardClick,
        colors = CardDefaults.cardColors(containerColor = todoColors.backgroundColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            CompleteButton(onCompleteClick, todoColors.checkColor, todo.completed)
            Text(
                text = todo.title,
                color = todoColors.textColor,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Row (
            verticalAlignment = Alignment.Top
        ){
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ){
                Text(
                    text = todo.description,
                    color = todoColors.textColor,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }

    }
}

@Preview
@Composable
fun TodoItemCardPreview() {
    OnDutiesTheme {
        TodoItemCard(
            todo = TodoItem(
                title = "Subscribe to my channel",
                description = "Keep Learning Kotlin Language",
                timestamp = System.currentTimeMillis(),
                completed = true,
                archived = false,
                id = 0
            ),
            onDeleteClick = {},
            onCompleteClick = {},
            onArchiveClick = {},
            onCardClick = {}
        )
    }
}