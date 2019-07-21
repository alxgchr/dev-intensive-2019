package ru.skillbranch.devintensive.models

import java.lang.NumberFormatException

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    var count: Int = 0

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    //    fun listenAnswer(answer:String):Pair<String,Triple<Int,Int,Int>>{
//        return if(question.answer.contains(answer)){
//            question = question.nextQuestion()
//            "Отлично - это правильный ответ!\n${question.question}" to status.color
//        }else{
//            status = status.nextStatus()
//            "Это неправильный ответ!\n${question.question}" to status.color
//        }
//
//    }
    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        if (validate(answer) != "Ok")
            return validate(answer) to status.color
        if (question.answer.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            return "Отлично - ты справился\n${question.question}" to status.color
        } else {
            count++
            return if (count < 3) {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            } else {
                count = 0
                status = Status.NORMAL
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }

        }

    }

    private fun validate(answer: String): String {
        var result = "Ok"
        when (question) {
            Question.NAME -> {
                val char = answer.toCharArray()
                if (char.isEmpty() || char[0].isLowerCase()) {
                    result = "Имя должно начинаться с заглавной буквы\n${question.question}"
                }
            }
            Question.PROFESSION -> {
                //Профессия должна начинаться со строчной буквы
                val char = answer.toCharArray()
                if (char.isEmpty() || char[0].isUpperCase()) {
                    result = "Профессия должна начинаться со строчной буквы\n${question.question}"
                }
            }
            Question.MATERIAL -> {
                //Материал не должен содержать цифр
                if (answer.matches(Regex(pattern = ".*\\d.*"))) {
                    result = "Материал не должен содержать цифр\n${question.question}"
                }
            }
            Question.BDAY -> {
                //Год моего рождения должен содержать только цифры
                try {
                    answer.toInt()
                } catch (e: NumberFormatException) {
                    result = "Год моего рождения должен содержать только цифры\n${question.question}"
                }
            }
            Question.SERIAL -> {
                //Серийный номер содержит только цифры, и их 7
                if (answer.length != 7 || answer.toIntOrNull() == null)
                    result = "Серийный номер содержит только цифры, и их 7\n${question.question}"
//                try {
//                    answer.toInt()
//                }catch (e:Exception){
//                    result = "Серийный номер содержит только цифры, и их 7\n${question.question}"
//                }
            }
            Question.IDLE -> {

            }
        }

        return result
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answer: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question

    }
}