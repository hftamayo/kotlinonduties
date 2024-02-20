package com.hftamayo.onduties.feature_todo.domain.use_case

import com.hftamayo.onduties.feature_todo.domain.model.TodoItem
import com.hftamayo.onduties.feature_todo.domain.repo.TodoListRepo
import com.hftamayo.onduties.feature_todo.domain.util.InvalidTodoItemException
import javax.inject.Inject

class TodoUseCases @Inject constructor(
    private val repo: TodoListRepo
){
    suspend fun addTodoItem(todo: TodoItem){
        if(todo.title.isBlank() || todo.description.isBlank()){
            throw InvalidTodoItemException()
        }
        repo.addTodoItem(todo)
    }
}