package com.hftamayo.onduties.feature_todo.presentation.util

sealed class Screen(val route: String) {
    object TodoItemListScreen : Screen("todoItemList_screem")
    object TodoNewUpdateScreen : Screen("todoNewUpdate_screen")
}