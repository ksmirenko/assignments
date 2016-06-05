package patterns.Visitor

interface ItemElement {
    fun accept(visitor: ShoppingCartVisitor): Int
}

class Book(val price: Int, val name: String): ItemElement {
    override fun accept(visitor: ShoppingCartVisitor): Int {
        return visitor.visit(this)
    }
}

class Fruit(val PricePerKg: Int, val weight: Int, val name: String): ItemElement {
    override fun accept(visitor: ShoppingCartVisitor): Int {
        return visitor.visit(this)
    }
}

interface ShoppingCartVisitor {
    fun visit(book: Book): Int
    fun visit(fruit: Fruit): Int
}

class ShoppingCartVisitorA: ShoppingCartVisitor {
    override fun visit(book: Book): Int {
        var cost: Int
        if (book.price > 50) {
            cost = book.price - 5
        } else {
            cost = book.price
        }
        println("Book: ${book.name} cost = $cost")
        return cost
    }

    override fun visit(fruit: Fruit): Int {
        var cost = fruit.PricePerKg * fruit.weight
        println("${fruit.name} cost = $cost")
        return cost
    }
}


fun main(args: Array<String>) {
    fun calculatePrice(items: Array<ItemElement>): Int {
        val visitor = ShoppingCartVisitorA()
        var sum = 0
        items.forEach {
            sum += it.accept(visitor)
        }
        return sum
    }
    val items = arrayOf(Book(20, "book1"), Book(100, "book2"), Fruit(10, 2, "Banana"), Fruit(5, 5, "Apple"))
    val total = calculatePrice(items)
    println("Total Cost = $total")
}