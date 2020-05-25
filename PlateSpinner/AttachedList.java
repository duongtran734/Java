import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * AttachedList is also known as LinkedList.
 * Has all the list operations such as add,remove,get,set,clear,indexOf,size,isEmpty,contains.
 *
 * @param <T> AttachedList accept T data types.
 * @author Steve Tran.
 */
public class AttachedList<T> implements List<T> {
    //for more information on these methods
    //read the documentation of the list interface
    //here: https://docs.oracle.com/javase/8/docs/api/java/util/List.html
    //keep in mind that we are doing a _linked_ list
    //but the documentation is for general lists (like array lists)

    //NOTE: the documentation above is not optional, it tells you things
    //like what exceptions to throw

    /**
     * Node class.
     *
     * @param <T> can hold any Any Type
     */
    private static class Node<T> {
        //you may NOT change these instance variables and/or
        //add any additional instance variables here
        //(so you may not doubly link your list)
        /**
         * Generic type for value.
         */
        T value;
        /**
         * Next node.
         */
        Node<T> next;

        /**
         * Node has value and next.
         *
         * @param value T data type
         */
        public Node(T value) {
            this.value = value;
            next = null;
        }

        //you may add more methods here... and they may be public!
        //note: a constructor _is_ a method (just a special type of method)
        //note: you don't have to add anything if you don't want
        //      this will work as-is
    }

    /**
     * Global variable head.
     */
    private Node<T> head = null;
    /**
     * Global variable tail.
     */
    private Node<T> tail = null;
    /**
     * Global variable size.
     */
    private int size;
    //You _MUST_ use head defined above, we will be "breaking into"
    //your class for testing and we'll be using this "head" variable
    //as part of the tests. If you rename or change it, you will
    //not pass the unit tests.

    //Note: if you're interested on what "breaking in" means, it means
    //we'll be using reflection to access your private instance variables.
    //Interested? See: https://docs.oracle.com/javase/tutorial/reflect/index.html


    //you may add more private methods and instance variables here if you want
    //you may add additional private helper functions as well
    //no new protected or public variables or methods

    /**
     * Constructor.
     */
    public AttachedList() {
        size = 0;
        //initialize anything you want here...
    }

    /**
     * Report number of elements in the list.
     *
     * @return current size
     */
    @Override
    public int size() {
        return size;
        //O(1)
    }

