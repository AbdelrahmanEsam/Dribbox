package com.apptikar.dribbox.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.apptikar.dribbox.presentation.change_password.ChangePassword
import com.apptikar.dribbox.presentation.home.HomeScreenRoute
import com.apptikar.dribbox.presentation.login.Login
import com.apptikar.dribbox.presentation.profile.MyProfileRoute
import com.apptikar.dribbox.presentation.storage.StorageDetailRoute
import com.apptikar.dribbox.presentation.storage.StorageDetails
import com.apptikar.dribbox.presentation.ui.theme.BackgroundGray
import com.apptikar.dribbox.utils.ScreenClassifier
import com.apptikar.dribbox.utils.sdp


@Composable
fun DribboxNavGraph(
    screenClassifier: ScreenClassifier,
    navController: NavHostController,
    modifier: Modifier,
    scaffoldState: ScaffoldState,
    isHome: (String) -> Unit,
    ) {



    NavHost(
        navController = navController,
        startDestination = Destinations.Home,
        modifier = modifier
    ) {


        composable(Destinations.Home) {
            isHome(Destinations.Home)
            HomeScreenRoute(screenClassifier = screenClassifier,modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray.copy(alpha = 0.2f))
                .verticalScroll(rememberScrollState())
                .padding(25.dp), openAndCloseScope = rememberCoroutineScope(), scaffoldState = scaffoldState,navController = navController
            )
        }

        composable(Destinations.Login) {
            isHome(Destinations.Login)
            Login()
        }

        composable(Destinations.ChangePassword) {
            isHome(Destinations.ChangePassword)
            ChangePassword(modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray.copy(alpha = 0.2f))
                .padding(horizontal = 25.dp)
                .verticalScroll(rememberScrollState()))
        }

        composable(Destinations.Profile){
            isHome(Destinations.Profile)
            MyProfileRoute(modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray.copy(alpha = 0.2f))
                .padding(horizontal = 5.sdp)
                .verticalScroll(rememberScrollState())
                , screenClassifier = screenClassifier,openAndCloseScope = rememberCoroutineScope(), scaffoldState = scaffoldState,navController = navController,
            )
        }

        composable(Destinations.Details){
            isHome(Destinations.Details)
            StorageDetailRoute(
                screenClassifier = screenClassifier,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundGray.copy(alpha = 0.2f))
                    .padding(horizontal = 30.dp)
                    .verticalScroll(
                        rememberScrollState(),
                    ),
            openAndCloseScope = rememberCoroutineScope(),
                scaffoldState,
                navController
                )

        }
    }


}