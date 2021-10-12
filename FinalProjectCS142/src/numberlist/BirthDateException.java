package numberlist;

/**
 * This class serves as an exception for methods where the BirthDate class is
 * used, and the given birth date is invalid.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 *
 * @version %I%, %G%
 * @since JDK 8u271
 */
public class BirthDateException extends Exception {

    private final int monthValue;
    private final int dayValue;
    private final int yearValue;

    /**
     * Constructor. Initializes values if birth date is invalid.
     *
     * @param monthValue the month entered by the user
     * @param dayValue the day entered by the user
     * @param yearValue the year entered by the user
     */
    public BirthDateException(int monthValue, int dayValue, int yearValue) {
        super("Birth date is invalid.");
        this.monthValue = monthValue;
        this.dayValue = dayValue;
        this.yearValue = yearValue;
    }

    /**
     * A getter for the month value.
     *
     * @return the value of monthValue
     */
    public int getMonthValue() {
        return monthValue;
    }

    /**
     * A getter for the day value.
     *
     * @return the value of dayValue
     */
    public int getDayValue() {
        return dayValue;
    }

    /**
     * A getter for the year value.
     *
     * @return the value of yearValue
     */
    public int getYearValue() {
        return yearValue;
    }

}
