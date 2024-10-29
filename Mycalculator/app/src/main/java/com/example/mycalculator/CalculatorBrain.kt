package com.example.mycalculator

import javax.xml.validation.Validator

class CalculatorBrain
{
    enum class Operation (op : String) {
        SUM("+"),
        SUB("-"),
        MULT("*"),
        DIV("/"),
        SQRT("√"),
        SIGNAL("±"),
        PERCENT("%"),
        RAND("Rand"),
        AC("AC"),
        NONE("");

        companion object {
            fun getOp(value: String): Operation {
                return when (value) {
                    SUM.toString() -> SUM
                    SUB.toString() -> SUB
                    MULT.toString() -> MULT
                    DIV.toString() -> DIV
                    SQRT.toString() -> SQRT
                    SIGNAL.toString() -> SIGNAL
                    PERCENT.toString() -> PERCENT
                    RAND.toString() -> RAND
                    AC.toString() -> AC
                    NONE.toString() -> NONE
                    else -> {
                        NONE
                    }
                }
            }
        }
    var operation : Operation? = null
    var operand : Double = 0.0

    fun doOperation(op : Operation, value : Double) {
        var result = when (operation){
            Operation.SUM -> operand + value
            Operation.SUB -> operand - value
            Operation.MULT -> operand * value
            Operation.DIV -> operand / value
            Operation.SQRT -> Math.sqrt(value)
            Operation.SIGNAL -> -value
            Operation.PERCENT -> value / 100
            Operation.RAND -> Math.random()
            Operation.AC -> 0.0
            Operation.NONE -> value
            null -> 0.0

        }
    }

    }

}