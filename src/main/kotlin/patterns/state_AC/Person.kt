package patterns.state_AC

import java.util.*

/**
 * Created by Alexander Chebykin
 * */

private abstract class State() {
    abstract fun act()
    abstract fun nextState(state : String)
}

private class AliveState(private val person: Person) : State() {
    override fun act() {
        println("I'm alive!\n")
        nextState("dead")
    }

    override fun nextState(state : String) {
        person.changeState(state)
    }
}

private class DeadState(private val person: Person) : State() {
    override fun act() {
        println("I'm dead!\n")
        nextState("zombie")
    }

    override fun nextState(state : String) {
        person.changeState(state)
    }
}

private class ZombieState(private val person: Person) : State() {
    var count = 0
    override fun act() {
        println("BRAAAAAINS")
        if (++count == 3) {
            nextState("alive")
            count = 0
            return
        }
        nextState("zombie")
    }

    override fun nextState(state : String) {
        println("Trying to become alive\n")
        person.changeState(state)
    }
}
class Person() {

    private val states = hashMapOf(Pair("alive", AliveState(this)),
            Pair("dead", DeadState(this)), Pair("zombie", ZombieState(this)))

    private var currentState : State = states["alive"] ?: throw NoSuchElementException()

    fun changeState(newState : String){
        currentState = states[newState] ?: throw NoSuchElementException()
    }

    fun act(){
        currentState.act()
    }
}

fun main(args : Array<String>) {
    val human = Person()
    for (i in 0..9) human.act()
}