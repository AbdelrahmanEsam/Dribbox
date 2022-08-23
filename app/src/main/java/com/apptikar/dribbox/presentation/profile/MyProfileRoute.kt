package com.apptikar.dribbox.presentation.profile

import AdaptiveLayoutListOneThirdAndDetailTwoThirds
import AdaptiveLayoutListHalfAndDetailHalf
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.apptikar.dribbox.presentation.home.HomeScreen
import com.apptikar.dribbox.presentation.side_menu.SideMenu
import com.apptikar.dribbox.utils.ScreenClassifier
import com.apptikar.dribbox.utils.adaptive.AdaptiveLayoutListAndDetailStacked
import com.apptikar.dribbox.utils.adaptive.AdaptiveLayoutScreenType
import com.apptikar.dribbox.utils.adaptive.toAdaptiveLayoutScreenType
import kotlinx.coroutines.CoroutineScope

@Composable
fun MyProfileRoute(
    screenClassifier: ScreenClassifier,
    modifier: Modifier,
    openAndCloseScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    sideMenu: @Composable ()-> Unit,
    ) {
    var adaptiveLayoutScreenType by rememberSaveable { mutableStateOf(AdaptiveLayoutScreenType.ScreenOnly) }
    adaptiveLayoutScreenType = screenClassifier.toAdaptiveLayoutScreenType(articleSelected = false)
    when(adaptiveLayoutScreenType)
    {
        AdaptiveLayoutScreenType.ScreenOnly ->     MyProfile(modifier = modifier,screenClassifier = screenClassifier)
        AdaptiveLayoutScreenType.ListHalfAndDetailHalf -> {
            AdaptiveLayoutListHalfAndDetailHalf(screenClassifier = screenClassifier,
                firstHalf = {
                    sideMenu()
                }, secondHalf = {
                    MyProfile(modifier = modifier,screenClassifier = screenClassifier)
                }

            )
        }
        AdaptiveLayoutScreenType.ListOneThirdAndDetailThirds ->{
            AdaptiveLayoutListOneThirdAndDetailTwoThirds(
                firstHalf = {
                   sideMenu()
                },
                secondHalf  = {
                    MyProfile(modifier = modifier,screenClassifier = screenClassifier)
                })
        }
        AdaptiveLayoutScreenType.ListAndDetailStacked -> {
            check(screenClassifier is ScreenClassifier.HalfOpened.TableTopMode)
            AdaptiveLayoutListAndDetailStacked(screenClassifier = screenClassifier,
                firstHalf = {
                   sideMenu() },
                secondHalf  = {
                    MyProfile(
                        modifier = modifier,
                        screenClassifier = screenClassifier
                    )
                })
        }




    }
}