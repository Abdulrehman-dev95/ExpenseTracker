package com.example.expensetracker.ui.widgets

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.example.expensetracker.ui.theme.interFont

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = interFont,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
      text = text,
        fontFamily = fontFamily,
        color = color,
        fontSize = fontSize,
        fontStyle= fontStyle,
        modifier = modifier,
        fontWeight = fontWeight,
        style = style,
        textAlign = textAlign,
        textDecoration = textDecoration

    )

}