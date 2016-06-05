package patterns.Flyweight

import java.util.*

abstract class House {
    abstract  val floors: Int
    abstract val materials: String
    abstract fun Build (color: String, street: String)
}

class PanelHouse : House() {
    override val floors = 16
    override val materials = "panels"
    override fun Build(color: String, street: String ){
        println("House is built of $materials; $floors floors; $color color; $street street")
    }
}

class BrickHouse : House() {
    override val floors = 5
    override val materials = "bricks"
    override fun Build(color: String, street: String ){
        println("House is built of $materials; $floors floors; $color color; $street street")
    }
}

class HouseFactory() {
    var houses = HashMap<Int, House>()

    fun  GetHouse(key: Int): House?{
        if (houses.containsKey(key)) return houses[key]
        else {
            if (key == 5) houses.put(key, BrickHouse())
            if (key == 16) houses.put(key, PanelHouse())
            return houses[key]
        }
    }
}

fun main(args: Array<String>) {
    val houseFactory = HouseFactory()
    houseFactory.GetHouse(5)?.Build("red", "First")
    houseFactory.GetHouse(16)?.Build("green", "Baker")
}