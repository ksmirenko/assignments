package patterns.Observer

import java.util.ArrayList

 class Subject {

    private var  observers: ArrayList<Observer> = ArrayList<Observer>()
    private var state: Int = 0

    fun getState(): Int {
        return state
    }

    fun setState(state: Int) {
        this.state = state
        notifyAllObservers()
    }

    fun attach(observer: Observer) {
        observers.add(observer)
    }

    fun notifyAllObservers() {
        for (observer in observers) {
            observer.update()
        }
    }
}

abstract class Observer {
    var subject: Subject = Subject()
    abstract fun update()
}

class BinaryObserver(): Observer() {

    fun BinaryObserver(subject: Subject) {
    this.subject = subject
    this.subject.attach(this)
}

    override fun update() {
        println("Binary String: " + Integer.toBinaryString(subject.getState()))
    }
}

class OctalObserver(): Observer(){

    fun  OctalObserver(subject: Subject) {
        this.subject = subject
        this.subject.attach(this)
    }

    override fun update() {
        println("Octal String: " + Integer.toOctalString(subject.getState()))
    }
}

class HexaObserver(): Observer() {
    fun HexaObserver(subject: Subject) {
        this.subject = subject
        this.subject.attach(this)
    }

    override fun update() {
        println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase())
    }
}

    fun main(args: Array<String>) {

        var subject: Subject = Subject()
        var Bin: BinaryObserver = BinaryObserver()
        Bin.BinaryObserver(subject)
        var Oct: OctalObserver = OctalObserver()
        Oct.OctalObserver(subject)
        var Hex: HexaObserver = HexaObserver()
        Hex.HexaObserver(subject)


        println("First state change: 8")
        subject.setState(8)
        println("\n")
        println("Second state change: 19")
        subject.setState(19)
    }

