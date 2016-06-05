/* Implementation of the Template method pattern
by Sokolova Polina */

package patterns.templateMethod

abstract class Instrument() {
    abstract protected fun instrument() : String
    abstract protected fun tuning()
    abstract protected val model : String
    fun play() {
        println("Let's buy " + instrument() + model)
        tuning()
        println("Enjoy playing!\n")
    }
}

class Guitar(override val model : String) : Instrument() {
    override fun instrument() : String {
        return "a guitar "
    }
    override fun tuning() {
        println("Tuning a guitar")
    }
}

class Piano(override val model : String) : Instrument() {
    override fun instrument() : String {
        return "a piano "
    }
    override fun tuning() {
        println("Setting a piano")
    }
}

class Drums(override val model : String) : Instrument() {
    override fun instrument() : String {
        return "a drum kit "
    }
    override fun tuning() {
        println("Setting drums")
    }
}

fun main(args : Array<String>) {
    val gibson = Guitar("Gibson Les Paul Standard 2015")
    gibson.play()

    val kawai = Piano("KAWAI ES100")
    kawai.play()

    val yamaha = Drums("Yamaha DTX950K")
    yamaha.play()
}