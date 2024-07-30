
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SettingsScreen(onNavigationClick: MutableState<() -> Unit>) {



    Text(
        "SettingsScreen",
        modifier = Modifier.fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically),
        textAlign = TextAlign.Center
    )


}