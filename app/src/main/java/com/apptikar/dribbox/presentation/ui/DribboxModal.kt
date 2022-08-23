package com.apptikar.dribbox.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.WindowLayoutInfo
import com.apptikar.dribbox.MainActivity
import com.apptikar.dribbox.presentation.side_menu.SideMenu
import com.apptikar.dribbox.presentation.ui.navigation.Destinations
import com.apptikar.dribbox.presentation.ui.navigation.DribboxNavGraph
import com.apptikar.dribbox.presentation.ui.theme.DribboxTheme
import com.apptikar.dribbox.utils.ScreenClassifier
import com.apptikar.dribbox.utils.ScreenInfo
import com.apptikar.dribbox.utils.WindowSizeClass
import kotlinx.coroutines.flow.StateFlow

@Composable
fun Dribbox(
    windowSizeDp: DpSize,
    devicePosture: StateFlow<WindowLayoutInfo>,
    activity: MainActivity
) {
val devicePostureValue by devicePosture.collectAsState()



    DribboxTheme {
        val screenClassifier = ScreenInfo().screenClassifier(windowDpSize =windowSizeDp, devicePosture = devicePostureValue)
        val isCompactAndHome = screenClassifier is ScreenClassifier.FullyOpened && (screenClassifier.width.sizeClass == WindowSizeClass.Compact || screenClassifier.height.sizeClass == WindowSizeClass.Compact)
        val navController = rememberNavController()
        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isCompactAndHome)
        val scaffoldState = rememberScaffoldState(sizeAwareDrawerState)
        var isGestureEnabled by remember{ mutableStateOf(false)}
        val openAndCloseScope = rememberCoroutineScope()
        val sideMenu : @Composable () -> Unit = {SideMenu(
            screenClassifier = screenClassifier,
            navController = navController,
            scaffoldState = scaffoldState,
            openAndCloseScope = openAndCloseScope)}



        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {


            Scaffold(
                scaffoldState = scaffoldState,
                floatingActionButtonPosition = FabPosition.End ,

                drawerContent = {
                    sideMenu()
                },
                drawerGesturesEnabled =  isCompactAndHome && isGestureEnabled,
            ){paddingValues ->

            Row(Modifier.fillMaxSize()) {
                    DribboxNavGraph(screenClassifier = screenClassifier, navController = navController , modifier = Modifier.padding(paddingValues),scaffoldState,
                        isHome = {   isGestureEnabled =  isGestureDestination(it) },{sideMenu()})

            }

            }

        }
    }
}

private fun isGestureDestination(destination:String) : Boolean
{
    with(Destinations){
        return !(destination == ChangePassword  || destination ==  Login)
    }


}

@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
//        DrawerState(DrawerValue.Closed)
        DrawerState(DrawerValue.Closed)
    }
}

