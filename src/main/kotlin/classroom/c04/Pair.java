package classroom.c04;

import org.jetbrains.annotations.NotNull;

class Pair
        <A extends Comparable<? super A>, B extends Comparable<? super B>>
        implements Comparable<Pair<? extends A, ? extends B>> {
    private A first;
    private final B second;

    public Pair(A first, B second) {
        this.first  = first;
        this.second = second;
    }

    private A getFirst() { return first;  }
    public void setFirst(A newFirst) { first = newFirst;  }

    private B getSecond() { return second; }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pair)) { return false; }
        Pair otherPair = (Pair) other;
        return first.equals(otherPair.getFirst()) &&
                second.equals(otherPair.getSecond());
    }

    @Override
    public int hashCode() {
        return first.hashCode() * 31 + second.hashCode();
    }

    @Override
    public int compareTo(@NotNull Pair<? extends A, ? extends B> other) {
        int firstCompare = first.compareTo(other.first);
        if (firstCompare != 0) { return firstCompare; }
        return second.compareTo(other.second);
    }
}
