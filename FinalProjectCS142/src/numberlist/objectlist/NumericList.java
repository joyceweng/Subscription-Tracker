package numberlist.objectlist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This abstract class provides an outline for list abstract data type
 * implementations.
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
abstract class NumericList implements Serializable {

    //field 
    int count;

    /**
     * This abstract method inserts the given Copiable value at the given index.
     *
     * @param index the index where the new value should be stored
     * @param obj the value to be stored
     * @throws numberlist.IndexException
     */
    abstract void add(int index, Copiable obj) throws IndexException;

    /**
     * This abstract method will insert the Copiable object at the end of the
     * list abstract data type.
     *
     * @param obj the value to be stored
     * @return the index in which the value is stored
     */
    public int add(Copiable obj) {
        try {
            add(count, obj);
        } catch (IndexException ie) {
            //should never happen
        }
        return count - 1;
    }

    /**
     * Deletes the value at the given index.
     *
     * @param index the index of the element that should be removed
     * @return the value that was removed
     * @throws numberlist.IndexException
     */
    abstract Copiable remove(int index) throws IndexException;

    /**
     * Deletes the first instance of the given value.
     *
     * @param obj the value to remove
     */
    abstract void remove(Copiable obj);

    /**
     * Returns the value at the given index.
     *
     * @param index the index of the element
     * @return the value at that index
     * @throws numberlist.IndexException
     */
    abstract Copiable getValue(int index) throws IndexException;

    /**
     * Returns the index of the first instance of the given value in the array.
     *
     * @param obj the value to find in the array
     * @return the index where the value was found
     */
    abstract int findFirstIndex(Copiable obj);

    /**
     * Replaces the value at the specified index with the specified object
     *
     * @param index the index at which the object is stored
     * @param obj the object used to replace
     * @return the replaced object
     * @throws numberlist.IndexException
     */
    abstract Copiable set(int index, Copiable obj) throws IndexException;

    /**
     * Returns the number of elements that exist in the list.
     *
     * @return the count value
     */
    public int getCount() {
        return count;
    }

}
