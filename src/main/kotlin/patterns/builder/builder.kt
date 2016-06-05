// Example for pattern "Bilder"
// Alekseev Aleksei, group 271

@file:Suppress("unused")

package patterns.builder

/* "Product" */
class House {
    var foundation: String = ""
    var wall: String = ""
    var roof: String = ""
}

/* "Abstract Builder" */
abstract class HouseBuilder() {
    var house : House = House()
    fun createNewHouse() {
        house = House()
    }
    abstract fun buildFoundation()
    abstract fun buildWall()
    abstract fun buildRoof()
}

/* "ConcreteBuilder" */
internal class WoodenHouseBuilder : HouseBuilder() {
    override fun buildFoundation() {
        house.foundation = "pier"
    }
    override fun buildWall() {
        house.wall = "wooden"
    }
    override fun buildRoof() {
        house.roof = "slate"
    }
}

/* "ConcreteBuilder" */
internal class BrickHouseBuilder : HouseBuilder() {
    override fun buildFoundation() {
        house.foundation = "strip"
    }
    override fun buildWall() {
        house.wall = "brick"
    }
    override fun buildRoof() {
        house.roof = "tile"
    }
}

/* "Director" */
class Director (internal var houseBuilder : HouseBuilder) {
    fun constructHouse() {
        houseBuilder.createNewHouse()
        houseBuilder.buildFoundation()
        houseBuilder.buildWall()
        houseBuilder.buildRoof()
    }
}

/*
fun main(args : Array<String>) {
    var director = Director(WoodenHouseBuilder())
    director.constructHouse()
    var temp = director.houseBuilder.house
    var house = "foundation: " + temp.foundation + "\nwalls: " + temp.wall + "\nroof: " + temp.roof
    println("=========Wooden house=========")
    print(house)
    println()
    director = Director(BrickHouseBuilder())
    director.constructHouse()
    temp = director.houseBuilder.house
    house = "foundation: " + temp.foundation + "\nwalls: " + temp.wall + "\nroof: " + temp.roof
    println("=========Brick house=========")
    print(house)
}
*/