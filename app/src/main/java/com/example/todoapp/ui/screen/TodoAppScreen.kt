package com.example.todoapp.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.screen.topappbar.TodoTopBar
import com.example.todoapp.utility.NavScreens
import com.example.todoapp.viewmodel.TodoAppViewModel
import com.example.todoapp.viewmodel.TodoFormViewModel
import com.example.todoapp.viewmodel.TopAppBarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoAppScreen(
    topAppBarViewModel: TopAppBarViewModel,
    todoAppViewModel: TodoAppViewModel,
    todoFormViewModel: TodoFormViewModel,
    navHostController: NavHostController = rememberNavController(),
) {
    val currentBackStack by navHostController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    Scaffold(
        topBar = { TodoTopBar(topAppBarViewModel, navHostController) },
        floatingActionButton = {
            if (currentRoute != NavScreens.TODO_FORM.name) {
                TodoFloatingActionButton {
                    navHostController.navigate(NavScreens.TODO_FORM.name)
                }
            }
        }
    ) {
        TodoNavHost(
            todoAppViewModel,
            todoFormViewModel,
            navController = navHostController,
            modifier = Modifier.padding(paddingValues = it)
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTodoAppScreen() {
    // TodoAppScreen(TopAppBarViewModel(), TodoAppViewModel())
}