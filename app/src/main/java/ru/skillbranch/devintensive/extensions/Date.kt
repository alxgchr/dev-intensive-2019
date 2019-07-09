package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*
import kotlin.String

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnits: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (timeUnits) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

enum class TimeUnits {
    SECOND {
        override fun plural(num: Int): String {
            return "${if(num!=1) "$num " else ""}${if (num%10 == 1) "секунду" else if (num%10 in 2..4) "секунды" else "секунд"}"
        }
    }, MINUTE {
        override fun plural(num: Int): String {
            return "${if(num!=1) "$num " else ""}${if (num%10 == 1) "минуту" else if (num%10 in 2..4) "минуты" else "минут"}"
        }
    }, HOUR {
        override fun plural(num: Int): String {
            return "${if(num!=1) "$num " else ""}${if (num%10 == 1) "час" else if (num%10 in 2..4) "часа" else "часов"}"
        }
    }, DAY {
        override fun plural(num: Int): String {
            return "${if(num!=1) "$num " else ""}${if (num%10 == 1) "день" else if (num%10 in 2..4) "дня" else "дней"}"
        }
    };

    abstract fun plural(num: Int): String
}

fun Date.humanizeDiff(date: Date = Date()): String {

    var interval = date.time - time
    var isFuture = true


    if(interval>0)
        isFuture = false

    interval = abs(interval)

    val str = when {

        interval / SECOND <= 1 -> "только что"
        interval / SECOND <= 45 -> if(isFuture) {"через несколько секунд"} else{"несколько секунд назад"}
        //interval / SECOND <= 75 -> if(isFuture) {"через минуту"} else{"минуту назад"}

        interval / MINUTE <=45 -> if(isFuture) {"через ${TimeUnits.MINUTE.plural((interval/ MINUTE).toInt())}"} else{"${TimeUnits.MINUTE.plural((interval/ MINUTE).toInt())} назад"}
        interval / HOUR <=22 -> if(isFuture) {"через ${TimeUnits.MINUTE.plural((interval/ HOUR).toInt())}"} else{"${TimeUnits.HOUR.plural((interval/ HOUR).toInt())} назад"}
        interval / HOUR <=22 -> if(isFuture) {"через ${TimeUnits.HOUR.plural((interval/ HOUR).toInt())}"} else{"${TimeUnits.HOUR.plural((interval/ HOUR).toInt())} назад"}
        interval / DAY <=360 -> if(isFuture) {"через ${TimeUnits.DAY.plural((interval/ DAY).toInt())}"} else{"${TimeUnits.DAY.plural((interval/ DAY).toInt())} назад"}
        else-> if(isFuture) {"более чем через год"} else{"более года назад"}
    }


    return str
}