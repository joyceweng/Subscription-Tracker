package numberlist.objectlist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * This class represents a single complex number of the form x + yi.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 * 
 * @version %I%, %G%
 * @since JDK 8u271
 * 
 * @see java.io.Serializable
 * @see java.math.BigDecimal
 * @see java.math.MathContext
 */
public final class Complex implements Copiable, Comparable<Complex>, Serializable {

    //fields
    private double real;
    private double imaginary;

    /**
     * Default constructor. Creates a new Complex object. Sets both real and
     * imaginary to zero.
     */
    public Complex() {
        real = 0;
        imaginary = 0;
    }

    /**
     * Full constructor. Creates a new Complex object.
     *
     * @param real the value of the real portion of the complex number
     * @param imaginary the value of the imaginary portion of the complex number
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Provides access to the real portion of the complex number
     *
     * @return the value of the real portion of the complex number
     */
    public double getReal() {
        return real;
    }

    /**
     * Provides access to the imaginary portion of the complex number
     *
     * @return the value of the imaginary portion of the complex number
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Adds the current and the given complex numbers together, and stores the
     * sum in a new Complex object. The current and given complex numbers are
     * not altered in the process.
     *
     * @param other the other complex number to add to this one
     * @return the new Complex object that holds the result of the addition
     */
    public Complex add(Complex other) {
        double sumReal = real + other.real;
        double sumImaginary = imaginary + other.imaginary;
        return new Complex(sumReal, sumImaginary); //replace this return statement
    }

    /**
     * Subtracts the other complex number from this one, and stores the result
     * in a new Complex object. The current and given complex numbers are not
     * altered in the process.
     *
     * @param other the other complex number to subtract from this one
     * @return the new Complex object that holds the result of the subtraction
     */
    public Complex subtract(Complex other) {
        double SubReal = real - other.real;
        double SubImaginary = imaginary - other.imaginary;
        return new Complex(SubReal, SubImaginary); //replace this return statement
    }

    /**
     * Provides a string representation of the current complex number, in the
     * form x + yi.
     *
     * @return the string representation of the current complex number
     */
    @Override
    public String toString() {
        BigDecimal bd = new BigDecimal(real);
        bd = bd.round(new MathContext(2));
        real = bd.doubleValue();
        bd = new BigDecimal(imaginary);
        bd = bd.round(new MathContext(2));
        imaginary = bd.doubleValue();
        if (imaginary == 0) {
            return real + "";
        }
        if (real == 0) {
            return imaginary + "i";
        }
        if (imaginary < 0) {
            return real + " - " + (-imaginary) + "i";
        }
        return real + " + " + imaginary + "i";
    }

    /**
     * Provides a hashCode value for the Complex object.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.real) ^ (Double.doubleToLongBits(this.real) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.imaginary) ^ (Double.doubleToLongBits(this.imaginary) >>> 32));
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
        final Complex other = (Complex) obj;
        if (Double.doubleToLongBits(this.real) != Double.doubleToLongBits(other.real)) {
            return false;
        }
        if (Double.doubleToLongBits(this.imaginary) != Double.doubleToLongBits(other.imaginary)) {
            return false;
        }
        return true;
    }

    /**
     * Creates a deep copy of the Complex object with the same fields.
     *
     * @return the new copied object
     */
    @Override
    public Complex copy() {
        return new Complex(this.real, this.imaginary);
    }

    /**
     * Compares this Complex object with another Complex object.
     *
     * @param other the other Complex object
     * @return 0 if two Complex objects are same, less then 0 if smaller,
     * greater then 0 if larger.
     */
    @Override
    public int compareTo(Complex other) {
        if (Double.compare(this.real, other.real) == 0) {
            return Double.compare(this.imaginary, other.imaginary);
        } else {
            return Double.compare(this.real, other.real);
        }
    }
}
