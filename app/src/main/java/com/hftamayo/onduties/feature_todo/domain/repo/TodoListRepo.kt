package com.hftamayo.onduties.feature_todo.domain.repo

import com.hftamayo.onduties.feature_todo.domain.model.TodoItem

interface TodoListRepo {
    suspend fun getAllTodos(): List<TodoItem>
    suspend fun getAllTodosFromLocalCache(): List<TodoItem>
    suspend fun getAllTodosFromRemote()
    suspend fun getSingleTodoItemById(id: Int): TodoItem?
    suspend fun addTodoItem(todoItem: TodoItem)
    suspend fun deleteTodoItem(todoItem: TodoItem)
    suspend fun updateTodoItem(todoItem: TodoItem)
}