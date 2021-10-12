package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class provides a growable array for primitive long values.
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
class LongArrayList implements Serializable {

    //fields
    private long[] list;
    private int count;

    /**
     * Constructor. Initializes the underlying array to 10 elements.
     */
    LongArrayList() {
        list = new long[10];
        count = 0;
    }

    /**
     * Inserts the given long value at the given index. The index is assumed to
     * be a value between 0 and count. Existing elements are moved up as needed
     * to make room for the new value.
     *
     * @param index the index where the new value should be stored
     * @param value the value to be stored
     * @throws numberlist.IndexException
     */
    public void add(int index, long value) throws IndexException {
        if (index < 0 || index > count) {
            throw new IndexException(0, count, index);
        }
        if (count == list.length) {
            long[] temp = new long[list.length * 2];
            for (int i = 0; i < count; i++) {
                temp[i] = list[i];
            }
            list = temp;
        }
        for (int i = count; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        count++;
    }

    /**
     * Deletes the value at the given index. The index is assumed to be a value
     * between 0 and count - 1. Existing elements are moved down as needed to
     * keep all values contiguous, without any empty spaces in the array.
     *
     * @param index the index of the element that should be removed
     * @return the value that was removed
     * @throws numberlist.IndexException
     */
    public long remove(int index) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, count - 1, index);
        }
        long removed = list[index];
        for (int i = index; i < list.length - 1; i++) {
            list[i] = list[i + 1];
        }
        count--;
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
    public void remove(long value) {
        int index = findFirstIndex(value);
        if (index != -1) {
            try {
                remove(index);
            } catch (IndexException ie) {
                //this should never happen
            }

        }
    }

    /**
     * Returns the value at the given index without removing it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element
     * @return the value at that index
     * @throws IndexException
     */
    public long getValue(int index) throws IndexException {
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
    public int findFirstIndex(long value) {
        for (int i = 0; i < count; i++) {
            if (value == list[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Provides access to the number of values currently in the array.
     *
     * @return the number of values in the array
     */
    public int getCount() {
        return count;
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
     * This is a method that replaces the value at the given index with the
     * given value. It also returns the value that was replaced
     * 
     * @param index that is the index of the replaced value
     * @param value that is the given value to be replaced at the given index
     * @return temp that is the value replaced
     * @throws numberlist.IndexException
     */
    public long set(int index, long value) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, count - 1, index);
        }
        long temp = getValue(index);
        list[index] = value;
        return temp;
    }

}
