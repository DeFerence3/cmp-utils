package me.diffy.cmp.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import me.diffy.utils.onAnyKeyClick
import me.diffy.utils.onKeyClick
import me.diffy.utils.toast.ToastDurationType
import me.diffy.utils.toast.ToastManager
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val toastManager by remember { mutableStateOf(ToastManager()) }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .onKeyClick(Key.A){
                    toastManager.showToast("Clicked A", ToastDurationType.SHORT)
                }
                .onAnyKeyClick{
                    toastManager.showToast("Clicked ${it.keyCode}", ToastDurationType.SHORT)
                }
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Press A for onKeyClick")
        }
    }
}