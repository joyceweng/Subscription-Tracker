package numberlist.objectlist;

import java.io.Serializable;

/**
 * This class represents a single monetary value in U.S. dollars and cents.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 * 
 * @version %I%, %G%
 * @since JDK 8u271
 * 
 * @see java.io.Serializable
 */
public class Money implements Copiable, Comparable<Money>, Serializable {

    //fields
    private long dollars;
    private byte cents;

    /**
     * Default constructor. Creates a new Money object. Sets both dollars and
     * cents to zero.
     */
    public Money() {
        dollars = 0;
        cents = 0;
    }

    /**
     * Full constructor. Creates a new Money object. Dollars and cents are
     * normalized. If one is negative and the other is positive, the resulting
     * object will be the natural addition of the two values. (Example: 2
     * dollars and -5 cents is converted to 1 dollar and 95 cents.) If the
     * absolute value of cents is greater than 99, the resulting object will
     * carry the extra cents into the dollars. (Example: 1 dollar and 105 cents
     * is converted to 2 dollars and 5 cents.) A negative value is represented
     * with both the dollars and cents negative.
     *
     * @param dollars the number of dollars in the amount
     * @param cents the number of cents in the amount
     */
    public Money(long dollars, byte cents) {
        long value = dollars * 100;
        value += cents;
        this.dollars = value / 100;
        this.cents = (byte) (value % 100);
    }

    /**
     * Provides access to the dollars portion of the monetary value.
     *
     * @return the dollars portion of this Money object
     */
    public long getDollars() {
        return dollars;
    }

    /**
     * Provides access to the cents portion of the monetary value.
     *
     * @return the cents portion of this Money object
     */
    public byte getCents() {
        return cents;
    }

    /**
     * Adds the current and the given Money objects together, and stores the sum
     * in a new Money object. The current and given Money objects are not
     * altered in the process. The resulting Money object's dollars and cents
     * are normalized as defined in the full constructor comments.
     *
     * @param other the other Money object to add to this one
     * @return the Money object containing the results of this addition
     */
    public Money add(Money other) {
        long value = dollars * 100;
        value += cents;
        long oValue = other.dollars * 100;
        oValue += other.cents;
        long result = value + oValue;
        return new Money(result / 100, (byte) (result % 100));
    }

    /**
     * Subtracts the given Money object from this one, and stores the result in
     * a new Money object. The current and given Money objects are not altered
     * in the process. The resulting Money object's dollars and cents are
     * normalized as defined in the full constructor comments.
     *
     * @param other the other Money object to subtract from this one
     * @return the Money object containing the results of this subtraction
     */
    public Money subtract(Money other) {
        long value = dollars * 100;
        value += cents;
        long oValue = other.dollars * 100;
        oValue += other.cents;
        long result = value - oValue;
        return new Money(result / 100, (byte) (result % 100));
    }

    /**
     * Provides a string representation of the current Money object, in the form
     * $mm.mm. Negative values have a minus sign before the dollar sign. Zero
     * dollar amounts are represented with one zero between the dollar sign and
     * the decimal point. Cents that are multiples of 10 still show two decimal
     * places. (Examples: $12.34, -$6.50, $0.99, -$0.99, $0.00)
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        String output;
        if (dollars >= 0) {
            output = "$";
        } else {
            dollars *= -1;
            cents *= -1;
            output = "-$";
        }
        return output + dollars + "." + String.format("%02d", cents);

    }

    /**
     * Provides a hashCode value for the Complex object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.dollars ^ (this.dollars >>> 32));
        hash = 43 * hash + this.cents;
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
        final Money other = (Money) obj;
        if (this.dollars != other.dollars) {
            return false;
        }
        if (this.cents != other.cents) {
            return false;
        }
        return true;
    }
    
    /**
     * Creates a deep copy of the Money object with the same fields.
     *
     * @return the new copied object
     */
    @Override
    public Money copy() {
        Money m = new Money(getDollars(), getCents());
        return m;
    }

    /**
     * Compares this Money object with another Complex object.
     *
     * @param other the other Money object
     * @return 0 if two Money objects are same, less then 0 if smaller, 
     * greater then 0 if larger.
     */
    @Override
    public int compareTo(Money other) {
        if ((this.dollars - other.dollars) != 0) {
            return Double.compare(this.dollars, other.dollars);
        } else {
            return Double.compare(this.cents, other.cents);
        }
    }
}
