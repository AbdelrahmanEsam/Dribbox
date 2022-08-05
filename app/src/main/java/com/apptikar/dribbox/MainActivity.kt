package com.apptikar.dribbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import com.apptikar.dribbox.presentation.side_menu.SideMenu
import com.apptikar.dribbox.presentation.ui.Dribbox
import com.apptikar.dribbox.utils.rememberWindowSizeDp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn



@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val devicePosture = WindowInfoTracker
            .getOrCreate(this)
            .windowLayoutInfo(this)
            .stateIn(scope =lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = WindowLayoutInfo(emptyList())
            )





        setContent {

            val windowSizeDp = rememberWindowSizeDp(this)
            Dribbox(
               devicePosture =  devicePosture,
              windowSizeDp =  windowSizeDp,
                activity = this@MainActivity
            )
        }
    }
}

