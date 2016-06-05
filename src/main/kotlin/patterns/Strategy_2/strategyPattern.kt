package patterns.Strategy_2


interface IStrategy {
    fun doOperation(n1: Int, n2: Int): Int
}

class OperationAdd(): IStrategy {
    override fun doOperation(n1: Int, n2: Int) = n1 + n2
}
class OperationSub(): IStrategy {
    override fun doOperation(n1: Int, n2: Int) = n1 - n2
}
class OperationMult(): IStrategy {
    override fun doOperation(n1: Int, n2: Int) = n1 * n2
}

class Context(private val strategy: IStrategy) {
    fun executeStrat(n1: Int, n2: Int): Int =
        strategy.doOperation(n1, n2)
}


fun main(args: Array<String>) {
    var context = Context(OperationAdd())
    println("15 + 10 = ${context.executeStrat(15, 10)}")

    context = Context(OperationSub())
    println("15 - 10 = ${context.executeStrat(15, 10)}")

    context = Context(OperationMult())
    println("15 * 10 = ${context.executeStrat(15, 10)}")
}