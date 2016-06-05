/**
 * Example for prototype pattern
 *
 * Author: Mikhail Kita, group 271
 */

package patterns.Prototype

import java.util.*

abstract class Monster() : Cloneable {
    abstract var pos : Pair<Float, Float>
    abstract var name : String
    abstract val type : String
    var health = 1000

    abstract fun attack()

    // this function also can be overridden in inherited classes
    public override fun clone() : Monster {
        return (super.clone() as Monster)
    }
}

class GroundMonster() : Monster() {
    override var pos  = Pair(0f, 0f)
    override val type = "ground"
    override var name = "Deathclaw"

    override fun attack() {
        println("Ground monster '$name' at position $pos attacked you. He has $health HP")
    }

    override fun clone() : GroundMonster {
        return (super.clone() as GroundMonster)
    }
}

class AirMonster() : Monster() {
    override var pos  = Pair(0f, 0f)
    override val type = "air"
    override var name = "Dragon"

    override fun attack() {
        println("Air monster '$name' at position $pos attacked you. He has $health HP")
    }
}

class WaterMonster() : Monster() {
    override var pos  = Pair(0f, 0f)
    override val type = "water"
    override var name = "Drowner"

    override fun attack() {
        println("Water monster '$name' at position $pos attacked you. He has $health HP")
    }
}

private class MonsterMaker() {
    private val existingMonsters = LinkedList<Monster>()

    fun makeMonster(type : String) : Monster {
        var found = false

        for (m in existingMonsters) {
            if (m.type == type) {
                found = true
                val monster = m.clone()
                monster.health = 1000
                existingMonsters.add(monster)
                break
            }
        }
        if (!found) {
            when (type) {
                "ground" -> existingMonsters.add(GroundMonster())
                "air"    -> existingMonsters.add(AirMonster())
                "water"  -> existingMonsters.add(WaterMonster())
                else     -> throw Exception("Incorrect type of monster")
            }
        }
        return existingMonsters.last
    }
}

fun main (args : Array<String>) {
    val maker = MonsterMaker()

    val monster = maker.makeMonster("air")
    monster.attack()
    monster.health -= 150
    monster.pos = Pair(100f, 50f)

    val monster1 = maker.makeMonster("air")
    monster.attack()
    monster1.attack()
}