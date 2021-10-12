package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class provides a growable array for primitive integer values.
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
public class IntegerArrayList extends LongArrayList implements Serializable {

    /**
     * Inserts the given int value at the last index. Existing elements are
     * moved up as needed to make room for the new value.
     *
     * @param value the value to be stored
     * @return the index the value was inserted at
     */
    public int add(long value) {
        try {
            add(getCount(), value);
        } catch (IndexException ie) {
            //this shoud never happen
        }

        return getCount() - 1;
    }

    /**
     * Deletes all instances of the value from the list
     *
     * @param value the value removed
     */
    public void removeAll(long value) {
        for (int i = getCount() - 1; i >= 0; i-- ) {
            try {
                if (getValue(i) == value) {
                    remove(i);
                }
            } catch (IndexException ie) {
                //should not happen
            }
        }
    }

    /**
     * Finds the index of the last element in the list that contains the value.
     * Returns -1 if the value does not exist.
     *
     * @param value the value to be removed
     * @return the index of the value in the list
     */
    public int findLastIndex(long value) {
        try {
            for (int i = getCount() - 1; i >= 0; i--) {
                if (value == getValue(i)) {
                    return i;
                }
            }
        } catch (IndexException ie) {
            //should never happen
        }
        return -1;
    }
}
