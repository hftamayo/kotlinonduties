package com.hftamayo.onduties.feature_todo.presentation.todo_list.components

import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import com.hftamayo.onduties.feature_todo.domain.util.TodoItemOrder

@Composable
fun sortingDrawerOptions(
    todoItemOrder: TodoItemOrder,
    onOrderChange: (TodoItemOrder) -> Unit
) {
    val titleSelected = todoItemOrder::class == TodoItemOrder.Title::class
    NavigationDrawerItem(
        label = {
                IconRow(
                    text = , isChecked = )

        },
        selected = false,
        onClick = {
    onOrderChange(TodoItemOrder.Title(todoItemOrder.sortingDirection, todoItemOrder.showArchived))
    })


}