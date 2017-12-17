package textgen;

import java.util.AbstractList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
    LLNode<E> head;
    LLNode<E> tail;
    int size;

    /**
     * Create a new empty LinkedList
     */
    public MyLinkedList() {
        head = new LLNode<>(null);
        tail = new LLNode<>(null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Appends an element to the end of the list
     *
     * @param element The element to add
     */
    public boolean add(E element) {
        if (element == null)
            throw new NullPointerException("element is null");
        LLNode<E> node = new LLNode<>(element);
        node.prev = tail.prev;
        node.next = tail;
        node.prev.next = node;
        tail.prev = node;
        size++;

        return true;
    }

    /**
     * Get the element at position index
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E get(int index) {
        if (index >= 0 && index < size && size > 0) {
            LLNode<E> node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.data;
        } else
            throw new IndexOutOfBoundsException("index is out of bounds");

    }

    /**
     * Add an element to the list at the specified index
     *
     * @param index   where the element should be added
     * @param element The element to add
     */
    public void add(int index, E element) {
        if (element == null)
            throw new NullPointerException("element is null");
        if (size > 0) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException("index is out of bounds");
            LLNode<E> node = new LLNode<>(element);
            LLNode<E> currentNode = head.next;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            node.prev = currentNode.prev;
            node.next = currentNode;
            node.prev.next = node;
            currentNode.prev = node;
            size++;
        } else {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("index is out of bounds");
            add(element);
        }

    }


    /**
     * Return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Remove a node at the specified index and return its data element.
     *
     * @param index The index of the element to remove
     * @return The data element removed
     * @throws IndexOutOfBoundsException If index is outside the bounds of the list
     */
    public E remove(int index) {
        if (index >= 0 && index < size && size > 0) {
            LLNode<E> node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            LLNode<E> prevNode = node.prev;
            LLNode<E> nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
            return node.data;
        } else
            throw new IndexOutOfBoundsException("index is out of bounds");
    }

    /**
     * Set an index position in the list to a new element
     *
     * @param index   The index of the element to change
     * @param element The new element
     * @return The element that was replaced
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */

    public E set(int index, E element) {
        if (element == null)
            throw new NullPointerException("element is null");
        if (index >= 0 && index < size && size > 0) {
            E changed;
            if (index < 0 && index >= size)
                throw new IndexOutOfBoundsException("index is out of bounds");
            LLNode<E> node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            changed = node.data;
            node.data = element;
            return changed;
        } else
            throw new IndexOutOfBoundsException("index is out of bounds");
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E data;

    // TODO: Add any other methods you think are useful here
    // E.g. you might want to add another constructor

    public LLNode(E e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }

}
