package com.hftamayo.onduties.feature_todo.data.remote.dto

data class RemoteTodoItem(
    val title: String,
    val description: String,
    val timestamp: Long,
    val completed: Boolean,
    val archived: Boolean,
    val id: Int?
)
