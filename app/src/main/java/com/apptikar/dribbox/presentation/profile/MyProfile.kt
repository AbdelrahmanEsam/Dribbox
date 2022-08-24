package com.apptikar.dribbox.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apptikar.dribbox.R
import com.apptikar.dribbox.presentation.home.StorageApps
import com.apptikar.dribbox.presentation.ui.theme.*
import com.apptikar.dribbox.utils.ScreenClassifier
import com.apptikar.dribbox.utils.WindowSizeClass
import com.apptikar.dribbox.utils.sdp
import com.apptikar.dribbox.utils.textSdp
import kotlin.math.min

@Composable
fun MyProfile(modifier: Modifier,
              viewModel: MyProfileViewModel = hiltViewModel(),
              screenClassifier: ScreenClassifier,
              ) {

    val lazyGridState = rememberLazyGridState()
    val folders = viewModel.categoriesState
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp


    val localDensity = LocalDensity.current.density

    var cardAndHeaderMaxHeight:Float = (screenHeight * 0.45).toFloat()
    val cardAndHeaderMinHeight:Float = (screenHeight * 0.1).toFloat()
    val cardAndHeaderMaxHeightPx = with(LocalDensity.current) { cardAndHeaderMaxHeight.dp.roundToPx().toFloat() }
    val cardAndHeaderMinHeightPx = with(LocalDensity.current) { cardAndHeaderMinHeight.dp.roundToPx().toFloat() }

    val cardAndHeaderOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // try to consume before LazyColumn to collapse toolbar if needed, hence pre-scroll
                val delta = available.y
                val newOffset = cardAndHeaderOffsetHeightPx.value + delta
                cardAndHeaderOffsetHeightPx.value = newOffset.coerceIn(cardAndHeaderMinHeightPx-cardAndHeaderMaxHeightPx, 0f)
                // here's the catch: let's pretend we consumed 0 in any case, since we want
                // LazyColumn to scroll anyway for good UX
                // We're basically watching scroll without taking it
                return Offset.Zero
            }
        }
    }
    
        Column(modifier = modifier
            .fillMaxSize()
            .padding((screenWidth * 0.05).toInt().dp)
            .nestedScroll(nestedScrollConnection)) {
            Column(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth().onGloballyPositioned {
                        cardAndHeaderMaxHeight = it.size.height.toFloat()
                    }

            ) {
                Spacer(modifier = Modifier.size((screenHeight * 0.02).toInt().dp))
                MyHeader(
                    screenClassifier = screenClassifier,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = "My Profile",
                    startIcon = R.drawable.arrow_back,
                    iconSize = 15,
                    endIcon = R.drawable.ic_more_options_horizontal,
                    onNavigationClicked = {}
                )
                Spacer(modifier = Modifier.size((screenHeight * 0.05).toInt().dp))
                PersonalCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    name = "Abd-Elrahman",
                    job = "Android developer",
                    about = "Android developer enthusiast with latest technologies like jetpack compose ,Dagger hilt,WorkManager,kotlin coroutines , navigation component and koin and architectures like MVVM,MVI",
                    image = R.drawable.ic_avatar,
                    screenHeight = screenHeight,
                    screenWidth = screenWidth
                )
                Spacer(modifier = Modifier.size((screenHeight * 0.05).toInt().dp))
                MyFoldersHeader()
                Spacer(modifier = Modifier.size((screenHeight * 0.05).toInt().dp))
            }

            val minSize =
                if (screenClassifier is ScreenClassifier.FullyOpened && (screenClassifier.width.sizeClass == WindowSizeClass.Compact || screenClassifier.height.sizeClass == WindowSizeClass.Compact)) 120.sdp else 70.sdp
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = minSize),
                modifier = Modifier.height(screenHeight.dp -((cardAndHeaderMaxHeight.dp) +( cardAndHeaderOffsetHeightPx.value / localDensity).dp)),
                verticalArrangement = Arrangement.spacedBy((screenHeight * 0.02).toInt().dp),
                horizontalArrangement = Arrangement.spacedBy((screenWidth * 0.04).toInt().dp),
                state = lazyGridState
            ) {
                items(folders.size) { index ->
                    when (index) {

                        in 0..folders.size step 4 -> {
                            StorageApps(
                                modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                                sort = folders[index].name,
                                date = folders[index].date,
                                drawableImage = R.drawable.ic_blue_folder,
                                dominantColor = Blue,
                                backGroundColor = CardSky
                            )
                        }

                        in 1..folders.size step 4 -> {
                            StorageApps(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp)),
                                sort = folders[index].name,
                                date = folders[index].date,
                                drawableImage = R.drawable.ic_yellow_folder,
                                dominantColor = Yellow,
                                backGroundColor = CardYellow
                            )
                        }

                        in 2..folders.size step 4 -> {
                            StorageApps(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp)),
                                sort = folders[index].name,
                                date = folders[index].date,
                                drawableImage = R.drawable.ic_red_folder,
                                dominantColor = Red,
                                backGroundColor = CardRed
                            )
                        }

                        in 3..folders.size step 4 -> {
                            StorageApps(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp)),
                                sort = folders[index].name,
                                date = folders[index].date,
                                drawableImage = R.drawable.ic_green_folder,
                                dominantColor = Green,
                                backGroundColor = CardGreen
                            )
                        }


                    }

                }

            }

        }






}

