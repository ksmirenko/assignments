package classroom.c04.kotlin

data class Pair<A: Comparable<A>, B: Comparable<B>>(
        val first: A, val second: B
): Comparable<Pair<A, B>> {
    override fun compareTo(other: Pair<A, B>): Int {
        val firstCompare = first.compareTo(other.first);
        if (firstCompare != 0) { return firstCompare; }
        return second.compareTo(other.second);
    }
}
