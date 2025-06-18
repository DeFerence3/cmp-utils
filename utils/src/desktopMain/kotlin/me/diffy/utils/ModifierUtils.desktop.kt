package me.diffy.utils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isCtrlPressed
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
actual inline fun Modifier.onCtrlClick(crossinline onClick: () -> Unit): Modifier {
    return this.then(Modifier.onPointerEvent(PointerEventType.Press) {
        val event = it.changes.first()
        if (event.pressed && currentEvent.button == PointerButton.Primary && it.keyboardModifiers.isCtrlPressed) {
            onClick()
            event.consume()
        }
    })
}