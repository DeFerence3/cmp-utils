package me.diffy.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key

actual inline fun Modifier.onCtrlClick(crossinline onClick: () -> Unit) = this.then(Modifier)
actual inline fun Modifier.onKeyClick(
    key: Key,
    crossinline onClick: () -> Unit,
): Modifier = this.then(Modifier)

actual inline fun Modifier.onAnyKeyClick(crossinline onClick: (Key) -> Unit): Modifier {
    TODO("Not yet implemented")
}