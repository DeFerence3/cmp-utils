package me.diffy.cmp.utils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform