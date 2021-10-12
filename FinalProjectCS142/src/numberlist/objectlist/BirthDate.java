package numberlist.objectlist;

import java.io.Serializable;
import numberlist.BirthDateException;

/**
 * This class represents a date value using birth month, day, year.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 *
 * @version %I%, %G%
 * @since JDK 8u271
 * 
 * @see java.io.Serializable
 * @see numberlist.BirthDateException
 */
public final class BirthDate implements Copiable, Comparable<BirthDate>, Serializable {

    //fields
    private final int month;
    private final int day;
    private final int year;

    /**
     * Default constructor. Creates a new BirthDate object. Sets date to
     * 1/1/2000.
     */
    public BirthDate() {
        this.month = 1;
        this.day = 1;
        this.year = 2000;
    }

    /**
     * Full constructor. Creates a new BirthDate object.
     *
     * @param month the birth day
     * @param day the birth month
     * @param year the birth year
     *
     * @throws numberlist.BirthDateException
     */
    public BirthDate(int month, int day, int year) throws BirthDateException {
        if (validate(month, day, year)) {
            this.month = month;
            this.day = day;
            this.year = year;
        } else {
            throw new BirthDateException(month, day, year);
        }
    }

    /**
     * Accessor method for the month value.
     *
     * @return the birth month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Accessor method for the day value.
     *
     * @return the birth month
     */
    public int getDay() {
        return day;
    }

    /**
     * Accessor method for the year value.
     *
     * @return the birth month
     */
    public int getYear() {
        return year;
    }

    /**
     * Calculates the age based on the current month and year (March 2021).
     *
     * @return the current age
     */
    public int getAge() {
        int age = 2021 - year;
        if (month <= 3) {
            return age;
        } else {
            return age - 1;
        }
    }

    /**
     * Provides a string representation of the current BirthDate object, in the
     * form of month/day/year.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return  month + "/" + day + "/" + year;
    }

    /**
     * Provides a hashCode value for the BirthDate object.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.month;
        hash = 53 * hash + this.day;
        hash = 53 * hash + this.year;
        return hash;
    }

    /**
     * Indicates whether this object is equal to the object being compared. If
     * the reference does not equal to the same object, then it checks if the
     * fields have the same values.
     *
     * @param obj the object being compared to.
     * @return a Boolean value to indicate whether it is equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BirthDate other = (BirthDate) obj;
        if (this.month != other.month) {
            return false;
        }
        if (this.day != other.day) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    /**
     * Indicates whether the birth date is valid, by checking whether the user
     * is within 13-121 years of age, ensuring the month value is within 1-12,
     * and checks whether the day is within the appropriate range according to
     * the month
     *
     * @param month the month value.
     * @param day the day value.
     * @param year the year value
     * @return a Boolean value to indicate whether birth date is valid.
     */
    public boolean validate(int month, int day, int year) {
        if (year < 1900 || year > 2008) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            if (day < 1 || day > 31) {
                return false;
            }
        } else {
            if (day < 1 || day > 30) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Creates a deep copy of the object, by creating a new BirthDate object
     * with the same fields.
     *
     * @return the new copied object
     */
    @Override
    public BirthDate copy() {
        try {
            return new BirthDate(this.month, this.day, this.year);
        } catch (BirthDateException bde) {
            return null;
        }
    }

    /**
     * Compares this BirthDate object with another BirthDate object. If both
     * objects have the same years, comparison will be done using months. If
     * both objects have the same months, comparison will be done using days.
     *
     * @param obj the BirthDate object to be compared to
     * @return 0 if two BirthDate objects are same, less then 0 if this object 
     * is older, greater then 0 if this object is younger.
     */
    @Override
    public int compareTo(BirthDate obj) {
        final BirthDate o = (BirthDate) obj;
        if (Integer.compare(this.year, o.year) == 0) {
            if (Integer.compare(this.month, o.month) == 0) {
                return Integer.compare(this.day, o.day);
            } else {
                return Integer.compare(this.month, o.month);
            }
        } else {
            return Integer.compare(this.year, o.year);
        }
    }

    
}
