package com.hftamayo.onduties.feature_todo.presentation.todo_list

import com.hftamayo.onduties.feature_todo.domain.model.TodoItem
import com.hftamayo.onduties.feature_todo.domain.util.TodoItemOrder

sealed class TodoListEvent{
    data class Sort(val todoItemOrder: TodoItemOrder): TodoListEvent()
    data class Delete(val todoItem: TodoItem): TodoListEvent()
    data class ToggleCompleted(val todoItem: TodoItem): TodoListEvent()
    data class ToggleArchived(val todoItem: TodoItem): TodoListEvent()
    object undoDelete: TodoListEvent()

}
