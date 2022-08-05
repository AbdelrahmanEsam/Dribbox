package com.apptikar.dribbox.utils.adaptive

import android.util.Log
import androidx.compose.runtime.Composable
import com.apptikar.dribbox.utils.ScreenClassifier
import com.apptikar.dribbox.utils.WindowSizeClass


@Composable
fun ScreenClassifier.toAdaptiveLayoutScreenType(articleSelected : Boolean) : AdaptiveLayoutScreenType {
    return if (this is ScreenClassifier.FullyOpened && width.sizeClass == WindowSizeClass.Expanded)
    {
        AdaptiveLayoutScreenType.ListOneThirdAndDetailThirds
    }else if (this is ScreenClassifier.FullyOpened && width.sizeClass == WindowSizeClass.Medium && height.sizeClass != WindowSizeClass.Compact)
    {
        AdaptiveLayoutScreenType.ListHalfAndDetailHalf
    }
    else if (this is ScreenClassifier.FullyOpened && !articleSelected)
    {
        AdaptiveLayoutScreenType.ScreenOnly
    }else if (this is ScreenClassifier.HalfOpened.BookMode)
    {

        AdaptiveLayoutScreenType.ListHalfAndDetailHalf
    }else if (this is ScreenClassifier.HalfOpened.TableTopMode)
    {

        AdaptiveLayoutScreenType.ListAndDetailStacked
    }else
    {
        AdaptiveLayoutScreenType.ScreenOnly
    }
}