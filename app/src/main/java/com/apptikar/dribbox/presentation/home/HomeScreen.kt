package com.apptikar.dribbox.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.apptikar.dribbox.R
import com.apptikar.dribbox.presentation.ui.theme.*
import com.apptikar.dribbox.utils.ScreenClassifier
import com.apptikar.dribbox.utils.WindowSizeClass
import com.apptikar.dribbox.utils.sdp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.collections.contains

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    modifier: Modifier,
    openAndCloseScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    screenClassifier: ScreenClassifier,
) {
    val searchState = viewModel.searchState
    val folders = viewModel.categoriesState
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Box(modifier = Modifier
        .fillMaxSize(), Alignment.Center) {
        Column(modifier = modifier,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            Header(screenClassifier = screenClassifier ,modifier = Modifier.fillMaxSize(),text = "Your Dribbbox", icon = R.drawable.ic_union,20,25,20){
                openAndCloseScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight * 0.02).dp))
//          CustomTextField(
//              leadingIcon = Icon(painterResource(R.drawable.ic_search_icon), contentDescription = "search_icon"
//                  , modifier = Modifier
//                      .size(18.sdp), tint = PurpleDark ),
//              modifier = Modifier.fillMaxSize(), name = searchState ,
//                onValueChange = {viewModel.setNewSearchState(it)},
//                placeHolder = "  Search  Folder",)

            OutlinedTextField(
                leadingIcon = {
                    Icon(painterResource(R.drawable.ic_search_icon), contentDescription = "search_icon"
                        , modifier = Modifier
                            .size(20.dp)
                            .offset(x = 10.dp, y = 1.dp), tint = PurpleDark )
                },
                value = searchState,
                onValueChange = { viewModel.setNewSearchState(it) },
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(10.sdp),
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = CardSky,
                    unfocusedBorderColor = CardSky),
                placeholder = {Text(text = "  Search  Folder",
                    style = TextStyle(color = PurpleDark, fontSize = 20.sp),
                    modifier = Modifier
                        .height(25.dp)
                        .offset(x = (-8).dp))},
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp)
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight * 0.02).dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RecentHeader(modifier = Modifier.wrapContentWidth(),
                    text = "Recent",
                    icon = R.drawable.ic_arrow_down_24,
                    25,
                    18,
                    5)
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight * 0.02).dp))

            val minSize = if (screenClassifier is ScreenClassifier.FullyOpened &&
                (screenClassifier.width.sizeClass == WindowSizeClass.Compact || screenClassifier.height.sizeClass == WindowSizeClass.Compact)) 120.sdp else 70.sdp
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = minSize),
                modifier = Modifier.height((screenHeight * 0.75).dp),
                verticalArrangement = Arrangement.spacedBy((screenWidth * 0.040).toInt().dp),
                horizontalArrangement = Arrangement.spacedBy((screenWidth * 0.040).toInt().dp)
            ){
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

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight * 0.05).dp))

        }
    }
    FAB()


}



    

@Composable
fun StorageApps(modifier : Modifier,sort:String,date:String,drawableImage:Int,dominantColor: Color,backGroundColor:Color) {
    Column(modifier = modifier.background(backGroundColor)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Image(painter = painterResource(drawableImage), contentDescription = "folderIcon",
                Modifier
                    .size(50.dp)
                    .offset(y = (-7).dp))
            Icon(painterResource(R.drawable.ic_more_options),contentDescription = "union",Modifier.size(20.dp).offset( y = ((8).dp)),tint = dominantColor)
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp))
        Text(text = sort,style = TextStyle(color = dominantColor, fontSize = 20.sp), modifier = Modifier.padding(horizontal = 15.dp), overflow = TextOverflow.Ellipsis, maxLines = 1)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp))
        Text(text = date,style = TextStyle(color = dominantColor, fontSize = 11.sp),modifier = Modifier.padding(horizontal = 15.dp),overflow = TextOverflow.Ellipsis, maxLines = 1)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

    }
}

@Composable
fun Header(screenClassifier: ScreenClassifier,modifier : Modifier,text:String,icon:Int,iconSize:Int,fontSize:Int,spacerSize:Int,onNavigationClicked : () -> Unit) {
    Row(modifier = modifier,horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text =text, style = TextStyle(color = PurpleDark, fontSize = fontSize.sp))
        Spacer(modifier = Modifier.size(spacerSize.dp))
        if(screenClassifier is ScreenClassifier.FullyOpened && (screenClassifier.width.sizeClass == WindowSizeClass.Compact || screenClassifier.height.sizeClass == WindowSizeClass.Compact)) {
            IconButton(modifier = Modifier.size(iconSize.dp), onClick = onNavigationClicked) {
                Icon(
                    painterResource(icon),
                    contentDescription = "Icon",
                    Modifier.size(iconSize.dp),
                    tint = PurpleDark
                )
            }
        }
    }
}

@Composable
fun RecentHeader(modifier : Modifier,text:String,icon:Int,iconSize:Int,fontSize:Int,spacerSize:Int) {
    Row(modifier = modifier,horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text =text, style = TextStyle(color = PurpleDark, fontSize = fontSize.sp))
        Spacer(modifier = Modifier.size(spacerSize.dp))
            IconButton(modifier = Modifier.size(iconSize.dp), onClick = {}) {
                Icon(
                    painterResource(icon),
                    contentDescription = "Icon",
                    Modifier.size(iconSize.dp),
                    tint = PurpleDark
                )
            }

    }
}


@Composable
fun FAB() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 15.dp, vertical = 45.dp), Alignment.BottomEnd) {
        FloatingActionButton(onClick = {}, backgroundColor = PurpleDark,modifier = Modifier.size(80.dp)){
            Icon(Icons.Filled.Add,contentDescription = "floating",Modifier.size(20.dp),tint = Color.White)
        }
    }
}







