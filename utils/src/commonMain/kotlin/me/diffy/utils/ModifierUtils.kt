package me.diffy.utils

import androidx.compose.ui.Modifier

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