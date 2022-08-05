package com.apptikar.dribbox.presentation.storage

import AdaptiveLayoutListHalfAndDetailHalf
import AdaptiveLayoutListOneThirdAndDetailTwoThirds
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.apptikar.dribbox.presentation.profile.MyProfile
import com.apptikar.dribbox.presentation.side_menu.SideMenu
import com.apptikar.dribbox.utils.ScreenClassifier
import com.apptikar.dribbox.utils.adaptive.AdaptiveLayoutListAndDetailStacked
import com.apptikar.dribbox.utils.adaptive.AdaptiveLayoutScreenType
import com.apptikar.dribbox.utils.adaptive.toAdaptiveLayoutScreenType
import kotlinx.coroutines.CoroutineScope

@Composable
fun StorageDetailRoute(
    screenClassifier: ScreenClassifier,
    modifier: Modifier,
    openAndCloseScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
) {
    var adaptiveLayoutScreenType by rememberSaveable { mutableStateOf(AdaptiveLayoutScreenType.ScreenOnly) }
    adaptiveLayoutScreenType = screenClassifier.toAdaptiveLayoutScreenType(articleSelected = false)
    when(adaptiveLayoutScreenType)
    {
        AdaptiveLayoutScreenType.ScreenOnly ->       StorageDetails(modifier = modifier,screenClassifier = screenClassifier)
        AdaptiveLayoutScreenType.ListHalfAndDetailHalf -> {
            AdaptiveLayoutListHalfAndDetailHalf(screenClassifier = screenClassifier,
                firstHalf = {
                    SideMenu(
                        screenClassifier = screenClassifier,
                        navController = navController,
                        scaffoldState = scaffoldState,
                        openAndCloseScope = openAndCloseScope,
                    )
                }, secondHalf = {
                    StorageDetails(modifier = modifier,screenClassifier = screenClassifier)
                }

            )
        }
        AdaptiveLayoutScreenType.ListOneThirdAndDetailThirds ->{
            AdaptiveLayoutListOneThirdAndDetailTwoThirds(
                firstHalf = {
                    SideMenu(
                        screenClassifier = screenClassifier,
                        navController = navController,
                        scaffoldState = scaffoldState,
                        openAndCloseScope = openAndCloseScope,
                    )
                },
                secondHalf  = {
                    StorageDetails(modifier = modifier,screenClassifier = screenClassifier)
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
                    )
                },
                secondHalf  = {
                    StorageDetails(modifier = modifier,screenClassifier = screenClassifier)
                })
        }




    }
}