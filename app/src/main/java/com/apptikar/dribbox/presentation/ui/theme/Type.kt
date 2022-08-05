package com.apptikar.dribbox.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.apptikar.dribbox.R

val Gilroy = FontFamily(
    Font(R.font.gilroy_light),
    Font(R.font.gilroy_extra_bold),
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */


)

val TypographyGilroy = Typography(
    body1 = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),

    h4 = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,

        )
    ,

    h3 = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
     shadow = Shadow(Black, blurRadius = 12f, offset = Offset(8f,8f))
    ),

    h5= TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,

        )

)