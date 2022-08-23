package com.apptikar.dribbox.presentation.home

import AdaptiveLayoutListOneThirdAndDetailTwoThirds
import AdaptiveLayoutListHalfAndDetailHalf
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.apptikar.dribbox.utils.ScreenClassifier
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.apptikar.dribbox.presentation.side_menu.SideMenu
import com.apptikar.dribbox.utils.adaptive.AdaptiveLayoutListAndDetailStacked
import com.apptikar.dribbox.utils.adaptive.AdaptiveLayoutScreenType
import com.apptikar.dribbox.utils.adaptive.toAdaptiveLayoutScreenType
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreenRoute(
    screenClassifier: ScreenClassifier,
    modifier: Modifier,
    openAndCloseScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    sideMenu: @Composable () -> Unit,
) {
    var adaptiveLayoutScreenType by rememberSaveable { mutableStateOf(AdaptiveLayoutScreenType.ScreenOnly)}
    adaptiveLayoutScreenType = screenClassifier.toAdaptiveLayoutScreenType(articleSelected = false)
    when(adaptiveLayoutScreenType)
    {
        AdaptiveLayoutScreenType.ScreenOnly ->     HomeScreen(modifier = modifier, openAndCloseScope = openAndCloseScope, scaffoldState = scaffoldState,navController = navController,screenClassifier = screenClassifier)
        AdaptiveLayoutScreenType.ListHalfAndDetailHalf -> {
           AdaptiveLayoutListHalfAndDetailHalf(screenClassifier = screenClassifier,
            firstHalf = { sideMenu() }, secondHalf = {
                    HomeScreen(
                        modifier = modifier,
                        openAndCloseScope = openAndCloseScope,
                        scaffoldState = scaffoldState,
                        navController = navController,
                        screenClassifier = screenClassifier
                    )
                }

                )
        }
        AdaptiveLayoutScreenType.ListOneThirdAndDetailThirds ->{
            AdaptiveLayoutListOneThirdAndDetailTwoThirds(
             firstHalf = {
                 sideMenu()
                         },
                 secondHalf  = {
                     HomeScreen(
                         modifier = modifier,
                         openAndCloseScope = openAndCloseScope,
                         scaffoldState = scaffoldState,
                         navController = navController,
                         screenClassifier = screenClassifier
                     )
                 })
             }
        AdaptiveLayoutScreenType.ListAndDetailStacked -> {
            check(screenClassifier is ScreenClassifier.HalfOpened.TableTopMode)
            AdaptiveLayoutListAndDetailStacked(screenClassifier = screenClassifier,
                firstHalf = {
                    SideMenu(
                        screenClassifier = screenClassifier,
                        navController = navController,
                        scaffoldState = scaffoldState,
                        openAndCloseScope = openAndCloseScope,
                    )},
                secondHalf  = {
                    HomeScreen(
                        modifier = modifier,
                        openAndCloseScope = openAndCloseScope,
                        scaffoldState = scaffoldState,
                        navController = navController,
                        screenClassifier = screenClassifier
                    )
                })
        }
    }

}




