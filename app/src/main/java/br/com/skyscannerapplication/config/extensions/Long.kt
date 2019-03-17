package br.com.skyscannerapplication.config.extensions

fun Long.minutesToPeriod(): String {
    val days = (this / 24 / 60)
    val hours = this / 60 % 24
    val minutes = this % 60

    return when {
        days > 0 -> "$days d $hours h $minutes m"
        hours > 0 -> "$hours h $minutes m"
        else -> "$hours h $minutes m"
    }
}
