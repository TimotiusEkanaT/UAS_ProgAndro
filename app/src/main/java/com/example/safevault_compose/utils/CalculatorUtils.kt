package com.example.safevault_compose.utils

fun evaluateExpression(expression: String): String {
    try {
        val expr = expression
            .replace("−", "-")
            .replace("×", "*")
            .replace("÷", "/")
            .replace("%", "/100")

        val tokens = expr.toCharArray()
        val values = mutableListOf<Double>()
        val ops = mutableListOf<Char>()

        var i = 0
        while (i < tokens.size) {
            when {
                tokens[i].isWhitespace() -> i++

                tokens[i].isDigit() || tokens[i] == '.' -> {
                    val sb = StringBuilder()
                    while (i < tokens.size && (tokens[i].isDigit() || tokens[i] == '.')) {
                        sb.append(tokens[i++])
                    }
                    values.add(sb.toString().toDouble())
                }

                tokens[i] in listOf('+', '-', '*', '/') -> {
                    while (ops.isNotEmpty() && hasPrecedence(tokens[i], ops.last())) {
                        val op = ops.removeAt(ops.size - 1)
                        val b = values.removeAt(values.size - 1)
                        val a = values.removeAt(values.size - 1)
                        values.add(applyOp(op, b, a))
                    }
                    ops.add(tokens[i])
                    i++
                }

                else -> return "Error"
            }
        }

        while (ops.isNotEmpty()) {
            val op = ops.removeAt(ops.size - 1)
            val b = values.removeAt(values.size - 1)
            val a = values.removeAt(values.size - 1)
            values.add(applyOp(op, b, a))
        }

        val result = values.last()
        return if (result % 1.0 == 0.0) "%,d".format(result.toLong()) else "%,.4f".format(result)

    } catch (e: Exception) {
        return "Error"
    }
}

private fun hasPrecedence(op1: Char, op2: Char): Boolean {
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false
    return true
}

private fun applyOp(op: Char, b: Double, a: Double): Double {
    return when (op) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> if (b == 0.0) throw ArithmeticException("Divide by zero") else a / b
        else -> 0.0
    }
}