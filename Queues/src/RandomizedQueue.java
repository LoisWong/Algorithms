import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private List<Item> list;
    // construct an empty randomized queue
    public RandomizedQueue() {
        list = new ArrayList<Item>();
    }

    // is the queue empty?
    public boolean isEmpty() {
        return list.size() == 0;
    }

    // return the number of items on the queue
    public int size() {
        return list.size();
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        list.add(item);
    }
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(size());
        return (Item) list.remove(index);
    }
    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(size());
        return (Item) list.get(index);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }
    private class ListIterator<E> implements Iterator<E> {
        public boolean hasNext() {
            return size() > 0;
        }
        public E next() {
            if (isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            return (E) dequeue();
        }
        public void remove() {
            throw new UnsupportedOperationException();      
        }
        
    }
    // unit testing
    public static void main(String[] args) {
        return;
    }
}
