@file:OptIn(ExperimentalComposeUiApi::class)

package me.diffy.utils

import androidx.compose.foundation.focusable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isCtrlPressed
import androidx.compose.ui.input.pointer.onPointerEvent

actual inline fun Modifier.onCtrlClick(crossinline onClick: () -> Unit): Modifier {
    return this.then(Modifier.onPointerEvent(PointerEventType.Press) {
        val event = it.changes.first()
        if (event.pressed && currentEvent.button == PointerButton.Primary && it.keyboardModifiers.isCtrlPressed) {
            onClick()
            event.consume()
        }
    })
}

actual inline fun Modifier.onKeyClick(key: Key, crossinline onClick: () -> Unit): Modifier = composed {
    val focusReqiester = remember { FocusRequester() }
    val modifier = Modifier.onKeyEvent{
        if (it.key == key){
            onClick()
            true
        } else false
    }
        .focusable()
        .focusRequester(focusReqiester)
    LaunchedEffect(Unit){
        focusReqiester.requestFocus()
    }
    this.then(modifier)
}

actual inline fun Modifier.onAnyKeyClick(crossinline onClick: (Key) -> Unit): Modifier = composed {
    val focusRequester = remember { FocusRequester() }
    val modifier = Modifier.onKeyEvent{
        onClick(it.key)
        true
    }
        .focusable()
        .focusRequester(focusRequester)
    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }
    this.then(modifier)
}