// Задания 01-04 от 08.09.2015
// Автор: Кирилл Смиренко, группа 271
package homework.hw01

abstract class Tree {}
open class Empty() : Tree() {}
open class Node<B>(val value : B, val l : Tree, val r : Tree) : Tree() {}

fun intLeaf(v : Int) : Tree = Node(v, Empty(), Empty())

@Suppress("unused")
fun Tree.toText() : String {
    fun Tree.toText_() : List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Node<*> -> {
                val lText = l.toText_().map { "| $it"}
                val rText = r.toText_().map { "| $it"}
                val vText = listOf("$value\n")
                return lText + vText + rText
            }
            else -> throw Exception("Unknown class")
        }
    }
    val builder = StringBuilder()
    val lines = this.toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

// 02. Поиск максимальной суммы в дереве на путях от корня до листьев

fun Tree.maxPath() : Int = foldDown( { a, b -> a + b }, { a, b -> Math.max(a, b) }, 0, this)

// 03. Обобщенный метод для обхода деревьев сверху вниз.

fun <B> foldDown(fDown : (B, B) -> B, fUp : (B, B) -> B, acc : B, tree : Tree) : B {
    when (tree) {
        is Empty -> return acc
        is Node<*>  -> {
            @Suppress("UNCHECKED_CAST")
            val acc1 = fDown(acc, tree.value as B)
            val resLeft  = foldDown(fDown, fUp, acc1, tree.l)
            val resRight = foldDown(fDown, fUp, acc1, tree.r)
            return fUp(resLeft, resRight)
        }
        else -> throw Exception("Unknown class")
    }
}