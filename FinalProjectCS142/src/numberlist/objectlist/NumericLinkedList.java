package numberlist.objectlist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class provides a NumericLinkedList that extends NumericList.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 *
 * @version %I%, %G%
 * @since JDK 8u271
 * 
 * @see java.io.Serializable
 * @see numberlist.IndexException
 */
public class NumericLinkedList extends NumericList implements Copiable,
        Serializable {

    private Node firstNode;

    /**
     * Constructor. Calls super method and initializes firstNode to null.
     */
    public NumericLinkedList() {
        super();
        this.firstNode = null;
    }

    /**
     * Inserts the given Object value at the given index. The index is assumed
     * to be a value between 0 and count. Existing elements are moved up as
     * needed to make room for the new value.
     *
     * @param index the index where the new value should be stored
     * @param obj the object to be stored
     * @throws numberlist.IndexException
     */
    @Override
    public void add(int index, Copiable obj) throws IndexException {
        if (index < 0 || index > count) {
            throw new IndexException(0, count, index);
        }
        Node newNode = new Node(obj);
        if (index == 0) {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
        }
        count++;
    }

    /**
     * Deletes the value at the given index.Existing elements are moved down as
     * needed to keep all values contiguous, without any empty spaces in the
     * array.
     *
     * @param index the index of the removed element
     * @return the obj that was removed
     * @throws numberlist.IndexException
     */
    @Override
    public Copiable remove(int index) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, count - 1, index);
        }
        if (index == 0) {
            Node theRemovedNode = firstNode;
            firstNode = firstNode.getNextNode();
            count--;
            return theRemovedNode.getValue();
        } else {
            Node theRemovedNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                theRemovedNode = theRemovedNode.getNextNode();
            }
            Node theNextNode = theRemovedNode.getNextNode();
            theRemovedNode.setNextNode(theNextNode.getNextNode());
            count--;
            return theNextNode.getValue();
        }
    }

    /**
     * Deletes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param obj the value to remove
     */
    @Override
    public void remove(Copiable obj) {
        try {
            remove(findFirstIndex(obj));
        } catch (IndexException ie) {
            //should not happen
        }

    }

    /**
     * Returns the value at the given index
     *
     * @param index the index of the element
     * @return the object of index
     * @throws numberlist.IndexException
     */
    @Override
    public Copiable getValue(int index) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, count - 1, index);
        }
        Node currentNode = firstNode;
        while (index > 0) {
            currentNode = currentNode.getNextNode();
            index--;
        }

        return (Copiable) currentNode.getValue();
    }

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param obj the Object to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    @Override
    public int findFirstIndex(Copiable obj) {
        Node currentNode = firstNode;
        for (int i = 0; currentNode != null; i++) {
            if (currentNode.getValue().equals(obj)) {
                return i;
            }
            currentNode = currentNode.getNextNode();
        }
        return -1;
    }

    /**
     * Provides a string representation of the growable array, displaying all
     * values currently in the array using the format [ value1, value2, ... ].
     *
     * @return the string representation of the array
     */
    @Override
    public String toString() {
        String output = "[ ";
        Node currentNode = firstNode;
        for (int i = 0; i < count; i++) {
            output += currentNode.getValue() + ", ";
            currentNode = currentNode.getNextNode();
        }
        if (count > 0) {
            output = output.substring(0, output.length() - 2);
        } else {
            output = output.substring(0, output.length() - 1);
        }
        output += " ]";
        return output;
    }

    /**
     * Creates a deep copy of the NumericList object.
     *
     * @return the new copied object
     */
    @Override
    public NumericLinkedList copy() {
        NumericLinkedList copyList = new NumericLinkedList();
        Node currentNode = firstNode;
        while (currentNode != null) {
            copyList.add(currentNode.getValue().copy());
            currentNode = currentNode.getNextNode();
        }

        return copyList;
    }

    /**
     * Replaces the value at the specified index with the specified object
     *
     * @param index the index at which the object is stored
     * @param obj the object used to replace
     * @return the replaced object
     * @throws numberlist.IndexException
     */
    @Override
    public Copiable set(int index, Copiable obj) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, count - 1, index);
        }
        Copiable temp = getValue(index);
        Node currentNode = firstNode;
        for (int i = index; i > 0; i--) {
            currentNode = currentNode.getNextNode();
        }
        currentNode.setValue(obj);
        return temp;
    }
}
