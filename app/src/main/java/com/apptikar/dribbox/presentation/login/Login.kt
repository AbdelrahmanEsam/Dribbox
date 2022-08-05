package com.apptikar.dribbox.presentation.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.apptikar.dribbox.R
import com.apptikar.dribbox.presentation.ui.theme.*
import com.apptikar.dribbox.utils.sdp
import com.apptikar.dribbox.utils.standardQuadFromTo
import com.apptikar.dribbox.utils.textSdp


@Composable
fun Login() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        Column(modifier = Modifier
            .background(BackgroundGray.copy(alpha = 0.2f))
            .fillMaxSize()
            .drawBehind {

                val width = size.width
                val height = size.height

                val point1 = Offset(0f, height.times(0.25f))
                val point2 = Offset(width.times(0.2f), height.times(0.3f))
                val point3 = Offset(width.times(0.6f), height.times(0.2f))
                val point4 = Offset(width.times(0.9f), height.times(0.35f))
                val point5 = Offset(width.times(1.5f), height.times(0.6f))
                val path = Path().apply {
                    moveTo(point1.x, point1.y)
                    standardQuadFromTo(point1, point2)
                    standardQuadFromTo(point2, point3)
                    standardQuadFromTo(point3, point4)
                    standardQuadFromTo(point4, point5)
                    lineTo(width + 100f, -100f)
                    lineTo(-100f, -100f)
                    close()
                }


                drawPath(path = path, color = SideMenu)


            }
            .padding(horizontal = screenWidth * 0.05f)
        ) {




            Spacer(modifier = Modifier.size(screenHeight * 0.1f))
            Image(painter = painterResource(R.drawable.ic_cloud), contentDescription = "cloudImage",modifier = Modifier
                .width((180.sdp))
                .height(180.sdp)
                .absoluteOffset(x = screenWidth * 0.0f, y = screenHeight * 0.001f)
                .aspectRatio(1f))
            Spacer(modifier = Modifier.size(screenHeight * 0.015f))
            Text(text = "Welcome to", modifier = Modifier, fontSize = 25.textSdp, fontFamily =
            Gilroy, color = PurpleDark, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.size(screenHeight * 0.015f))
            Text(text = "Dirbbox", modifier = Modifier, fontSize = 40.textSdp, fontFamily =
            Gilroy, color = PurpleDark, style = MaterialTheme.typography.h5, fontWeight = FontWeight.W800)
            Spacer(modifier = Modifier.size(screenHeight * 0.015f))
            Text(text = "Best cloud storage platform for all \nbusiness and individuals to \nmanage there data \n" +
                    "\n" +
                    "Join For Free.", modifier = Modifier,style = TextStyle(color = TintText,fontWeight = FontWeight.SemiBold, fontFamily = Gilroy, fontSize = 13.textSdp))
            Spacer(modifier = Modifier.size(screenHeight * 0.05f))
            Row(modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(
                            color = SideMenu,
                            shape = RoundedCornerShape(10.sdp)
                        )
                        .padding(horizontal = 10.sdp)
                        .width(screenWidth * 0.35f)
                        .height(45.sdp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_fingerprint),
                        contentDescription = "fingerPrint",tint = LightBlue, modifier = Modifier.size(15.sdp)
                    )
                    Spacer(modifier = Modifier.size(screenWidth * 0.015f))
                    Text(text = "Smart Id", fontFamily = Gilroy, fontWeight = FontWeight.SemiBold, fontSize = 15.textSdp, style = TextStyle(color =  LightBlue, shadow =  Shadow(
                        Gray, blurRadius = 10f, offset = Offset(10f,10f))))
                }
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(
                            color = LightBlue,
                            shape = RoundedCornerShape(10.sdp)
                        )
                        .padding(horizontal = 10.sdp)
                        .width(screenWidth * 0.35f)
                        .height(45.sdp)
                ) {


                    Text(text = "Sign in", fontFamily = Gilroy, fontWeight = FontWeight.SemiBold, fontSize = 15.textSdp, style = TextStyle(color =  White))
                    Spacer(modifier = Modifier.size(screenWidth * 0.015f))
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow),
                        contentDescription = "fingerPrint",tint = White, modifier = Modifier.size(15.sdp)
                    )
                }


            }
            Spacer(modifier = Modifier.size(screenHeight * 0.05f))
            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-10).sdp), horizontalArrangement = Arrangement.Center) {
                Text(text = "Use Social Login", fontFamily = Gilroy, fontWeight = FontWeight.SemiBold, fontSize = 10.textSdp, style = TextStyle(color =  OnBackground))
            }
            Spacer(modifier = Modifier.size(screenHeight * 0.03f))
            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-10).sdp), horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.width(screenWidth * 0.45f), horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        painter = painterResource(R.drawable.ic_instgram),
                        contentDescription = "instgram",tint = TintPurple, modifier = Modifier.size(15.sdp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_twitter),
                        contentDescription = "twitter",tint = TintPurple, modifier = Modifier.size(15.sdp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_facebook),
                        contentDescription = "facebook",tint = TintPurple, modifier = Modifier.size(15.sdp)
                    )
                }
            }
            Spacer(modifier = Modifier.size(screenHeight * 0.05f))
            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-10).sdp), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Create an account",
                    fontFamily = Gilroy,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.textSdp,
                    style = TextStyle(color = OnBackground)
                )
            }
            Spacer(modifier = Modifier.size(screenHeight * 0.02f))
        }



        Canvas(modifier = Modifier.width(50.sdp).height(50.sdp).offset(x = screenWidth * 0.6f, y = (-50).sdp)){

            cloudDraw(size)
        }




        }







}






internal fun DrawScope.cloudDraw(size: Size) {
    val width = size.width
    val height = size.height
    val path = Path().apply {
        moveTo(width.times(.75f), height)
        cubicTo(
            width.times(1f),
            height,
            width.times(1f),
            height.times(.70f),
            width.times(.75f),
            height.times(.70f)
        )
        cubicTo(
            width.times(.75f),
            height.times(.45f),
            width.times(.25f),
            height.times(.45f),
            width.times(.25f),
            height.times(.70f)
        )
        cubicTo(
            width.times(.0f),
            height.times(.70f),
            width.times(.0f),
            height.times(1f),
            width.times(.25f),
            height
        )
        close()
    }

    drawPath(path = path, color = CloudColor)
}



