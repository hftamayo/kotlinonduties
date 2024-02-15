package com.hftamayo.onduties.feature_todo.data.repo

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.hftamayo.onduties.feature_todo.data.di.IoDispatcher
import com.hftamayo.onduties.feature_todo.data.local.TodoDao
import com.hftamayo.onduties.feature_todo.data.mapper.toLocalTodoItem
import com.hftamayo.onduties.feature_todo.data.mapper.toTodoItemListFromLocal
import com.hftamayo.onduties.feature_todo.data.remote.TodoApi
import com.hftamayo.onduties.feature_todo.domain.model.TodoItem
import com.hftamayo.onduties.feature_todo.domain.repo.TodoListRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.UnknownFormatConversionException

class TodoListRepoImpl(
    private val dao: TodoDao,
    private val api: TodoApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher

): TodoListRepo {
    override suspend fun getAllTodos(): List<TodoItem> {
        getAllTodosFromRemote()
        return dao.getAllTodoItems().toTodoItemListFromLocal()
    }

    override suspend fun getAllTodosFromLocalCache(): List<TodoItem> {
        return dao.getAllTodoItems().toTodoItemListFromLocal()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getAllTodosFromRemote() {
        return withContext(dispatcher) {
            try {
            } catch (e: Exception) {
                when(e){
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        Log.e("HTTP" "Error: No data from Remote")
                        if()
                        // handle network exception
                    }
                }
                // handle exception
            }
            val remoteTodoList = api.getAllTodos()
            dao.addTodoItems(remoteTodoList.map { it.toLocalTodoItem() })
        }
    }

    private fun isCacheEmpty(): Boolean {
        return dao.getAllTodoItems().isEmpty()
    }

    override suspend fun getSingleTodoItemById(id: Int): TodoItem? {
        return dao.getSingleTodoItemById(id)?.toTodoItem()
    }

    override suspend fun addTodoItem(todoItem: TodoItem) {
        val newId = dao.addTodoItem(todo.toLocalTodoItem())
        val id = newId.toInt()
        val url = "todo/$id.json"
        api.addTodo(url, todo.toRemoteTodoItem().copy(id = id))
    }

    override suspend fun updateTodoItem(todoItem: TodoItem) {
        dao.addTodoItem(todo.toLocalTodoItem())
        api.updateTodoItem(todo.id, todo.toRemoteTodoItem())

    }
    override suspend fun deleteTodoItem(todoItem: TodoItem) {
        TODO("Not yet implemented")
    }


}