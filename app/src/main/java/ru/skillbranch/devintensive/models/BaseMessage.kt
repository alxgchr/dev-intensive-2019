package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object {
        var lastId = -1

        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date,
            type: String = "text",
            payload: Any,

            isIncoming: Boolean = false
        ): BaseMessage {
            lastId++
            return when (type) {
                "text" -> TextMessage(id = "$lastId", from = from, chat = chat, date = date, isIncoming = isIncoming, text = payload as String)
                else -> ImageMessage(id = "$lastId", from = from, chat = chat, date = date, isIncoming = isIncoming, image = payload as String)
            }
        }
    }
}