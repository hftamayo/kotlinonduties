package com.hftamayo.onduties.feature_todo.presentation.todo_new_update

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hftamayo.onduties.feature_todo.data.di.IoDispatcher
import com.hftamayo.onduties.feature_todo.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class TodoNewUpdateViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = mutableStateOf(TodoNewUpdateState())
    val state = State<TodoNewUpdateState> = _state

    private val errorHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
        _state.value = state.value.copy(
            error = e.message,
            isLoading = false
        )
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
        object SaveTodo : UIEvent()
    }
}