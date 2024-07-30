package com.example.composemvvm


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composemvvm.ui.theme.datastore.IS_LOGGED_IN
import com.example.composemvvm.ui.theme.datastore.dataStore
import com.example.composemvvm.ui.theme.datastore.getValueFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen()
        }

    }

}


@Composable
fun SplashScreen() {

    val currentRotation = remember { mutableStateOf<Float>(0f) }

    val isPlaying = remember { mutableStateOf(true) }

    val rotation = remember { Animatable(currentRotation.value) }
    val context = LocalContext.current
    LaunchedEffect(isPlaying.value) {
        if (isPlaying.value) {
            // Infinite repeatable rotation when is playing
            rotation.animateTo(
                targetValue = currentRotation.value + 360.0f,
                animationSpec = repeatable(
                    iterations = 1,
                    animation = tween(2000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {

                currentRotation.value = value

            }
        }

        context.dataStore.getValueFlow(IS_LOGGED_IN, false)
            .collect { value ->
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("is_logged_in",value)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                val options = ActivityOptions.makeCustomAnimation(context, R.anim.animation_enter, R.anim.animation_leave).toBundle()
                context.getActivity()?.startActivity(intent,options)
            }



    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White, shape = CircleShape)
        .wrapContentHeight(align = Alignment.CenterVertically)
        .wrapContentWidth(align = Alignment.CenterHorizontally)
        .rotate(rotation.value)
        .clickable {
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.splash_icon),
            contentDescription = "Register",
            tint = Color.Unspecified


        )
    }
}

@Preview()
@Composable
fun PreviewScreen() {
    SplashScreen()
}

