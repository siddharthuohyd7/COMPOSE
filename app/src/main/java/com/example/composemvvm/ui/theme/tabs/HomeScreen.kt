import android.net.Uri
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composemvvm.ui.theme.AssetParamType
import com.example.composemvvm.ui.theme.DetailScreen
import com.example.composemvvm.ui.theme.ListScreen
import com.example.composemvvm.ui.theme.models.MovieItem
import com.example.composemvvm.ui.theme.navigateSingleTopTo
import com.google.gson.Gson


@Composable
fun HomeScreen(onNavigationClick: MutableState<() -> Unit>, onCLick: (value: Boolean,title:String ?) -> Unit) {
    val navController = rememberNavController()
    onNavigationClick.value = {
        onCLick(false,"Home")
        navController.popBackStack("listScreen", inclusive = false)
    }

    NavHost(navController, startDestination = "listScreen") {
        composable("listScreen") {
            ListScreen(onCLick = { movie ->
                onCLick(true,"Detail")
                navController.navigateSingleTopTo("detailScreen/${Uri.encode(Gson().toJson(movie).toString())}")
            })
        }

        composable(
            route = "detailScreen/{text}",
            arguments = listOf(navArgument("text") {
                type = AssetParamType()
            })
        ) {

            val device = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.arguments?.getParcelable("text",MovieItem::class.java)
            } else {
                it.arguments?.getParcelable("text")
            }

            DetailScreen(movie = device) {
                onCLick(false,"Home")
                navController.popBackStack("listScreen", inclusive = false)

            }
        }
    }

}




