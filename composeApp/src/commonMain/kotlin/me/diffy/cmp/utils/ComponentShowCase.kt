package me.diffy.cmp.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ComponentShowCase(
    modifier: Modifier = Modifier,
    name: String,
    component: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .fillMaxHeight()
            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Text(text = name)
        HorizontalDivider(modifier = Modifier
            .padding(bottom = 4.dp)
            .height(1.dp))
        component()
    }
}