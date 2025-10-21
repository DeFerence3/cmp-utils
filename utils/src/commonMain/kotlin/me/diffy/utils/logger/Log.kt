package me.diffy.utils.logger

const val isLogging = true

object Log {
    fun i(tag: String = "TableManagement",message: () -> String) = if (isLogging) { println("INFO: $tag  ${message()}") } else Unit
    fun d(tag: String = "TableManagement",message: () -> String) = if (isLogging) { println("DEBUG: $tag  ${message()}") } else Unit
    fun w(tag: String = "TableManagement",message: () -> String) = if (isLogging) { println("WARN: $tag  ${message()}") } else Unit
    fun raw(message: () -> String) = if (isLogging) { println(message()) } else Unit
}