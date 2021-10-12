package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class provides a growable array for primitive real values.
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
public class RealArrayList extends DoubleArrayList implements Serializable {

    /**
     * Inserts at the end of the list and return the index it was inserted at
     *
     * @param value the value to stored
     * @return index of the value was inserted at
     */
    public int add(double value) {
        try {
            add(getCount(), value);
        } catch (IndexException ie) {
            //this should never happen
        }
        return getCount() - 1;
    }

    /**
     * Deletes all instances of the value from the list
     *
     * @param value the value to be removed
     */
    public void removeAll(double value) {
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
     * Returns the index of the last element in the list that contains the
     * value, or -1 if the value does not exist
     *
     * @param value the value to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    public int findLastIndex(double value) {
        for (int i = getCount() - 1; i > -1; i--) {
            try {
                if (value == getValue(i)) {
                    return i;
                }
            } catch (IndexException ie) {
                //this should never happen
            }
        }
        return -1;
    }
}
