package patterns.template_method

abstract class DataParser {
    //Primitive operations
    abstract protected fun readData()
    abstract protected fun parseData()

    //Common implementations
    private fun writeData()
    {
        println("Writing generated output to a file")
    }

    //Template method
    fun parseDataAndGenerateOutput() {
        readData()
        parseData()
        writeData()
    }
}

class ConsoleDataParser : DataParser() {
    //Overriding placeholder methods
    override fun readData() {
        println("Reading data from console")
    }

    override fun parseData() {
        println("Parsing data from console")
    }
}

class FileDataParser : DataParser() {
    //Overriding placeholder methods
    override fun readData() {
        println("Reading data from file")
    }

    override fun parseData() {
        println("Parsing data from file")
    }
}

fun main (args : Array<String>) {
    println("Data from console")
    val consoleDataParser = ConsoleDataParser()
    consoleDataParser.parseDataAndGenerateOutput()
    println("-----------")
    println("Data from file")
    val fileDataParser = FileDataParser()
    fileDataParser.parseDataAndGenerateOutput()
}
