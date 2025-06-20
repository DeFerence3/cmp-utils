package me.diffy.cmp.utils

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import me.diffy.utils.toast.setComposeWindowProvider

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "cmp-utils",
    ) {
        setComposeWindowProvider { window }
        App()
    }
}