@Composable
fun MyHeader(screenClassifier: ScreenClassifier, modifier : Modifier, text:String, startIcon:Int, iconSize:Int, endIcon:Int, onNavigationClicked : () -> Unit) {
    Row(modifier = modifier,horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        IconButton(modifier = Modifier.size(iconSize.sdp), onClick = onNavigationClicked) {
            Icon(
                painterResource(startIcon),
                contentDescription = "Icon",
                Modifier.size(iconSize.dp),
                tint = PurpleDark
            )
        }
        Text(text =text, style = TextStyle(
            color = PurpleDark,
            fontWeight = FontWeight.W500,fontSize = 14.sp,
            fontSynthesis = FontSynthesis.All))
            IconButton(modifier = Modifier.size(iconSize.dp), onClick = onNavigationClicked) {
                Icon(
                    painterResource(endIcon),
                    contentDescription = "Icon",
                    Modifier.size(iconSize.dp),
                    tint = PurpleDark
                )
            }

    }
}

@Composable
fun PersonalCard(modifier: Modifier, name:String, job:String, about:String, image:Int, screenHeight: Int, screenWidth:Int) {
    val cardWidth = remember{ mutableStateOf(0)}
                Column(
                    modifier = modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Spacer(modifier = Modifier.size((screenHeight * 0.02).toInt().dp))
                    Row(modifier = Modifier.fillMaxWidth().onGloballyPositioned {
                        cardWidth.value = it.size.width
                    },Arrangement.Start,Alignment.Top,) {
                       Spacer(modifier = Modifier.width( with(LocalDensity.current) { cardWidth.value.toDp() }/2 -  25.dp))
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = "userImage",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(AvatarBackground)
                        )
                        Spacer(modifier = Modifier.width( with(LocalDensity.current) { cardWidth.value.toDp() }/2 -  100.dp))
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Rose)
                                .width(50.dp)
                                .height(25.dp),Arrangement.Center)
                        {
                            Text(text = "PRO", style = MaterialTheme.typography.h4, color = Color.White,modifier = Modifier.align(CenterHorizontally), fontSize = 10.sp)
                        }
                    }

                    Spacer(modifier = Modifier.size((screenHeight * 0.01).toInt().dp))
                    Text(text = name, style = MaterialTheme.typography.h5, color = PurpleDark)
                    Spacer(modifier = Modifier.size((screenHeight * 0.01).toInt().dp))
                    Text(text = job, style = MaterialTheme.typography.h4, color = PurpleDark)
                    Spacer(modifier = Modifier.size((screenHeight * 0.01).toInt().dp))

                    Text(
                        text = about,
                        style = TextStyle(
                            color = TintText,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Gilroy, fontSize = 8.textSdp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = (screenWidth * 0.05).toInt().dp),
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.size((screenHeight * 0.02).toInt().dp))
                }

            }

@Composable
fun MyFoldersHeader() {
Row(modifier = Modifier.fillMaxWidth(),Arrangement.SpaceBetween) {
    Text(
        text = "My Folders",
        style = TextStyle(
            color = PurpleDark,
            fontWeight = FontWeight.W500,fontSize = 16.sp,
            fontSynthesis = FontSynthesis.All))
    Row(modifier = Modifier.fillMaxWidth(0.3f),Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Filled.Add,contentDescription = "Add",Modifier.size(20.dp),tint = PurpleDark)
        Icon(painterResource(id =R.drawable.ic_settings ),contentDescription = "settings",Modifier.size(15.dp),tint = PurpleDark)
        Icon(painterResource(id =R.drawable.ic_right),contentDescription = "right",Modifier.size(15.dp),tint = PurpleDark)
    }
}
}

@Composable
fun RecentUploadsHeader() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Recent Uploads",
            style = TextStyle(
                color = PurpleDark,
                fontWeight = FontWeight.W500,fontSize = 16.sp,
                fontSynthesis = FontSynthesis.All))
            Icon(painterResource(id =R.drawable.ic_sort),contentDescription = "sort",Modifier.size(18.dp),tint = PurpleDark)

    }
}




