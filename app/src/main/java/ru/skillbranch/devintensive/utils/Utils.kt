package ru.skillbranch.devintensive.utils

class Utils {
    companion object {
        fun parseFullName(fullName: String?): Pair<String?, String?> {
            if (fullName?.length == 0 || fullName == " ")
                return Pair(null, null)

            val parts: List<String?>? = fullName?.split(" ")

            var firstName: String? = parts?.getOrNull(0)
            var lastName: String? = parts?.getOrNull(1)

            if (firstName?.length == 0)
                firstName = null
            if (lastName?.length == 0)
                lastName = null


            return Pair(firstName, lastName)
        }

        fun transliteration(payload: String, divider: String = " "): String {
            return ""
        }

        fun toInitials(firstName: String?, lastName: String?): String? {
            val localFirstName = firstName?.trim()
            val localLastName = lastName?.trim()

            val inicialFirst: String?
            val inicialLast: String?

//            if(localFirstName.isNullOrEmpty() && localLastName.isNullOrEmpty())
//                return null
//            if(localFirstName?.isNotEmpty())
            if (localFirstName.isNullOrEmpty()) {
                inicialFirst = ""
            } else {
                inicialFirst = localFirstName.substring(0, 1).toUpperCase()
            }

            if (localLastName.isNullOrEmpty()) {
                inicialLast = ""
            } else {
                inicialLast = localLastName.substring(0, 1).toUpperCase()
            }

            if (inicialFirst.isEmpty() && inicialLast.isEmpty())
                return null

            return inicialFirst + inicialLast

        }
    }
}
