package com.apptikar.dribbox.presentation.side_menu

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.apptikar.dribbox.R
import com.apptikar.dribbox.presentation.ui.navigation.Destinations

import com.apptikar.dribbox.presentation.ui.theme.CloseTint
import com.apptikar.dribbox.presentation.ui.theme.LightBlue
import com.apptikar.dribbox.presentation.ui.theme.OnBackground
import com.apptikar.dribbox.presentation.ui.theme.TintText
import com.apptikar.dribbox.utils.ScreenClassifier
import com.apptikar.dribbox.utils.WindowSizeClass
import com.apptikar.dribbox.utils.navigateSingleTopTo
import com.apptikar.dribbox.utils.sdp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.apptikar.dribbox.presentation.ui.theme.SideMenu

@Composable
fun SideMenu(
    screenClassifier: ScreenClassifier,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    openAndCloseScope: CoroutineScope,

) {
    Column(modifier = Modifier
        .background(SideMenu)
        .fillMaxSize()) {
        CustomSideNavigator(
            modifier = Modifier
                .fillMaxHeight(),
            list = listOf(stringResource(R.string.home), stringResource(R.string.profile), stringResource(
                            R.string.storage), stringResource(R.string.shared), stringResource(R.string.stats), stringResource(
                                            R.string.settings), stringResource(R.string.help))
            , screenClassifier = screenClassifier,navController,scaffoldState,openAndCloseScope)

//    Spacer(modifier = Modifier.size(30.sdp))
    }
}

@Composable
fun SideMenuHeader(
    image: Int,
    name: String,
    place: String,
    screenClassifier: ScreenClassifier,
    openAndCloseScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {


    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.6f)
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 50.dp,
                        bottomStart = 0.dp,
                        topEnd = 0.dp,
                        topStart = 0.dp
                    )
                )
                .background(Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(painter = painterResource(id = image), contentDescription = "userImage",modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape))
                Column(modifier = Modifier
                    .wrapContentHeight()) {
                    Text(text = name, style = TextStyle(color = OnBackground, fontSize = 18.sp), overflow = TextOverflow.Ellipsis, maxLines = 1)
                    Text(text = place, style = TextStyle(color = TintText, fontSize = 12.sp), overflow = TextOverflow.Ellipsis,maxLines = 1)
                }
            }

        }

        if (screenClassifier is ScreenClassifier.FullyOpened && (screenClassifier.width.sizeClass == WindowSizeClass.Compact || screenClassifier.height.sizeClass == WindowSizeClass.Compact))
        {
            IconButton(modifier = Modifier.padding(end = 15.dp)
                    then(Modifier.size(28.dp)),onClick = {
                openAndCloseScope.launch {
                    scaffoldState.drawerState.close()
                } }) {
                Icon(Icons.Filled.Close, contentDescription = "close" , tint = CloseTint, modifier = Modifier.size(28.dp))
            }
        }

    }
}

@Composable
fun CustomSideNavigator(
    modifier: Modifier,
    list: List<String>,
    screenClassifier: ScreenClassifier,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    openAndCloseScope: CoroutineScope,
    ) {

    val fontWeightIndex = rememberSaveable{ mutableStateOf(0)}
    val activity = (LocalContext.current as? Activity)




    BackHandler(enabled = true) {

        openAndCloseScope.launch {
            scaffoldState.drawerState.close()
        }

      if (!navController.popBackStack()) activity?.finish()



    }

    SideEffect {
        navController.addOnDestinationChangedListener{ _, destination, _ ->

            when (destination.route) {
                Destinations.Home ->  {
                    fontWeightIndex.value = 0
                }
                Destinations.Profile ->  {
                    fontWeightIndex.value = 1
                }
                Destinations.Details -> fontWeightIndex.value = 2
                Destinations.Shared -> fontWeightIndex.value = 3
                Destinations.Stats -> fontWeightIndex.value = 4
            }
        }
    }






         Column(
             modifier = modifier.verticalScroll(rememberScrollState()).background(SideMenu),
             horizontalAlignment = Alignment.Start
         ) {




             SideMenuHeader(R.drawable.ic_avatar,"Abd-Elrahman Esam","Mansoura ,Egypt", screenClassifier = screenClassifier,openAndCloseScope,scaffoldState)
             Spacer(modifier = Modifier.size((LocalConfiguration.current.screenHeightDp * 0.1).toInt().dp))



             list.forEachIndexed {  index,title->
                 Text(
                     modifier = Modifier
                         .padding(start = 30.dp, bottom = 40.dp)
                         .clickable(enabled = true, onClick = {

                             when (title) {
                                 "Home" -> navController.navigateSingleTopTo(Destinations.Home)


                                 "Profile" -> navController.navigateSingleTopTo(Destinations.Profile)

                                 "Storage" -> navController.navigateSingleTopTo(Destinations.Details)


                                 "Shared" -> {}



                                 "Stats" -> {}




                                 "Settings" -> navController.navigateSingleTopTo(Destinations.ChangePassword)


                                 "Help" -> {}

                             }

                             openAndCloseScope.launch {
                                 scaffoldState.drawerState.close()
                             }


                         }),
                     text = title,
                     style = TextStyle(
                         color = OnBackground, fontSize = 18.sp, fontWeight = if (index == fontWeightIndex.value) {
                             FontWeight.W700
                         } else FontWeight.W400, shadow = Shadow(
                             Color.Gray,
                             offset = Offset(2f, 2f),
                             blurRadius = 8f
                         )
                     ),
                     maxLines = 1,

                     )
             }
             Spacer(modifier = Modifier.size((LocalConfiguration.current.screenHeightDp * 0.06).toInt().dp))
             SideMenuFooter(modifier = Modifier.wrapContentSize())
             Spacer(modifier = Modifier.size((LocalConfiguration.current.screenHeightDp * 0.06).toInt().dp))

         }


     }



    






@Composable
fun SideMenuFooter(modifier: Modifier) {
    Row(modifier = modifier.padding(start = 30.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(painterResource(R.drawable.ic_log_out),contentDescription = "logOut", Modifier.size(20.dp))
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Logout", style = TextStyle(color = OnBackground, fontSize = 20.sp), fontWeight = FontWeight.W700)
    }
}

private enum class IndicatorState{ HOME, PROFILE, STORAGE, SHARED, STATS,SETTINGS, HELP}