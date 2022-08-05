package com.apptikar.dribbox.utils.adaptive

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.apptikar.dribbox.utils.ScreenClassifier

@Composable
fun AdaptiveLayoutListAndDetailStacked(
    screenClassifier: ScreenClassifier.HalfOpened.TableTopMode,
    firstHalf : @Composable () -> Unit,
    secondHalf : @Composable () -> Unit
) {


    Column {

        Box(modifier = Modifier.fillMaxHeight(screenClassifier.hingeSeparationRatio)) {
            firstHalf()
        }

        Box(modifier = Modifier.fillMaxHeight()) {
            secondHalf()
        }
    }


}