package numberlist;

/**
 * This class is a custom exception called IndexException that extends
 * Exception.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 *
 * @version %I%, %G%
 * @since JDK 8u271
 */
public class IndexException extends Exception {

    //fields
    private int min;
    private int max;
    private int value;

    /**
     * This is the constructor that requires the lowest and highest index
     * allowed and the index actually sent as arguments
     *
     * @param min that is the lowest index
     * @param max that is the highest index
     * @param value that is the index value
     */
    public IndexException(int min, int max, int value) {
        super("Index is out of range");
        this.min = min;
        this.max = max;
        this.value = value;
    }

    /**
     * This is the getter for the lowest index
     *
     * @return min that is the lowest index
     */
    public int getMin() {
        return min;
    }

    /**
     * This is the getter for the highest index
     *
     * @return max that is the highest index
     */
    public int getMax() {
        return max;
    }

    /**
     * This is the getter for the index value
     *
     * @return value that is the index value
     */
    public int getValue() {
        return value;
    }

}
