package me.diffy.utils

import androidx.compose.ui.Modifier

actual inline fun Modifier.onCtrlClick(crossinline onClick: () -> Unit) = this.then(Modifier)