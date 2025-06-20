package me.diffy.cmp.utils

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import me.diffy.utils.onAnyKeyClick
import me.diffy.utils.toast.ToastDurationType
import me.diffy.utils.toast.ToastManager
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val toastManager by remember { mutableStateOf(ToastManager()) }
        val focusRequester = remember { FocusRequester() }
        Column(
            modifier = Modifier
                .focusable()
                .focusRequester(focusRequester)
                .safeContentPadding()
                .onAnyKeyClick{
                    println("Clicked A")
                    toastManager.showToast("Clicked A", ToastDurationType.SHORT)
                }
                .fillMaxSize()
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Click A [Key Press Detector,Toast]")
        }

        LaunchedEffect(Unit){
            focusRequester.requestFocus()
        }
    }
}