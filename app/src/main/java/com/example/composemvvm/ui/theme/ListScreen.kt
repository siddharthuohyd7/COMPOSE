package com.example.composemvvm.ui.theme

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composemvvm.R
import com.example.composemvvm.ui.theme.models.Movie
import com.example.composemvvm.ui.theme.models.MovieItem
import com.example.composemvvm.ui.theme.viewmodels.MovieViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream


fun getJsonString(context: Context): String? {
    var json: String? = null
    try {
        val `is`: InputStream = context.assets.open("movies.json")
        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        json = String(buffer, charset("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }

    return json
}

@Composable
fun ListScreen(onCLick: (item: MovieItem) -> Unit) {


    val movieViewModel: MovieViewModel = hiltViewModel()


    val list: State<List<MovieItem>> = movieViewModel.moviesDataStateFlow.collectAsState()


    if (list.value.isEmpty()) Text(
        "Data is empty",
        style = TextStyle(fontSize = 20.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically),

        )
    else Box(
        Modifier
            .fillMaxHeight()
            .absolutePadding(top = 16.dp, bottom = 16.dp)
    ) {
        LazyColumn {
            items(count = list.value.size) { index ->
                GreetingPreview(list.value[index], onCLick)
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }


}


@Composable
fun GreetingPreview(movie: MovieItem, onCLick: (item: MovieItem) -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight()
            .padding(vertical = 5.dp, horizontal = 16.dp)
            .clickable { onCLick(movie) }


    ) {


        Box(
            Modifier
                .size(100.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(defaultDarkBlue)
                .border(2.dp, color = Color.Red, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center


        )

        {
            if (movie.posterUrl.isNotEmpty())
                AsyncImage(
                    contentScale = ContentScale.FillBounds,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.posterUrl)
                        .setHeader("User-Agent", "Mozilla/5.0")
                        .error(R.drawable.ic_launcher_background)
                        .build(), contentDescription = "image"
                )
            else
                Text(
                    movie.title.first().toString(),
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
        }

        Spacer(modifier = Modifier.width(15.dp))


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth(1f)
                .weight(1f)
        ) {

            Text(movie.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(5.dp))

            Text(movie.director, fontSize = 12.sp)


        }



        Icon(
            Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = "navigation",
            tint = Color.Gray,
            modifier = Modifier
                .size(35.dp)


        )

    }


}