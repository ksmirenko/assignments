abstract class EnglishCharacter
{
    protected abstract var symbol : Char
    protected abstract var width : Int
    protected abstract var height : Int

    abstract fun printCharacter()
}


class CharacterA : EnglishCharacter()
{
    override var symbol: Char = 'A'
    override var width = 10
    override var height = 20

    override fun printCharacter() {
        println("Simbol = $symbol Width = $width Height = $height");
    }
}

class CharacterB : EnglishCharacter()
{
    override var symbol = 'B'
    override var width = 20
    override var height = 30

    override fun printCharacter() {
        println("Simbol = $symbol Width = $width Height = $height");
    }
}

class CharacterC : EnglishCharacter()
{
    override var symbol = 'C'
    override var width = 40
    override var height = 50

    override fun printCharacter() {
        println("Simbol = $symbol Width = $width Height = $height");
    }
}

class FlyweightFactory
{
    fun getCharacter(characterCode : Int) : EnglishCharacter
    {
        var character : EnglishCharacter? = null

        when (characterCode){
            1 -> character = CharacterA()
            2 -> character = CharacterB()
            3 -> character = CharacterC()
        }


        return character ?: throw Exception("Unknown letter");
    }
}

fun main (args : Array<String>)
{
    val factory : FlyweightFactory  = FlyweightFactory();
    val characterCodes : Array<Int> = arrayOf(1, 2, 3);
    for (nextCode in characterCodes){
        val character : EnglishCharacter = factory.getCharacter(nextCode);
        character.printCharacter();
    }
}
