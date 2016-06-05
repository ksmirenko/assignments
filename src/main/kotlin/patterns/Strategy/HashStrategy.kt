package patterns.Strategy

interface IHasher {
    fun computeHash(password : String) : String
}

class Context() {
    private var strategy : IHasher = MD5()
    fun setStrategy(str : IHasher){
        strategy = str
    }
    fun executeStrategy(input : String) : String {
        return strategy.computeHash(input);
    }
}

fun main(args: Array<String>) {
    val context = Context()
    println("MD5     is ${context.executeStrategy("Web Biscuit")}")
    context.setStrategy(SHA256())
    println("SHA-256 is ${context.executeStrategy("Web Biscuit")}")
}
