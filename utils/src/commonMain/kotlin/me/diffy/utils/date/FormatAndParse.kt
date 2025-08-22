@file:OptIn(ExperimentalTime::class)

package me.diffy.utils.date

import kotlinx.datetime.*
import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.MonthNames
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Format a Instant into a string. dd/MMM/yyyy
 */
fun Instant.format(builder: DateTimeFormatBuilder.WithDateTime.() -> Unit = {
    day()
    chars(" / ")
    monthName(MonthNames.ENGLISH_ABBREVIATED)
    chars(" / ")
    year()
}): String {
    val localDateTime = this.toLocalDateTime(TimeZone.currentSystemDefault())
    val formatter = LocalDateTime.Format(builder)
    return formatter.format(localDateTime)
}

/**
 * Parse a string into LocalDateTime.
 */
fun String.parse(builder: DateTimeFormatBuilder.WithDateTime.() -> Unit = { LocalDateTime.Formats.ISO }): LocalDateTime {
    val dateFormat = LocalDateTime.Format(builder)
    val date = dateFormat.parse(this)
    return date
}

/**
 * Check if a LocalDate is today.
 */
fun LocalDate.isToday(): Boolean {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    return this == today
}
