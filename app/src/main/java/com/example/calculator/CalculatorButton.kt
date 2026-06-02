package com.example.calculator

import net.objecthunter.exp4j.ExpressionBuilder

object CalculatorLogic {
    fun calculate(equation: String): String {
        return try {
            val refined = equation.replace("÷", "/").replace("*", "*")
            val result = ExpressionBuilder(refined).build().evaluate()
            if (result == result.toLong().toDouble()) result.toLong().toString()
            else result.toString()
        } catch (e: Exception) {
            "Error"
        }
    }
}