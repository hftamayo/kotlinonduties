package com.hftamayo.onduties.feature_todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hftamayo.onduties.feature_todo.presentation.todo_list.TodoListScreen
import com.hftamayo.onduties.feature_todo.presentation.todo_list.TodoListViewMode
import com.hftamayo.onduties.feature_todo.presentation.util.Screen
import com.hftamayo.onduties.ui.theme.OnDutiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val listViewModel: TodoListViewModel = hiltViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.TodoItemListScreen.route
                    ) {
                        composable(route = Screen.TodoItemListScreen.route) {
                            TodoListScreen(
                                navController = navController,
                                viewModel = listViewModel,
                            )
                        }
                        composable(
                            route = Screen.TodoNewUpdateScreen.route
                        ) {
                            //TODO
                        }
                    }
                }
            }
        }
    }
}

