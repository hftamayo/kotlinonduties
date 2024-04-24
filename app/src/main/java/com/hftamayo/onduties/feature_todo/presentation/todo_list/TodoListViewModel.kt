package com.hftamayo.onduties.feature_todo.presentation.todo_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hftamayo.onduties.feature_todo.data.di.IoDispatcher
import com.hftamayo.onduties.feature_todo.domain.model.TodoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import com.hftamayo.onduties.feature_todo.domain.use_case.TodoUseCases
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class TodoListViewMode @Inject constructor(
    private val todoUseCase: TodoUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel(){
    private val _state = mutableStateOf(TodoListState())
    val state: State<TodoListState> = _state

    private var undoTodoItem: TodoItem? = null
    private val errorHandler = CoroutineExceptionHandler {_, e ->
        e.printStackTrace()
        _state.value = state.value.copy(
            error = e.message,
            isLoading = false
        )
    }

}