    /**
     * Check to see if list is empty.
     *
     * @return True if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
        //O(1)
    }

    /**
     * Search for the index of the value being searched for.
     *
     * @param o value can be Any type
     * @return the index of the value, -1 if didnt find the index of object
     */
    @Override
    public int indexOf(Object o) {
        Node<T> current = head;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (current.value == null) {
                    return i;
                }
                current = current.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(current.value);
                if (o.equals(current.value)) {
                    return i;
                }
                current = current.next;
            }
        }
        //O(n)
        //yes, nulls are allowed to be searched for
        return -1;
    }

    /**
     * Check to see if the element is in the list.
     *
     * @param o Any Type of element
     * @return true if this list contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        Node<T> current = head;
        while (current != null) {
            if (o == null) {
                if (current.value == null) {
                    return true;
                }
            } else {
                if (o.equals(current.value)) {
                    return true;
                }
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Add element at beginning of the list.
     *
     * @param e Any Type element
     */
    private void addFirst(T e) {
        Node<T> temp = new Node<>(e);
        if (isEmpty()) {
            head = tail = temp;
        } else {
            temp.next = head;
            head = temp;
        }
        size++;
    }

    /**
     * Add element at the end using tail algorithm.
     *
     * @param e Any Type of value
     * @return true you can always add an element in linked list
     */
    @Override
    public boolean add(T e) {
        //this should append to the end of the list
        //O(1) <-- not a typo... think about it!
        //yes, nulls are allowed to be added

        Node<T> current = new Node<>(e);
        if (isEmpty()) {
            head = tail = current;
            size++;

        } else {
            tail.next = current;
            tail = current;


            size++;
        }
        return true;
    }


    /**
     * Add an element at a specific index.
     *
     * @param index   location where to add the new element
     * @param element Any Type of element
     */
    @Override
    public void add(int index, T element) {
        Node<T> temp = new Node<>(element);

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        } else if (index == size()) {
            add(element);
        } else if (index == 0) {
            addFirst(element);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            temp.next = current.next;
            current.next = temp;
            size++;
            //O(n)
            //yes, nulls are allowed to be added
        }


    }

    /**
     * Remove first element in list.
     *
     * @return data value removed
     */
    private T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("No element to delete");
        }
        T dataRemoved = head.value;
        if (head == tail) {
            clear();
        } else {
            head = head.next;
            size--;
        }

        return dataRemoved;
    }

    /**
     * Remove lsat element from the list.
     *
     * @return data value removed
     */
    private T removeLast() {
        Node<T> current = head, previous = null;
        T dataRemoved = tail.value;
        //check for empty list
        if (isEmpty()) {
            throw new RuntimeException("No element to delete");
        }
        //check for single element the list
        if (head == tail) {
            return removeFirst();
        }
        //remove last element in the lits
        while (current.next != null) {
            previous = current;

            current = current.next;
        }

        previous.next = null;
        tail = previous;
        size--;

        return dataRemoved;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index position to be remove
     * @return
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<T> current = head, previous = null;
        T dataRemoved = null;
        if (index == 0) {
            dataRemoved = removeFirst();
        } else if (index == size() - 1) {
            dataRemoved = removeLast();
        } else {

            for (int i = 0; i < index; i++) {

                previous = current;
                current = current.next;
            }
            dataRemoved = previous.next.value;
            previous.next = current.next;
            size--;

        }
        //O(n)
        return dataRemoved;
    }

    /**
     * Removes the first occurrence of the specified element from this list.
     *
     * @param o Any type element
     * @return true if this list contained the specified element else return false;
     */
    @Override
    public boolean remove(Object o) {
        Node<T> current = head, previous = null;
        if (isEmpty()) {
            return false;
        } else {
            //looping through the list
            while (current != null) {
                // when object is equal to current value
                if (o.equals(current.value)) {
                    //check if at begining
                    if (current == head) {
                        removeFirst();
                    }
                    //check if at end
                    else if (current == tail) {
                        removeLast();
                    }
                    //value is somewhere in between first and last
                    else {
                        previous.next = current.next;
                    }
                    return true;
                }
                previous = current;
                current = current.next;
            }
            //O(n)
            //yes, nulls are allowed to removed
            return false;
        }
    }

    /**
     * Remove everything from the list.
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
        //O(1) <-- not a typo... think about it!
    }


    /**
     * Get the element of the node at the given index.
     *
     * @param index Any type of element
     * @return the element at the given index
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        //O(n)
        return current.value;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   the position to replace with
     * @param element Any Type element
     * @return previous element
     */
    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        T oldValue = null;
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (index == count) {
                oldValue = current.value;
                current.value = element;
                break;
            }
            current = current.next;
            count++;
        }
        //O(n)
        return oldValue;
    }

    /**
     * Removes a "slice" from fromIndex to toIndex (inclusive).
     *
     * @param fromIndex position to be remove from.
     * @param toIndex   position to be remove to
     * @return the sliced list
     */
    public AttachedList<T> slice(int fromIndex, int toIndex) {
        AttachedList<T> sliced = new AttachedList<>();
        if (fromIndex < 0 || fromIndex > toIndex || toIndex < fromIndex || toIndex >= size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (fromIndex == toIndex) {
            for (int i = 0; i <= toIndex; i++) {
                if (i == toIndex) {
                    sliced.add(get(i));
                    remove(i);
                }
            }
        } else {
            for (int i = fromIndex; i <= toIndex; i++) {
                sliced.add(get(fromIndex));
                remove(fromIndex);
            }
        }
        //removes a "slice" from fromIndex to toIndex (inclusive)
        //return the slice as a new AttachedList
        //throws IndexOutOfBoundsException if fromIndex _or_ toIndex is invalid
        //O(n)
        return sliced;
    }

    /**
     * Reverse the element in side the list.
     *
     * @return reversed list
     */
    public AttachedList<T> reverseCopy() {
        AttachedList<T> reverseList = new AttachedList<>();
        Node<T> current = head;
        while (current != null) {
            reverseList.addFirst(current.value);
            current = current.next;
        }
        return reverseList;
        //returns a copy of the list with the elements reversed
        //does not alter the original list
        //O(n)
    }

    /**
     * Convert a 2D List into 1D List.
     *
     * @param packedList 2D list passed in a parameter
     * @param <E>        E Data Type
     * @return the 1D list
     */
    public static <E> AttachedList<E> flatten(AttachedList<AttachedList<E>> packedList) {
        AttachedList<E> flat = new AttachedList<>();
        //given a 2D list of lists (packedList), "flatten" the list into 1D
        //Example 1: [[1,2,3],[4,5],[6]] becomes [1,2,3,4,5,6]
        //Example 2: [[null],[1,3],[5],[6]] becomes [null,1,3,5,6]
        //IMPORTANT: the above examples are _lists NOT arrays_
        for (int i = 0; i < packedList.size(); i++) {
            for (int j = 0; j < packedList.get(i).size(); j++) {
                flat.add(packedList.get(i).get(j));
            }
        }

        return flat;
    }

    /**
     * Turn a 1D list into 2D lists.
     *
     * @param flatList 1D list
     * @param <E>      E Data type
     * @return 2D lists
     */
    public static <E> AttachedList<AttachedList<E>> pack(AttachedList<E> flatList) {
        //given a 1D (flatList), "pack" sequential items together
        //to form a 2D list of values

        AttachedList<AttachedList<E>> packList = new AttachedList<>();
        //to be modified
        AttachedList<E> smallList = new AttachedList<>();

        Node<E> current = flatList.head;
        Node<E> temp = current.next;
        while (current != null) {

            //check if head == head.next
            if (current.value == temp.value) {
                smallList.add(current.value);
            }
            //add old current small list to big list, and make a new small list after
            //check to see if the value of new head is equal to new head.next
            else {
                packList.add(smallList);
                smallList = new AttachedList<>();
                temp = current;
                if (temp.value == current.value) {
                    smallList.add(temp.value);
                }
            }
            current = current.next;
            if (current == null) {
                packList.add(smallList);
            }
        }
        return packList;
        //Example 1: [1,1,2,3,3] becomes [[1,1],[2],[3,3]]
        //Example 1: [1,1,2,1,1,2,2,2,2] becomes [[1,1],[2],[1,1],[2,2,2,2]]
        //Example 3: [1,2,3,4,5] becomes [[1],[2],[3],[4],[5]]
        //IMPORTANT: the above examples are _lists NOT arrays_
        //promise: we will never test this with nulls in the flatList
        //though there's no harm in coding it to work with nulls
    }

    /**
     * Iterator class.Iterates over the nodes.
     *
     * @return current node
     */
    @Override
    public Iterator<T> iterator() {
        //this method is outlined for you... just fill out next() and hasNext()
        //NO ADDITIONAL ANYTHING (METHODS, VARIABLES, ETC.) INSIDE THE ANONYMOUS CLASS
        //You may NOT override remove() or any other iterator methods
        return new Iterator<T>() {
            //starts at the head
            private Node<T> current = head;

            /**
             * Check to see if there is a next node.
             * @return true of current not null else false
             */

            @Override
            public boolean hasNext() {
                //    throw new UnsupportedOperationException("Not supported yet. Replace this line with your implementation.");
                //O(1)

                return current != null;
            }

            @Override
            public T next() {
                // throw new UnsupportedOperationException("Not supported yet. Replace this line with your implementation.");
                //O(1)
                if (!hasNext()) {
                    throw new NoSuchElementException("");
                }
                T res = current.value;
                current = current.next;
                return res;

            }
        };
    }

    // --------------------------------------------------------
    // testing code goes here... edit this as much as you want!
    // --------------------------------------------------------

    /**
     * This method print all the element out as a String.
     *
     * @return a string output that users can read off
     */
    public String toString() {
        //you may edit this to make string representations of your
        //list for testing
        StringBuffer sb = new StringBuffer();
        for (Object x : this)
            sb.append(x + " ");
        return sb.toString();
    }

    /**
     * Run the tests cases for the AttacchedList class.
     * @param args argument array String
     */
    public static void main(String[] args) {

        AttachedList<Integer> nums = new AttachedList<>();
        for (int i = 0; i < 3; i++) nums.add(i, i * 2);
        if (nums.size() == 3 && nums.get(2) == 4) {
            System.out.println("Yay 2");
        }

        AttachedList<String> msg = new AttachedList<>();
        //insert some strings
        //insert some strings
        msg.add(0, "world");
        msg.add(0, "hello");
        msg.add(1, "new");
        msg.add("!");
        System.out.println(msg);
        if (msg.get(0).equals("hello") && msg.set(1, "beautiful").equals("new")
                && msg.size() == 4) {
            System.out.println("Yay 3 " + msg);
        }

        if (msg.remove(1).equals("beautiful") && msg.get(1).equals("world")
        ) {
            System.out.println("Yay 5 " + msg);
        }
        if (msg.set(0, "bye").equals("hello")) {
            System.out.println("Yay 6 " + msg);
        }
        msg.clear();
        System.out.println("-----------------------------------------------------");
        msg.add("FIRST");
        msg.add("1");
        msg.add("2");
        msg.add("3");
        msg.add("LAST");
        System.out.println(msg);

        System.out.println("Sliced " + msg.slice(3, 3));
        System.out.println(msg);

        System.out.println("HEAD: " + msg.head.value);
        System.out.println("TAIL: " + msg.tail.value);

        AttachedList<AttachedList<String>> flatList = new AttachedList<>();
        for (int i = 0; i < 10; i++) {
            AttachedList<String> smallFlatList = new AttachedList<>();
            flatList.add(smallFlatList);
        }

        AttachedList<Integer> numbers = new AttachedList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                numbers.add(i);
            }
        }
        System.out.println(pack(numbers));
    }

    // --------------------------------------------------------
    // DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
    // --------------------------------------------------------

    @Override
    public Object[] toArray() {
        Object[] items = new Object[this.size()];
        int i = 0;
        for (T val : this) {
            items[i++] = val;
        }
        return items;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return (T[]) this.toArray();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported.");
    }
}