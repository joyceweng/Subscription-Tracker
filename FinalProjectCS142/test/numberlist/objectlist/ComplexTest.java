package numberlist.objectlist;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the JUnit Testing for the Complex Class
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 * 
 * @version 4.1
 */
public class ComplexTest {

    private Complex c1, c2, c3, c4;

    @Before
    public void setUp() {
        c1 = new Complex(1.2, 2.3);
        c2 = new Complex(4.5, 5.6);
        c3 = new Complex(1.2, 2.3);
        c4 = new Complex(-5.6, -6.7);
    }

    /**
     * Test of getReal method, of class Complex.
     */
    @Test
    public void testGetRealPos() {
        assertEquals(c1.getReal(), 1.2, 0.1);
    }

    @Test
    public void testGetRealNeg() {
        assertEquals(c4.getReal(), -5.6, 0.1);
    }

    /**
     * Test of getImaginary method, of class Complex.
     */
    @Test
    public void testGetImaginaryPos() {
        assertEquals(c1.getImaginary(), 2.3, 0.1);
    }

    @Test
    public void testGetImaginaryNeg() {
        assertEquals(c4.getImaginary(), -6.7, 0.1);
    }

    /**
     * Test of add method, of class Complex.
     */
    @Test
    public void testAddPos() {
        Complex c = c1.add(c2);
        assertTrue(c != c1);
        assertTrue(c != c2);
        assertEquals(c1.getReal(), 1.2, 0.1);
        assertEquals(c1.getImaginary(), 2.3, 0.1);
        assertEquals(c2.getReal(), 4.5, 0.1);
        assertEquals(c2.getImaginary(), 5.6, 0.1);
        assertEquals(c.getReal(), 5.7, 0.1);
        assertEquals(c.getImaginary(), 7.9, 0.1);
    }

    @Test
    public void testAddNeg() {
        Complex c = c1.add(c4);
        assertTrue(c != c1);
        assertTrue(c != c4);
        assertEquals(c1.getReal(), 1.2, 0.1);
        assertEquals(c1.getImaginary(), 2.3, 0.1);
        assertEquals(c4.getReal(), -5.6, 0.1);
        assertEquals(c4.getImaginary(), -6.7, 0.1);
        assertEquals(c.getReal(), -4.4, 0.1);
        assertEquals(c.getImaginary(), -4.4, 0.1);
    }

    /**
     * Test of subtract method, of class Complex.
     */
    @Test
    public void testSubtractPos() {
        Complex c = c2.subtract(c1);
        assertTrue(c != c1);
        assertTrue(c != c2);
        assertEquals(c1.getReal(), 1.2, 0.1);
        assertEquals(c1.getImaginary(), 2.3, 0.1);
        assertEquals(c2.getReal(), 4.5, 0.1);
        assertEquals(c2.getImaginary(), 5.6, 0.1);
        assertEquals(c.getReal(), 3.3, 0.1);
        assertEquals(c.getImaginary(), 3.3, 0.1);
    }

    @Test
    public void testSubtractNeg() {
        Complex c = c1.subtract(c4);
        assertTrue(c != c1);
        assertTrue(c != c4);
        assertEquals(c1.getReal(), 1.2, 0.1);
        assertEquals(c1.getImaginary(), 2.3, 0.1);
        assertEquals(c4.getReal(), -5.6, 0.1);
        assertEquals(c4.getImaginary(), -6.7, 0.1);
        assertEquals(c.getReal(), 6.7, 0.1);
        assertEquals(c.getImaginary(), 9.0, 0.1);
    }

    /**
     * Test of toString method, of class Complex.
     */
    @Test
    public void testToString() {
        assertEquals(c1.toString(), "1.2 + 2.3i");
        assertEquals(c4.toString(), "-5.6 - 6.7i");
        assertEquals((new Complex(0, 1.2)).toString(), "1.2i");
        assertEquals((new Complex(1.2, 0)).toString(), "1.2");
        assertEquals((new Complex(0, -1.2)).toString(), "-1.2i");
        assertEquals((new Complex(-1.2, 0)).toString(), "-1.2");
        assertEquals(c1.toString(), c3.toString());
    }

    /**
     * Test of equals method, of class Complex.
     */
    @Test
    public void testEquals() {
        assertTrue(new Complex(1.2, 2.3).equals(c1));
        assertFalse(new Complex(1.2, 2.3).equals(c2));
        assertTrue(new Complex(1.2, 2.3).equals(c3));
        assertFalse(new Complex(1.2, 2.3).equals(c4));
    
    }

    /**
     * Test of copy method, of class Complex.
     */
    @Test
    public void testCopy() {
        c1 = new Complex(1.2, 2.3);
        c2 = c1.copy();
        assertTrue(c1 != c2);
        assertTrue(c1.equals(c2));
    }
    
     /**
     * Test of copy compareTo, of class Complex.
     */
    @Test
    public void testCompareTo() {
        assertEquals(c1.compareTo(c3), 0);
        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c1.compareTo(c4) > 0);
        assertTrue(c2.compareTo(c4) > 0);
    }
}
