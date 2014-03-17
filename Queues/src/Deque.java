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
    // Test case: NullPointer when adding null to deque.
       Deque<String> d = new Deque<String>();
       try { 
           String str = null;
           d.addFirst(str); 
           StdOut.println("Test should throw java.lang.NullPointerException: "
                          + "FAIL!");
       } catch (java.lang.NullPointerException e)
       {
           StdOut.println("Test should throw java.lang.NullPointerException: "
                          + "Successfull!");
       }

       // Test case: Throw java.util.NoSuchElementException when deque is 
       //            empty and someone tries to remove an item. 
       d = new Deque<String>();
       try {
           d.removeFirst();
           StdOut.println("Test should throw java.util.NoSuchElementException: " 
                          + "FAIL!");
       } catch (java.util.NoSuchElementException e)
       {
           StdOut.println("Test should throw java.util.NoSuchElementException: "
                          + "Successfull!");
       }

       try {
           d.removeLast();
           StdOut.println("Test should throw java.util.NoSuchElementException: "
                           + "FAIL!");
       } catch (java.util.NoSuchElementException e)
       {
           StdOut.println("Test should throw java.util.NoSuchElementException: "
                          + "Successfull!");
       }

       // Test case: Add stuff at the front, end and fetch the 
       //            data from front and end. 
       d = new Deque<String>();
       // Make deque 1,2,3,4
       d.addFirst("3");
       d.addFirst("2");
       d.addLast("4");
       d.addFirst("1");
       StdOut.println("1 == " + d.removeFirst() + ": size: " + d.size());
       d.addFirst("0");
       // deque: 0,2,3,4
       StdOut.println("4 == " + d.removeLast() + ": size: " + d.size());
       StdOut.println("3 == " + d.removeLast() + ": size: " + d.size());
       StdOut.println("0 == " + d.removeFirst() + ": size: " + d.size());
       StdOut.println("2 == " + d.removeLast() + ": size: " + d.size());
       try { 
           d.removeLast(); 
       } catch (java.util.NoSuchElementException e) 
         { StdOut.println("deque is empty"); }
       
       d.addLast("5");
       StdOut.println("5 == " + d.removeFirst() + ": size: " + d.size());      
    }
}