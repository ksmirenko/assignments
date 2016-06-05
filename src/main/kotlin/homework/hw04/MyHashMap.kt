// Homework #04 (29.09 - 13.10)
// Author: Kirill Smirenko, group 271
package homework.hw04

import java.util.*

/**
 * Map implementation with hash table.
 * @param K The type of table's keys (must be comparable).
 * @param V The type of table's values.
 */
class MyHashMap<K, V>() : MyAbstractMap<K, V> where K : Comparable<K> {
    private val TABLE_SIZE = 1024
    private val hashTable = Array(TABLE_SIZE, { x -> ArrayList<MyMapEntry<K, V>>() })

    override fun insert(newKey : K, newValue : V) {
        remove(newKey)
        val index = newKey.hashCode() % TABLE_SIZE
        hashTable[index].add(MyMapEntry(newKey, newValue))
    }

    override fun search(key : K) : V? {
        val tableIndex = key.hashCode() % TABLE_SIZE
        val listIndex = hashTable[tableIndex].indexOfFirst { x -> x.equals(MyMapEntry(key, null)) }
        return if (listIndex != -1)
            hashTable[tableIndex].elementAt(listIndex).value
        else null
    }

    override fun remove(key : K) {
        fun Pair<K, V>.equals(another : Pair<K, V>) = (this.first.equals(another.first))
        val index = key.hashCode() % TABLE_SIZE
        try {
            val entry = hashTable[index].first { x -> x.equals(MyMapEntry(key, null)) }
            hashTable[index].remove(entry)
        }
        catch (e : NoSuchElementException) {
        }
    }

    override fun forEach(f : (K, V) -> Unit) {
        for (list in hashTable) {
            for (entry in list) {
                f(entry.key, entry.value)
            }
        }
    }

    override fun newClassInstance() : MyAbstractMap<K, V> = MyHashMap()

    class MyMapEntry<K, V>(val key : K, val value : V) {
        override fun equals(other : Any?) : Boolean = (other is MyMapEntry<*, *>) && (key?.equals(other.key) ?: false)
        override fun hashCode() : Int = key?.hashCode() ?: 0
    }
}