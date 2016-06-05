package classroom.c06;

import java.util.LinkedList;

public class Main {
    public void hello() {
        ArrayOrderedList list = new ArrayOrderedList(100);
        try {
            list.get(10);
        } catch (IndexOutOfCollectionException e) {
            System.out.println("Doing stuff");
        }
    }


    class IndexOutOfCollectionException extends Exception {}

    abstract class IOrderedList {
        public abstract int get(@SuppressWarnings("SameParameterValue") int index) throws IndexOutOfCollectionException;

        IndexOutOfCollectionException getOutOfCollectionException() {
            return new IndexOutOfCollectionException();
        }
    }

    class ArrayOrderedList extends IOrderedList {
        private final int size;
        private final int[] array;

        public ArrayOrderedList(@SuppressWarnings("SameParameterValue") int size) {
            this.size = size;
            array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = 5;
            }
        }

        @Override
        public int get(int index) throws IndexOutOfCollectionException {
            if (index < 0 || index >= size) {
                throw getOutOfCollectionException();
            }
            return array[index];
        }
    }

    class OrderedLinkedList extends IOrderedList {
        private final LinkedList<Integer> list;

        public OrderedLinkedList(int size) {
            list = new LinkedList<Integer>();
            for (int i = 0; i < size; i++) {
                list.add(5);
            }
        }

        @Override
        public int get(int index) throws IndexOutOfCollectionException {
            if (index < 0) {
                throw getOutOfCollectionException();
            }
            int curElemIndex = 0;
            for (Integer e: list) {
                if (curElemIndex == index) { return e; }
                curElemIndex++;
            }
            throw getOutOfCollectionException();
        }
    }
}
