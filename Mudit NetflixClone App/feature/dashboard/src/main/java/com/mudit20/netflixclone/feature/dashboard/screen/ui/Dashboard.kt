package com.mudit20.netflixclone.feature.dashboard.screen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mudit20.netflixclone.feature.dashboard.domain.domainModel.movie.Result
import com.mudit20.netflixclone.feature.dashboard.screen.ui.viewmodel.MovieViewModel

import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DashBoardSCreen(
    modifier: Modifier= Modifier,
    viewModel: MovieViewModel= hiltViewModel()
){
    val state=viewModel.moviestate.collectAsState()

    Box(modifier=Modifier.fillMaxSize()){
        when{
            state.value.isLoading->{
                CircularProgressIndicator(
                    modifier= Modifier.align(Alignment.Center)
                )

            }
            state.value.movies?.results?.isNotEmpty()==true->{
                TrendingMovieList(
                    movie=state.value.movies?.results
                )


            }
            state.value.error.isNotEmpty() ->{

            }

        }
    }

}


@Composable
fun TrendingMovieList(movie: List<Result>?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movie?.size?:0){
            MovieCard(movie = movie?.get(it))

        }
    }

}

@Composable
fun MovieCard(movie: Result?) {
    val imageUrl = movie?.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = CardDefaults.elevatedCardElevation(draggedElevation = 5.dp)
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = imageUrl,
                contentDescription = movie?.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f/3f)
                    .clip(RoundedCornerShape(8.dp))
                    .border(5.dp, Color.Black, RoundedCornerShape(8.dp))
            )
            Text(
                text = movie?.title ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )

            Text(text = movie?.overview ?: "", modifier = Modifier.padding(vertical = 4.dp))






            Row(modifier = Modifier.padding(vertical = 2.dp)) {
                Text(
                    text = "Release Date: ",
                    fontWeight = FontWeight.Bold
                )
                Text(text = movie?.releaseDate ?: "")
            }
            Row(modifier = Modifier.padding(vertical = 2.dp)) {
                Text(
                    text = "Rating: ",
                    fontWeight = FontWeight.Bold
                )
                Text(text = movie?.voteAverage.toString())
            }
        }
    }
}