@file:Suppress("SENSELESS_COMPARISON")

package patterns.Memento_

interface IOriginator {
    fun getMemento(): Memento
    fun setMemento(memento: Memento)
}

class Memento {
    private var _helth: Int = 0

    constructor(health: Int) {
        _helth = health
    }

    fun getState(): Int {
        return _helth
    }
}

class Player: IOriginator {
    private var _helth: Int = 0
    constructor() {
        _helth = 100
    }

    fun getHurt( hurt: Int) {
        _helth -= hurt
    }

    fun getCure(cure: Int) {
        _helth += cure
    }

    fun printPulse() {
        when {
            (_helth > 70)                 ->   return println("Health status indicator: green")
            (_helth <= 70 && _helth > 40) ->   return println("Health status indicator: yellow")
            else                          ->   return println("Health status indicator: red")
        }
    }

    override fun setMemento(memento: Memento) {
        _helth = memento.getState()
    }

    override fun getMemento(): Memento {
        val tmp = Memento(_helth)
        return tmp
    }
}

class gameUtils {
    private var _memento = Memento(0)
    fun saveState(originator: IOriginator) {
        if (originator == null) { return println("Originator is null") }

        _memento = originator.getMemento()
        println("Save state")
    }

    fun loadState(originator: IOriginator) {
        if (originator == null) { return println("Originator is null") }
        if (_memento == null)   { return println("Memento is null") }

        originator.setMemento(_memento)
        println("Load state")
    }
}

fun main(args: Array<String>) {
    val gameUtils = gameUtils()
    val player = Player()

    player.getHurt(20)
    player.getHurt(30)
    player.getHurt(20)
    player.printPulse()

    gameUtils.saveState(player)

    player.getCure(50)
    player.printPulse()

    gameUtils.loadState(player)

    player.printPulse()
}