package com.hftamayo.onduties.feature_todo.data.repo

import com.hftamayo.onduties.feature_todo.data.local.TodoDao
import com.hftamayo.onduties.feature_todo.data.remote.TodoApi
import com.hftamayo.onduties.feature_todo.domain.model.TodoItem
import com.hftamayo.onduties.feature_todo.domain.repo.TodoListRepo
import kotlinx.coroutines.CoroutineDispatcher

class TodoListRepoImpl(
    private val dao: TodoDao,
    private val api: TodoApi,
    private val dispatcher: CoroutineDispatcher
): TodoListRepo {
    override suspend fun getAllTodos(): List<TodoItem> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTodosFromLocalCache(): List<TodoItem> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTodosFromRemote() {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleTodoItemById(id: Int): TodoItem? {
        TODO("Not yet implemented")
    }

    override suspend fun addTodoItem(todoItem: TodoItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTodoItem(todoItem: TodoItem) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTodoItem(todoItem: TodoItem) {
        TODO("Not yet implemented")
    }
}