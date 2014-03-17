import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Deque<Item> implements Iterable<Item> {
    private List<Item> list;
    // construct an empty deque
    public Deque() {
       list = new LinkedList<Item>();
    }
    // is the deque empty?
    public boolean isEmpty() {
        return list.isEmpty();
    }
    // return the number of items on the deque
    public int size() {
        return list.size();
    }
    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("");
        }
        list.add(0, item);
    }
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("");
        }
        list.add(item);
    }
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) 
            throw new java.util.NoSuchElementException();
        return (Item) list.remove(0);
    }
    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) 
            throw new java.util.NoSuchElementException();
        return (Item) list.remove(list.size() - 1);
    }
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return this.new DequeIterator<Item>();
    }
    
    private class DequeIterator<E> implements Iterator<E> {
        public boolean hasNext() {
            return size() > 0;
        }
        public E next() {
            if (isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            return (E) removeFirst();
        }
        public void remove() {
            throw new UnsupportedOperationException();      
        }
        
    }
    // unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(2);
        System.out.println("size: "+deque.size()+ " removed value: "+deque.removeFirst());
        deque.addFirst(3);
        deque.addLast(7);
        System.out.println("size: "+deque.size()+ " removed value: "+deque.removeLast());
        for (Integer s : deque)
        {
            System.out.print(" " + s + " ");
        }
    }
}