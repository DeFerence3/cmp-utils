package me.diffy.utils

fun <T> Iterable<T>.containsBy(selector: (T) -> Boolean): Boolean {
    for (item in this) {
        if (selector(item)) {
            return true
        }
    }
    return false
}