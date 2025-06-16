package me.diffy.cmp.utils

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "cmp-utils",
    ) {
        App()
    }
}