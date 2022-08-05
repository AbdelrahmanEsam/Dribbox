package com.apptikar.dribbox.presentation.storage

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apptikar.dribbox.R
import com.apptikar.dribbox.data.ItemDetail
import com.apptikar.dribbox.presentation.profile.MyHeader
import com.apptikar.dribbox.presentation.ui.theme.*
import com.apptikar.dribbox.utils.ScreenClassifier
import com.apptikar.dribbox.utils.sdp
import com.apptikar.dribbox.utils.textSdp
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun StorageDetails(modifier: Modifier, screenClassifier: ScreenClassifier,storageViewModel: StorageViewModel = hiltViewModel()) {
   val width =  LocalConfiguration.current.screenWidthDp
   val height = LocalConfiguration.current.screenHeightDp
   val  totalInternalSize = storageViewModel.totalMemorySize.collectAsState()
   val freeInternalSize = storageViewModel.freeMemorySize.collectAsState()
   val listOfDataRows = storageViewModel.listOfDataRows.collectAsState()



 LazyColumn(modifier = modifier.height((height).dp), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Spacer(modifier = Modifier.size(25.dp))
            MyHeader(
                screenClassifier = screenClassifier,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = "Storage Details",
                startIcon = R.drawable.arrow_back,
                iconSize = 15,
                endIcon = R.drawable.ic_more_options_horizontal
            ) {

            }

            Spacer(modifier = Modifier.size(35.dp))
            DonateCanvas(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), listOfData = listOfDataRows)
            Spacer(modifier = Modifier.size(35.dp))
            Text(
                text = "Available", style = TextStyle(
                    color = PurpleDark,
                    fontWeight = FontWeight.W400,
                    fontSynthesis = FontSynthesis.All,
                    textAlign = TextAlign.Center
                ), fontSize = 18.sp
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = formatSize(freeInternalSize.value), style = TextStyle(
                    color = PurpleDark,
                    fontWeight = FontWeight.W700,
                    fontSynthesis = FontSynthesis.All,
                    textAlign = TextAlign.Center
                ), fontSize = 18.sp
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Total ${formatSize(totalInternalSize.value)}" , style = TextStyle(
                    color = PurpleDark,
                    fontWeight = FontWeight.W400,
                    fontSynthesis = FontSynthesis.All,
                    textAlign = TextAlign.Center
                ), fontSize = 18.sp
            )
            Spacer(modifier = Modifier.size(25.dp))
        }

        items( items = listOfDataRows.value){ item ->
            OneDetailRow(
                modifier = Modifier.fillMaxWidth(),
                detailName = item.name,
                storageCapacity = item.size,
                dominantColor = item.color,
                width = width,
                item.percentage.toFloat()
            )
            Spacer(modifier = Modifier.size(15.dp))
        }

        item {
            Text(text ="Export Details", style = TextStyle(
                color = PurpleDark,
                fontWeight = FontWeight.W500,fontSize = 14.sp,
                fontSynthesis = FontSynthesis.All))
            Spacer(modifier = Modifier.size(30.dp))
        }
    }
}




@Composable
fun DonateCanvas(modifier: Modifier, listOfData: State<MutableList<ItemDetail>>) {
    val animatables = rememberSaveable{mutableListOf(Animatable(0.0f),Animatable(0.0f),Animatable(0.0f),Animatable(0.0f),Animatable(0.0f))}


    LaunchedEffect("Percentage") {
        animatables.forEachIndexed  {   index, animatable ->
                animatable.animateTo(listOfData.value[index].percentage.toFloat(),
                    animationSpec = tween(durationMillis = (1500 * listOfData.value[index].percentage).toInt(), easing = LinearEasing))
        }
    }





    Row(modifier, horizontalArrangement = Arrangement.Center) {
        var percentage = 0f
        Canvas(modifier = Modifier.size(100.dp)){
                 listOfData.value.forEachIndexed { index, itemDetail ->
                         drawArc(
                             color = itemDetail.color,
                             startAngle = percentage,
                             sweepAngle = (animatables[index].value * 360),
                             useCenter = false,
                             style = Stroke(width = 40.dp.toPx())
                         )


                         percentage += (itemDetail.percentage * 360).toFloat()

                     }
        }
    }

}

@Composable
fun OneDetailRow(
    modifier: Modifier,
    detailName: String,
    storageCapacity: Long,
    dominantColor: Color,
    width: Int,
    storagePercentage:Float,

    ) {
    val storagePercentageValue  = remember{ Animatable(0.0f) }

    LaunchedEffect(storagePercentage) {
        storagePercentageValue.animateTo(storagePercentage,
            animationSpec = tween(durationMillis = 1500, easing = LinearEasing))
    }

Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
    Column(modifier = Modifier
        .wrapContentHeight(), verticalArrangement = Arrangement.Center) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Canvas(modifier = Modifier
                    .size(10.dp)
                    .clickable {

                    }) {
                    drawCircle(dominantColor)
                }
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = detailName, style = TextStyle(
                        color = PurpleDark,
                        fontWeight = FontWeight.Normal, fontSize = 15.sp,
                        fontSynthesis = FontSynthesis.All
                    )
                )
            }
            Canvas(modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(5.dp)) {
                drawRoundRect(color = SideMenu,cornerRadius = CornerRadius(x = 50f, y = 50f))
                drawRoundRect(color = dominantColor,cornerRadius = CornerRadius(x = 50f, y = 50f), size = Size(width = (size.width * storagePercentageValue.value) , height = this.size.height) , topLeft = Offset(x = size.width - size.width * storagePercentageValue.value,y = 0f))
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = formatSize(storageCapacity), style = TextStyle(
                    color = PurpleDark.copy(alpha = 0.6f),
                    fontWeight = FontWeight.W500, fontSize = 13.sp,
                    fontSynthesis = FontSynthesis.All
                )
            )
        }

    }

}
}



 private fun formatSize(size: Long): String {
    var finalSize:Double = size.toDouble()
    var suffix: String? = null
    if (finalSize >= 1024) {
        suffix = " KB"
        finalSize /= 1024
        if (finalSize >= 1024) {
            suffix = " MB"
            finalSize /= 1024
        }
        if (finalSize >= 1024) {
            suffix = " GB"
            finalSize /= 1024
        }
    }
    val resultBuffer = StringBuilder(((finalSize * 10.0).roundToInt() /10.0).toString())
    if (suffix != null) resultBuffer.append(suffix)
    return resultBuffer.toString()
}







