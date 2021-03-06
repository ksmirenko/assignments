package patterns.Interpreter

import java.util.*

abstract class Expression {
    abstract fun interpret(context: Map<String, Expression>): Int
}

class Number(private val number: Int) : Expression() {
    override fun interpret(context: Map<String, Expression>): Int {
        return number
    }
}

class Variable(private val variable: String) : Expression() {
    override fun interpret(context: Map<String, Expression>): Int {
        return context[variable]?.interpret(context) ?: variable.toInt()
    }
}

class Plus(var leftOperand: Expression, var rightOperand: Expression) : Expression() {
    override fun interpret(context: Map<String, Expression>): Int {
        return leftOperand.interpret(context) + rightOperand.interpret(context)
    }
}

class Minus(var leftOperand: Expression, var rightOperand: Expression) : Expression() {
    override fun interpret(context: Map<String, Expression>): Int {
        return leftOperand.interpret(context) - rightOperand.interpret(context)
    }
}

class Multi(var leftOperand: Expression, var rightOperand: Expression) : Expression() {
    override fun interpret(context: Map<String, Expression>): Int {
        return leftOperand.interpret(context) * rightOperand.interpret(context)
    }
}

class Calculator(expression: String): Expression() {
    private val syntaxTree: Expression
    init{
        val expressionStack = Stack<Expression>()
        for (token in expression.split(" ")) {
            when (token) {
                "+" -> {
                    val right = expressionStack.pop()
                    val left = expressionStack.pop()
                    val subExpression = Plus(left, right)
                    expressionStack.push(subExpression)}
                "-" -> {
                    val right = expressionStack.pop()
                    val left = expressionStack.pop()
                    val subExpression = Minus(left, right)
                    expressionStack.push(subExpression)}
                "*" -> {
                    val right = expressionStack.pop()
                    val left = expressionStack.pop()
                    val subExpression = Multi(left, right)
                    expressionStack.push(subExpression)}
                else -> expressionStack.push(Variable(token))
            }
        }
        syntaxTree = expressionStack.pop()
    }
    override fun interpret(context: Map<String, Expression>): Int {
        return syntaxTree.interpret(context)}
}

fun main(args: Array<String>) {
    val expression = "a b c * 6 - +"
    val sentence = Calculator(expression)
    val tokens = HashMap<String,Expression>()
    tokens.put("a", Number(1))
    tokens.put("b", Number(2))
    tokens.put("c", Number(3))
    val result = sentence.interpret(tokens)
    println(result)
}

