package patterns.Proxy

/**
 * @author Antropov Igor
 */

interface IBalance {
    fun getAccountBalance(): String
}

class Balance : IBalance {
    override fun getAccountBalance(): String {
        return "500"
    }
}

class ProxyBalance : IBalance {
    private var realBalance: Balance? = null

    fun authorization(name: String, password: String){
        if (name == "Bobby" && password == "qwerty")
            realBalance = Balance()
    }

    override fun getAccountBalance(): String {
        return realBalance?.getAccountBalance() ?: "You are not logged on"
    }
}

fun main(args: Array<String>) {
    val bobbyBalance = ProxyBalance()

    println(bobbyBalance.getAccountBalance())

    bobbyBalance.authorization("Bobby", "1234567890")
    println(bobbyBalance.getAccountBalance())

    bobbyBalance.authorization("Bobby", "qwerty")
    println(bobbyBalance.getAccountBalance())
}
