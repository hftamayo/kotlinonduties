package com.hftamayo.onduties.feature_todo.presentation.todo_new_update

sealed class TodoNewUpdateEvent{
    data class EnteredTitle(val title: String): TodoNewUpdateState()
}
