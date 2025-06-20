package me.diffy.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key

inline fun Modifier.applyWhen(
    condition: Boolean,
    ifFalse: Modifier.() -> Modifier = { this },
    ifTrue: Modifier.() -> Modifier,
): Modifier = if (condition) {
    then(ifTrue(Modifier))
} else {
    then(ifFalse(Modifier))
}

expect inline fun Modifier.onCtrlClick(crossinline onClick: () -> Unit): Modifier

expect inline fun Modifier.onKeyClick(key: Key,crossinline onClick: () -> Unit): Modifier