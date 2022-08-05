import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.apptikar.dribbox.presentation.side_menu.SideMenu
import com.apptikar.dribbox.utils.ScreenClassifier
import kotlinx.coroutines.CoroutineScope

@Composable
fun AdaptiveLayoutListOneThirdAndDetailTwoThirds(
    firstHalf : @Composable () -> Unit,
    secondHalf : @Composable () -> Unit
) {

    Row {

        Box(modifier = Modifier.fillMaxWidth(0.3f)) {
            firstHalf()
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            secondHalf()
        }
    }


}