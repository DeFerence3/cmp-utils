package me.diffy.cmp.utils.ktor

import io.ktor.client.*
import me.diffy.utils.ktor.CustomLoggerPlugin

val ktorClient = HttpClient {
    install(CustomLoggerPlugin){
        logResponse = true
        logRequest = true
        logOnError = true
    }
}