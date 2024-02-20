package com.hftamayo.onduties.feature_todo.domain.util

sealed class SortingDirection {
    object Up: SortingDirection()
    object Down: SortingDirection()

}