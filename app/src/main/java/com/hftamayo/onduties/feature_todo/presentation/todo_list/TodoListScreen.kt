package com.hftamayo.onduties.feature_todo.presentation.todo_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.hftamayo.onduties.R
import com.hftamayo.onduties.core.util.ContentDescriptions
import com.hftamayo.onduties.core.util.TodoListStrings
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    navController: NavController,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val configuration = LocalConfiguration.current
    val isPortrait =
        configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    val backgroundImage = if (isPortrait) {
        R.drawable.background_portrait
    } else {
        R.drawable.background_landscape
    }

    LaunchedEffect(key1 = true) {
        viewModel.getTodoItems()
    }

    ModalNavigationDrawer(drawerContent = (
            drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = TodoListStrings.SORT_BY,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 34.sp,
                    lineHeight = 38.sp
                )
                Divider()
                SortingDrawerOptions(
                    todoItemOrder = state.todoItemOrder,
                    onOrderChange = { order ->
                        viewModel.onEvent(TodoListEvent.Sort(order))
                    }
                )
            }
            //TODO
        }
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        /*TODO*/
                    },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = ContentDescriptions.ADD_TODO,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = TodoListStrings.TODO_LIST,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                        scrolledContainerColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    navigationIcon = {},
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = ContentDescriptions.SORTING_MENU,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopBarState())
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) {  padding ->
            TodoListContent(
                todoItems = state.todoItems,
                onTodoItemClick = { todoId ->
                    navController.navigate("todoDetail/$todoId")
                },
                onTodoItemDelete = { todoId ->
                    viewModel.onEvent(TodoListEvent.Delete(todoId))
                },
                onTodoItemComplete = { todoId ->
                    viewModel.onEvent(TodoListEvent.Complete(todoId))
                },
                onTodoItemArchive = { todoId ->
                    viewModel.onEvent(TodoListEvent.Archive(todoId))
                }
            )
        }
    }



                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = ContentDescriptions.OPEN_DRAWER,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ContentDescriptions.SEARCH,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            },
        ) {

        }

    }) {

    }

}