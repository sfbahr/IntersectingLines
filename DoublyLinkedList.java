/**
 * A generic DoublyLinkedList implementation.
 *
 * @param <T> The type of elements contained in the DoublyLinkedList.
 *
 * @author karl88
 * @author sfbahr
 * @version Sep 17, 2014
 */
public class DoublyLinkedList<T>
{
    //~ Instance/static variables .............................................

    // A reference to the sentinel node at the beginning of the list.
    private Node<T> head;

    // A reference to the sentinel node at the end of the list.
    private Node<T> tail;

    // A reference to the current node in the list.
    private Node<T> current;

    // The number of elements in the list.
    private int size;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Construct the list.
     */
    public DoublyLinkedList()
    {
        head = new Node<T>(null);
        tail = new Node<T>(null);
        current = null;
        head.join(tail);

        size = 0;
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Gets the data from the current node in the list.
     *
     * @return the current data
     */
    public T getCurrent()
    {
        if (current == null)
        {
            return null;
        }
        return current.data();
    }

    // ----------------------------------------------------------
    /**
     * Move current to the front of the list.
     */
    public void moveToFront()
    {
        if (head.next() != tail)
        {
            current = head.next();
        }
    }

    // ----------------------------------------------------------
    /**
     * Move current to the back of the list
     */
    public void moveToBack()
    {
        if (tail.previous() != head)
        {
            current = tail.previous();
        }
    }

    /**
     * Moves through the list from front to back looking for a match. If there
     * is none this is equivalent to moveToBack().
     *
     * Requires that class T supports the .equals(T) method.
     *
     * @param value the data value being checked
     * @return true if successfully moved to a match, false if no match was
     *      found
     */
    public boolean moveToValue(T value)
    {
        if (current == null)
        {
            return false;
        }
        moveToFront();
        while (!current.data().equals(value))
        {
            if (!next())
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Moves current to the next node in the list.
     *
     * @return true if current moved to the next value, false if it was
     *         already at the back or null.
     */
    public boolean next()
    {
        if (current == null)
        {
            return false;
        }
        if (current.next() != tail)
        {
            current = current.next();
            return true;
        }
        return false;
    }

    /**
     * Moves current to the previous node in the list.
     *
     * @return true if current moved to the previous value, false if it was
     *         already at the front or null.
     */
    public boolean previous()
    {
        if (current == null)
        {
            return false;
        }
        if (current.previous() != head)
        {
            current = current.previous();
            return true;
        }
        return false;
    }

    /**
     * If the list is currently empty, add the value to the list and make the
     * added value the current value, otherwise simply add the new value after
     * the current value.
     *
     * @param value the data value being inserted
     * @return the correct node and value
     */
    public DoublyLinkedList<T> insertAfterCurrent(T value)
    {
        if (current == null)
        {
            insert(head, new Node<T>(value));
            current = head.next();
            return this;
        }
        return insert(current, new Node<T>(value));
    }

    /**
     * If the list is currently empty, add the value to the list and make the
     * added value the current value, otherwise simply add the new value before
     * the current value.
     *
     * @param value the data value being inserted
     * @return the correct node and value
     */
    public DoublyLinkedList<T> insertBeforeCurrent(T value)
    {
        if (current == null)
        {
            insert(head, new Node<T>(value));
            current = head.next();
            return this;
        }
        return insert(current.previous(), new Node<T>(value));
    }

    /**
     * Handles the implementation of insertion for the two public insertion
     * methods.
     *
     * @param first the node preceding the new node
     * @param inserted the node being inserted
     */
    private DoublyLinkedList<T> insert(Node<T> first, Node<T> inserted)
    {
        Node<T> oldSecond = first.next();
        first.split();
        first.join(inserted);
        inserted.join(oldSecond);
        size++;
        return this;
    }

    // ----------------------------------------------------------
    /**
     * Remove the current node and move current to the next node.
     */
    public void removeAndNext()
    {
        if (current == tail.previous() && size > 1)
        {
            removeAndPrevious();
            return;
        }
        Node<T> removed = current;
        next();
        remove(removed);
    }

    // ----------------------------------------------------------
    /**
     * Remove the current node and move current to the previous node.
     */
    public void removeAndPrevious()
    {
        if (current == head.next() && size > 1)
        {
            removeAndNext();
            return;
        }
        Node<T> removed = current;
        previous();
        remove(removed);
    }

    /**
     * Handles the implementation of removal for the two public remove
     * methods.
     *
     * @param removed the node to be removed
     */
    private void remove(Node<T> removed)
    {
        if (removed == null)
        {
            return;
        }
        Node<T> first = removed.previous();
        Node<T> second = removed.next();
        removed.split();
        first.split();
        first.join(second);
        if (size == 1)
        {
            current = null;
        }
        size--;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int getSize()
    {
        return size;
    }

    // ----------------------------------------------------------
    /**
     * Empties the list.
     */
    public void clear()
    {
        if (size > 0)
        {
            tail.previous().split();
            head.split();
            head.join(tail);
        }
        size = 0;
    }


    /**
     * Returns a string representation of this DoublyLinkedList in the following
     * format:
     * T1.toString() -> T2.toString() -> T3.toString()
     *
     * An empty list returns an empty string.
     *
     * @return the string representation of this DoublyLinkedList
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        Node<T> tempCurrent = head.next();
        for (int i = 0; i < size; i++)
        {
            builder.append(tempCurrent.data().toString());
            if (i < size - 1)
            {
                builder.append(" -> ");
            }
            tempCurrent = tempCurrent.next();
        }
        return builder.toString();
    }
}
