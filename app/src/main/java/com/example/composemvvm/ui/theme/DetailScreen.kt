package com.example.composemvvm.ui.theme

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composemvvm.R
import com.example.composemvvm.ui.theme.models.MovieItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(movie: MovieItem?, onClick: () -> Unit) {

    val list =movie?.genres
    val actors = movie?.actors
    val actorsList = actors?.split(",")?.map {
        it.trim()
    }
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(16.dp)


    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight()
                .fillMaxWidth()
        ) {

            Box(
                Modifier
                    .size(200.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(defaultDarkBlue)
                    .border(2.dp, color = Color.Red, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center


            ) {

                AsyncImage(
                    contentScale = ContentScale.FillBounds,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie?.posterUrl)
                        .setHeader("User-Agent", "Mozilla/5.0")
                        .error(R.drawable.ic_launcher_background)
                        .build(), contentDescription = "image"
                )


            }

            Text(
                "Title : ${movie?.title}",
                Modifier
                    .padding(horizontal = 10.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold

                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                list?.map {
                    SuggestionChip(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(start = 10.dp),
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = defaultDarkBlue,
                            labelColor = Color.White,


                            ),
                        border = BorderStroke(width = 2.dp,Color.Red),
                        onClick = { Log.d(it, "hello world") },
                        label = { Text(it,) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            movie?.plot?.let {
                Text(
                    it,
                    Modifier
                        .padding(horizontal = 10.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight =18.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Director : ${movie?.director}",
                Modifier
                    .padding(horizontal = 10.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold

                )
            )


            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                actorsList?.map {
                    SuggestionChip(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(start = 10.dp),
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = defaultDarkBlue,
                            labelColor = Color.White,


                            ),
                        border = BorderStroke(width = 2.dp,Color.Red),
                        onClick = { Log.d(it, "hello world") },
                        label = { Text(it,) }
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun DetailScreenPreview() {

    val list = listOf(
        "Crime",
        "Drama",
        "Music"
    )
    val actors = "Richard Gere, Gregory Hines, Diane Lane, Lonette McKee"
    val actorsList = actors.split(",").map {
        it.trim()
    }
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(16.dp)


    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight()
                .fillMaxWidth()
        ) {

            Box(
                Modifier
                    .size(200.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(defaultDarkBlue)
                    .border(2.dp, color = Color.Red, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center


            ) {

                AsyncImage(
                    contentScale = ContentScale.FillBounds,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUwODE3MDE0MV5BMl5BanBnXkFtZTgwNTk1MjI4MzE@._V1_SX300.jpg")
                        .setHeader("User-Agent", "Mozilla/5.0")
                        .error(R.drawable.ic_launcher_background)
                        .build(), contentDescription = "image"
                )


            }

            Text(
                "Title : Beetlejuice",
                Modifier
                    .padding(horizontal = 10.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold

                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                list.map {
                    SuggestionChip(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(start = 10.dp),
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = defaultDarkBlue,
                            labelColor = Color.White,


                        ),
                        border = BorderStroke(width = 2.dp,Color.Red),
                        onClick = { Log.d(it, "hello world") },
                        label = { Text(it,) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "The Cotton Club was a famous night club in Harlem. The story follows the people that visited the club, those that ran it, and is peppered with the Jazz music that made it so famous.",
                Modifier
                    .padding(horizontal = 10.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight =18.sp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Director : Francis Ford Coppola",
                Modifier
                    .padding(horizontal = 10.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold

                )
            )


            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                actorsList.map {
                    SuggestionChip(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(start = 10.dp),
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = defaultDarkBlue,
                            labelColor = Color.White,


                            ),
                        border = BorderStroke(width = 2.dp,Color.Red),
                        onClick = { Log.d(it, "hello world") },
                        label = { Text(it,) }
                    )
                }
            }

        }
    }

}