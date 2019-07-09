package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*


data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    init {
        println("His name is $firstName $lastName and his id is $id")

    }

    companion object Factory {
        private var lastId = -1

        //        fun makeUser(fullName: String?): User {
//            lastId++
//
//            val parts: List<String>? = fullName?.split(" ")
//
//            val firstName = parts?.getOrNull(0)
//            val lastName = parts?.getOrNull(1)
//
//            return User("$lastId", firstName, lastName)
//        }
        fun makeUser(fullName: String?): User {
            lastId++

            val parts: Pair<String?, String?>? = Utils.parseFullName(fullName)

            val firstName = parts?.first
            val lastName = parts?.second

            return User("$lastId", firstName, lastName)
        }
    }

}