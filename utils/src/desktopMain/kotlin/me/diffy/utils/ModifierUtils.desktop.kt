@file:OptIn(ExperimentalComposeUiApi::class)

package me.diffy.utils

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.key.*
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
    val modifier = Modifier.onKeyEvent{
        println(it.key)
        if (it.key == key){
            onClick()
            true
        } else false
    }

    this.then(modifier)
}

actual inline fun Modifier.onAnyKeyClick(crossinline onClick: (key: Key) -> Unit): Modifier = composed {
    val pressedKeys = remember { mutableStateMapOf<Key, Boolean>() }
    val modifier = Modifier.onKeyEvent { keyEvent: KeyEvent ->
        when (keyEvent.type) {
            KeyEventType.KeyDown -> {
                if (pressedKeys[keyEvent.key] != true) {
                    onClick(keyEvent.key)
                    pressedKeys[keyEvent.key] = true
                }
                true
            }
            KeyEventType.KeyUp -> {
                pressedKeys[keyEvent.key] = false
                true
            }
            else -> false
        }
    }
    this.then(modifier)
}