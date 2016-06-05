package patterns.State

interface State {
    fun on()
    fun off()
    fun print()
    fun addPaper(count: Int)
}

class PowerOffState(var printer: Printer) : State {

    override fun on() {
        println("Принтер включен")
        printer.state = printer.waitingState
    }

    override fun off() {
        println("Принтер и так выключен")
    }

    override fun print() {
        println("Принтер отключен, печать невозможна")
    }

    override fun addPaper(count: Int) {
        printer.addPaper(count)
        println("Бумага добавлена")
    }
}

class WaitingState(var printer: Printer) : State {

    override fun on() {
        println("Принтер уже и так включен")
    }

    override fun off() {
        println("Принтер выключен")
    }

    override fun print() {
        if (printer.countPaper > 0) {
            println("Сейчас всё распечатаем")
            printer.addPaper(-1)
        } else {
            printer.state = printer.paperOffState
            printer.printDocument()
        }
    }

    override fun addPaper(count: Int) {
        printer.addPaper(count)
        println("Бумага добавлена")
    }
}

class PaperOffState(var printer: Printer) : State {

    override fun on() {
        println("Принтер уже и так включен")
    }

    override fun off() {
        println("Принтер выключен")
        printer.state = printer.powerOffState
    }

    override fun print() {
        if (printer.countPaper > 0) {
            printer.state = printer.printState
            printer.printDocument()
        } else {
            println("Бумаги нет, печатать не буду")
        }

    }

    override fun addPaper(count: Int) {
        println("Добавляем бумагу")
        printer.addPaper(count)
        if (printer.countPaper > 0)
            printer.state = printer.waitingState
    }
}

class PrintState(var printer: Printer) : State {

    override fun on() {
        println("Принтер уже и так включен")
    }

    override fun off() {
        println("Принтер выключен")
    }

    override fun print() {
        if (printer.countPaper > 0) {
            println("Идёт печать...")
            printer.addPaper(-1)
            printer.state = printer.waitingState
        } else {
            printer.state = printer.paperOffState
            printer.printDocument()
        }

    }

    override fun addPaper(count: Int) {
        printer.addPaper(count)
        println("Бумага добавлена")
    }
}

class Printer {
    var countPaper = 0; private set

    val paperOffState = PaperOffState(this)
    val powerOffState = PowerOffState(this)
    val printState = PrintState(this)
    val waitingState = WaitingState(this)

    var state: State = waitingState

    fun printDocument() = state.print()
    fun powerOff() = state.off()
    fun powerOn() = state.on()
    fun addPaper(count: Int) {
        countPaper += count
    }

}

fun main(args: Array<String>) {
    val printer = Printer()
    printer.powerOn()
    printer.printDocument()
    printer.addPaper(3)
    printer.printDocument()
    printer.printDocument()
    printer.printDocument()
    printer.printDocument()
    printer.powerOff()
}
