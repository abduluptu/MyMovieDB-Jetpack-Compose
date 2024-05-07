package com.soha.infotech.mymoviedb.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.soha.infotech.mymoviedb.data.models.MovieInfo
import com.soha.infotech.mymoviedb.viewmodel.MovieViewModel

//Step8: Create a Home Screen

@Composable
fun HomeScreen(viewModel: MovieViewModel, navController: NavController) {

    var movies by remember {
        mutableStateOf(listOf<MovieInfo>())
    }

    viewModel.movie.observeForever {
        movies = it.results
    }

    LaunchedEffect(Unit) {
        viewModel.fetchMovies("f6c06b6a1cc549d810d4fb194b9d7633", 1)
    }

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            Modifier.padding(4.dp)
        )
        {
            items(movies) {
                MovieItem(movieInfo = it, viewModel = viewModel, navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(movieInfo: MovieInfo, viewModel: MovieViewModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        onClick = {
            viewModel.setMovieInfo(movieInfo = movieInfo)
            navController.navigate("details")
        }
    ) {
        GlideImage(
            model = "https://image.tmdb.org/t/p/w500${movieInfo.poster_path}",
            contentDescription = "Poster",
            Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(4.dp)
        ) {
            Text(
                text = movieInfo.title, style = MaterialTheme.typography.titleMedium
            )
            Text(text = movieInfo.release_date)
        }
    }
}
