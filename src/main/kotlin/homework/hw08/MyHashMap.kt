// Homework #04 (29.09 - 13.10)
// Author: Kirill Smirenko, group 271
package homework.hw08

import java.util.*

/**
 * Map implementation with hash table.
 * @param K The type of table's keys (must be comparable).
 * @param V The type of table's values.
 */
class MyHashMap<K, V>() : MyAbstractMap<K, V> where K : Comparable<K> {
    private val TABLE_SIZE = 1024
    private val hashTable = Array(TABLE_SIZE, { ArrayList<MyMapEntry<K, V>>() })

    override fun insert(newKey : K, newValue : V) {
        remove(newKey)
        val index = newKey.hashCode() % TABLE_SIZE
        hashTable[index].add(MyMapEntry(newKey, newValue))
    }

    override fun insert(newEntry : MyMapEntry<K, V>) {
        remove(newEntry.key)
        val index = newEntry.key.hashCode() % TABLE_SIZE
        hashTable[index].add(newEntry)
    }

    override fun search(key : K) : V? {
        val tableIndex = key.hashCode() % TABLE_SIZE
        val listIndex = hashTable[tableIndex].indexOfFirst { x -> x.equals(MyMapEntry(key, null)) }
        return if (listIndex != -1)
            hashTable[tableIndex].elementAt(listIndex).value
        else null
    }

    override fun remove(key : K) {
        fun MyMapEntry<K, V>.equals(another : MyMapEntry<K, V>) = (this.key.equals(another.key))
        val index = key.hashCode() % TABLE_SIZE
        try {
            val entry = hashTable[index].first { x -> x.equals(MyMapEntry(key, null)) }
            hashTable[index].remove(entry)
        }
        catch (e : NoSuchElementException) {
        }
    }

    override fun newClassInstance() : MyAbstractMap<K, V> = MyHashMap()

    override fun iterator() : Iterator<MyMapEntry<K, V>> = HashMapIterator(this)

    private class HashMapIterator<K : Comparable<K>, V>(map : MyHashMap<K, V>) : Iterator<MyMapEntry<K, V>> {
        private val arrayIterator = map.hashTable.iterator()
        private var listIterator = nextListIterator()

        override fun hasNext() : Boolean {
            if (listIterator.hasNext()) return true
            try {
                listIterator = nextListIterator()
                return listIterator.hasNext()
            }
            catch (e : NoSuchElementException) {
                return false
            }
        }

        override fun next() : MyMapEntry<K, V> {
            if (!listIterator.hasNext()) {
                listIterator = nextListIterator()
            }
            return listIterator.next()
        }

        private fun nextListIterator() : Iterator<MyMapEntry<K, V>> {
            while (arrayIterator.hasNext()) {
                val list = arrayIterator.next()
                if (list.size > 0) return list.iterator()
            }
            return EmptyIterator()
        }
    }
}