package com.elflin.examplemovieapps.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.elflin.examplemovieapps.data.DataSource
import com.elflin.examplemovieapps.model.Movie
import com.elflin.examplemovieapps.repository.MovieDBContainer

@Composable
fun MovieDetailView(movie: Movie, modifier: Modifier = Modifier){

    var isLikedView by remember { mutableStateOf(movie.isLiked) }

    Column(
        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(MovieDBContainer.BASE_IMG + movie.poster_path)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.title,
                modifier = Modifier
                    .height(400.dp),
                contentScale = ContentScale.Inside
            )
            FloatingActionButton(
                onClick = {},
                shape = CircleShape,
                modifier = Modifier.padding(end = 4.dp, bottom = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Heart",
                    tint = if (isLikedView) {
                        Color(0xFFEC407A)
                    } else {
                        Color(0xFF9C9C9C)
                    }
                )
            }
        }

        Text(
            text = movie.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2
        )
        Text(
            text = "(${movie.getYear()})",
            fontSize = 14.sp,
            textAlign = TextAlign.Right
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                tint = Color(0xFFFDCC0D)
            )
            Text(
                text = movie.vote_average.toString() + "/10.0",
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Text(
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp,
            text = movie.overview,
            textAlign = TextAlign.Justify,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MovieDetailPreview(){
    MovieDetailView(DataSource().loadMovie()[0])
}