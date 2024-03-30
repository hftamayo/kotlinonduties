package com.hftamayo.onduties.feature_todo.domain.use_case

import com.hftamayo.onduties.core.util.TodoUseCaseStrings
import com.hftamayo.onduties.feature_todo.domain.model.TodoItem
import com.hftamayo.onduties.feature_todo.domain.repo.TodoListRepo
import com.hftamayo.onduties.feature_todo.domain.util.InvalidTodoItemException
import com.hftamayo.onduties.feature_todo.domain.util.SortingDirection
import com.hftamayo.onduties.feature_todo.domain.util.TodoItemOrder
import javax.inject.Inject

class TodoUseCases @Inject constructor(
    private val repo: TodoListRepo
) {
    suspend fun addTodoItem(todo: TodoItem) {
        if (todo.title.isBlank() || todo.description.isBlank()) {
            throw InvalidTodoItemException(TodoUseCaseStrings.EMPTY_TITLE_OR_DESCRIPTION)
        }
        repo.addTodoItem(todo)
    }

    suspend fun updateTodoItem(todo: TodoItem) {
        if (todo.title.isBlank() || todo.description.isBlank()) {
            throw InvalidTodoItemException(TodoUseCaseStrings.EMPTY_TITLE_OR_DESCRIPTION)
        }
        repo.updateTodoItem(todo)
    }

    suspend fun deleteTodoItem(todo: TodoItem) {
        repo.deleteTodoItem(todo)
    }

    suspend fun toggleCompleteTodoItem(todo: TodoItem) {
        repo.updateTodoItem(todo.copy(completed = !todo.completed))
    }

    suspend fun toggleArchiveTodoItem(todo: TodoItem) {
        repo.updateTodoItem(todo.copy(archived = !todo.archived))
    }

    suspend fun getTodoItemById(id: Int): TodoItem? {
        return repo.getSingleTodoItemById(id)
    }

    suspend fun getTodoItems(
        todoItemOrder: TodoItemOrder = TodoItemOrder.Time(SortingDirection.Down, true)
    ): TodoUseCaseResult {
        var todos = repo.getAllTodosFromLocalCache()

        if (todos.isEmpty()) {
            todos = repo.getAllTodos()
        }

        val filteredTodos = if (todoItemOrder.showArchived) {
            todos
        } else {
            todos.filter { !it.archived }
        }

        return when (todoItemOrder.sortingDirection) {
            is SortingDirection.Down -> {
                when (todoItemOrder) {
                    is TodoItemOrder.Title -> TodoUseCaseResult.Success(filteredTodos.sortedBy { it.title.lowercase() })
                    is TodoItemOrder.Title -> TodoUseCaseResult.Success(filteredTodos.sortedBy { it.title.lowercase() })
                    is TodoItemOrder.Title -> TodoUseCaseResult.Success(filteredTodos.sortedBy { it.title.lowercase() })
                }
            }

            is SortingDirection.Up -> {

            }
        }
    }
}

sealed class TodoUseCaseResult {
    data class Success(val todoItems: List<TodoItem>) : TodoUseCaseResult()
    data class Error(val message: String) : TodoUseCaseResult()
}