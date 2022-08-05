package com.apptikar.dribbox.presentation.change_password

import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apptikar.dribbox.R
import com.apptikar.dribbox.presentation.ui.theme.*
import com.apptikar.dribbox.utils.sdp
import com.apptikar.dribbox.utils.textSdp

@Composable
fun ChangePassword(viewModel: ChangePasswordViewModel = hiltViewModel(),modifier: Modifier) {
val currentPasswordState = viewModel.currentPasswordState
val newPasswordState = viewModel.newPasswordState
val confirmPasswordState = viewModel.confirmPasswordState

    val textPaintStroke = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        style = android.graphics.Paint.Style.STROKE
        textSize =  with(LocalDensity.current) { 25.sp.toPx() }
        color = android.graphics.Color.BLACK
        strokeWidth = 5f
        strokeMiter= 5f
        strokeJoin = android.graphics.Paint.Join.ROUND
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            typeface = LocalContext.current.resources.getFont(R.font.gilroy_light)
        }
    }

    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        style = android.graphics.Paint.Style.FILL
        textSize =with(LocalDensity.current) { 25.sp.toPx() }
        color = OnBackground.toArgb()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            typeface = LocalContext.current.resources.getFont(R.font.gilroy_light)
        }
    }

    Column(modifier = modifier,
       horizontalAlignment = Alignment.Start,
       verticalArrangement = Arrangement.Top,
        ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))
        BackIcon()
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))
            Canvas(
                modifier = Modifier.fillMaxWidth(),
                onDraw = {
                    drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            "Change Password",
                            0f,
                            10.dp.toPx(),
                            textPaintStroke,

                            )
                        it.nativeCanvas.drawText(
                            "Change Password",
                            0f,
                            10.dp.toPx(),
                            textPaint
                        )
                    }
                }
            )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))
        TextPassword(modifier = Modifier.fillMaxWidth(), text ="Current Password",fontSize = 13.sp,color = OnBackground )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        CustomTextField(modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(), name = currentPasswordState , onValueChange = {viewModel.setCurrentPassword(it)} )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))
        TextPassword(modifier = Modifier.fillMaxWidth(), text ="New Password",fontSize = 13.sp,color = OnBackground )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        CustomTextField(modifier = Modifier.height(50.dp).fillMaxWidth(), name = newPasswordState , onValueChange = {viewModel.setNewPassword(it)} )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))
        TextPassword(modifier = Modifier.fillMaxWidth(), text ="Confirm new password",fontSize = 13.sp,color = OnBackground )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        CustomTextField(modifier = Modifier.height(50.dp).fillMaxWidth(), name = confirmPasswordState , onValueChange = {viewModel.setConfirmPassword(it)})
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))
    }



}


@Composable
fun BackIcon() {
    Icon(painterResource(R.drawable.arrow_back), contentDescription = "arrow_back",modifier = Modifier.size(15.dp))

}


@Composable
fun TextPassword(modifier: Modifier, text: String, fontSize: TextUnit, color: Color) {
    Text(text = text, modifier = modifier,fontSize = fontSize,style = TextStyle(color = color, fontSize = 11.sp, fontFamily = Gilroy, fontWeight = FontWeight.SemiBold))
}

@Composable
fun CustomTextField(modifier: Modifier, name:String, onValueChange : (String) -> Unit, placeHolder:String="") {
    OutlinedTextField(

       textStyle =  TextStyle(color = Color.Black, fontSize = 10.sp),
        value = name,
        onValueChange =  onValueChange ,
        modifier = modifier.border(1.dp, CardSky,RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = CardSky,
            unfocusedBorderColor = CardSky,
            ),
       placeholder = {Text(text = placeHolder, style = TextStyle(color = PurpleDark, fontSize = 20.sp), modifier = Modifier)}

    )

}


