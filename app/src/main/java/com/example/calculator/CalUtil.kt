package com.example.calculator

object CalUtil {

    // Input string: "12+52" // "32+58-12"
    fun addNumbers(inputString: String) : String {
        val plusIndex = inputString.indexOf("+")

        if (plusIndex == -1) return ""

        val firstNumberString = inputString.substring(0, plusIndex)
        val secondNumberString = inputString.substring(plusIndex + 1)

        val firstNumber = firstNumberString.toInt()
        val secondNumber = secondNumberString.toInt()

        val answer = firstNumber + secondNumber
        return answer.toString()
    }

    // Input string: "12+52"
    fun subNumbers(inputString: String) : String {
        val plusIndex = inputString.indexOf("-")

        if (plusIndex == -1) return ""

        val firstNumberString = inputString.substring(0, plusIndex)
        val secondNumberString = inputString.substring(plusIndex + 1)

        val firstNumber = firstNumberString.toInt()
        val secondNumber = secondNumberString.toInt()

        val answer = firstNumber - secondNumber
        return answer.toString()
    }

    // "12+52" , "15-12" // "32+58-12"
    fun calculate(inputString: String): String {

        return when {
            inputString.contains("+") -> {
                addNumbers(inputString)
            }
            inputString.contains("-") -> {
                subNumbers(inputString)
            }
            else -> ""
        }
    }
}