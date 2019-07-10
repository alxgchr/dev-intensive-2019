package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {
    var str = this
    if (length > count){
        str = substring(0, count)
    }

    str = str.trimEnd()

    if (length > count)
        str += "..."
    return str
}