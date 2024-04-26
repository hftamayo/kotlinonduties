package com.hftamayo.onduties.feature_todo.presentation.todo_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hftamayo.onduties.feature_todo.data.di.IoDispatcher
import com.hftamayo.onduties.feature_todo.domain.model.TodoItem
import com.hftamayo.onduties.feature_todo.domain.use_case.TodoUseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import com.hftamayo.onduties.feature_todo.domain.use_case.TodoUseCases
import com.hftamayo.onduties.feature_todo.domain.util.TodoItemOrder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewMode @Inject constructor(
    private val todoUseCase: TodoUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel(){
    private val _state = mutableStateOf(TodoListState())
    val state: State<TodoListState> = _state

    private var undoTodoItem: TodoItem? = null
    private var getTodoItemsJob: Job? = null
    private val errorHandler = CoroutineExceptionHandler {_, e ->
        e.printStackTrace()
        _state.value = state.value.copy(
            error = e.message,
            isLoading = false
        )
    }

    fun getTodoItems(todoItemOrder: TodoItemOrder){
        getTodoItemsJob?.cancel()

        getTodoItemsJob = viewModelScope.launch( dispatcher + errorHandler) {
            val result = todoUseCase.getTodoItems(
                todoItemOrder = todoItemOrder
            )
            when(result){
                is TodoUseCaseResult.Success -> {
                    _state.value = state.value.copy(
                        todoItems = result.todoItems,
                        todoItemOrder = todoItemOrder,
                        isLoading = false,
                        error = null
                    )
                }
                is TodoUseCaseResult.Error -> {
                    _state.value = state.value.copy(
                        error = "Could not retrieve Todo items: " + result.message,
                        isLoading = false
                    )
                }
            }
        }
    }

}
