package com.hftamayo.onduties.feature_todo.presentation.todo_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hftamayo.onduties.core.util.TodoListStrings
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

    fun onEvent(event: TodoListEvent){
        when(event){
            is TodoListEvent.Sort -> {
                if(
                    _state.value.todoItemOrder == event.todoItemOrder &&
                    _state.value.todoItemOrder.showArchived == event.todoItemOrder.showArchived &&
                ){
                    return
                }
                _state.value = state.value.copy(
                    todoItemOrder = event.todoItemOrder,
                )
                getTodoItems()
            }
            is TodoListEvent.Delete -> {
                viewModelScope.launch (dispatcher + errorHandler){
                    todoUseCases.deteteTodoItem(event.todo)
                    getTodoItems(_state.value.todoItemOrder)
                    undoTodoItem = event.todo
                }
            }
            is TodoListEvent.ToggleCompleted -> {
                toggleCompleted(event.todoItem)
            }
            is TodoListEvent.ToggleArchived -> {
                toggleArchived(event.todoItem)
            }
            is TodoListEvent.undoDelete -> {
                undoDelete()
            }
        }

    }

    fun getTodoItems(){
        getTodoItemsJob?.cancel()

        getTodoItemsJob = viewModelScope.launch( dispatcher + errorHandler) {
            val result = todoUseCase.getTodoItems(
                todoItemOrder = _state.value.todoItemOrder
            )
            when(result){
                is TodoUseCaseResult.Success -> {
                    _state.value = state.value.copy(
                        todoItems = result.todoItems,
                        todoItemOrder = _state.value.todoItemOrder,
                        isLoading = false,
                        error = null
                    )
                }
                is TodoUseCaseResult.Error -> {
                    _state.value = state.value.copy(
                        error = TodoListStrings.COULD_NOT_RETRIEVE_TODO_ITEMS + result.message,
                        isLoading = false
                    )
                }
            }
        }
    }

}
