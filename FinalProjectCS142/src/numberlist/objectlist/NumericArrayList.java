package numberlist.objectlist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class provides a growable array for numeric Objects.
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
public class NumericArrayList extends NumericList implements Copiable,
        Serializable {

    //fields
    private Copiable[] list;

    /**
     * Constructor. Initializes the underlying array to 10 elements.
     */
    public NumericArrayList() {
        list = new Copiable[10];
        count = 0;
    }

    /**
     * Inserts the given Object value at the given index.The index is assumed to
     * be a value between 0 and count. Existing elements are moved up as needed
     * to make room for the new value.
     *
     * @param index the index where the new value should be stored
     * @param value the value to be stored
     * @throws numberlist.IndexException
     */
    @Override
    public void add(int index, Copiable value) throws IndexException {
        if (index < 0 || index > count) {
            throw new IndexException(0, count, index);
        }
        if (list.length == count) {
            Copiable[] temp = new Copiable[2 * list.length];
            for (int i = 0; i < count; i++) {
                temp[i] = list[i];
            }
            list = temp;
        }
        for (int i = count - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = value;
        count++;
    }

    /**
     * Deletes the value at the given index.The index is assumed to be a value
     * between 0 and count - 1. Existing elements are moved down as needed to
     * keep all values contiguous, without any empty spaces in the array.
     *
     * @param index the index of the element that should be removed
     * @return the value that was removed
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
        Copiable removed = list[index];
        count--;
        for (int i = index; i < list.length - 1; i++) {
            list[i] = list[i + 1];
        }
        return removed;
    }

    /**
     * Deletes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param value the value to remove
     */
    @Override
    public void remove(Copiable value) { //Object value
        int index = findFirstIndex(value);
        if (index != -1) {
            try {
                remove(index);
            } catch (IndexException ie) {
                //should never happen
            }
        }
    }

    /**
     * Returns the value at the given index without removing it.The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element
     * @return the value at that index
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
        return list[index];
    }

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param value the value to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    @Override
    public int findFirstIndex(Copiable value) {
        for (int i = 0; i < count; i++) {
            if (list[i].equals(value)) {
                return i;
            }
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
        for (int i = 0; i < count; i++) {
            output += list[i] + ", ";
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
     * Creates a deep copy of the NumericArrayList object.
     *
     * @return the new copied object
     */
    @Override
    public NumericArrayList copy() {
        NumericArrayList copyArrayList = new NumericArrayList();
        for (int i = 0; i < count; i++) {

            try {
                copyArrayList.add(i, this.getValue(i).copy());
            } catch (IndexException ie) {
                //should never happen
            }
        }
        return copyArrayList;
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
        list[index] = obj;
        return temp;
    }

}