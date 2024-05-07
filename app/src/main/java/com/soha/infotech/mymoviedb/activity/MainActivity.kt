package com.soha.infotech.mymoviedb.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.soha.infotech.mymoviedb.ui.theme.MyMovieDBTheme
import com.soha.infotech.mymoviedb.ui.theme.screens.DetailScreen
import com.soha.infotech.mymoviedb.ui.theme.screens.HomeScreen
import com.soha.infotech.mymoviedb.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMovieDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/

                    // Added  here new code
                    // A surface container using the 'background' color from the theme
                    Surface(
                        //modifier = Modifier.fillMaxSize(),
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()

                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                HomeScreen(viewModel = viewModel, navController = navController)
                            }
                            composable("details") {
                                DetailScreen(viewModel = viewModel, navController = navController)
                            }
                        }
                    }
                }

            }
        }
    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyMovieDBTheme {
        Greeting("Android")
    }
}*/
