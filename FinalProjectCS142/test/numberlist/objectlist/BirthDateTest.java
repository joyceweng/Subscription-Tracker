package numberlist.objectlist;

import numberlist.BirthDateException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the JUnit Testing for the BirthDate Class.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 */
public class BirthDateTest {

    private BirthDate bd1, bd2, bd3, bd4;

    /**
     * Sets up BirthDate objects for testing.
     *
     * @throws BirthDateException
     */
    @Before
    public void setUp() throws BirthDateException {
        bd1 = new BirthDate();
        bd2 = new BirthDate(10, 6, 2003);
        bd3 = new BirthDate(12, 31, 1921);
    }

    /**
     * Test of getMonth method, of class BirthDate.
     */
    @Test
    public void testGetMonth() {
        assertTrue(bd1.getMonth() == 1);
        assertTrue(bd2.getMonth() == 10);
        assertTrue(bd3.getMonth() == 12);
    }

    /**
     * Test of getDay method, of class BirthDate.
     */
    @Test
    public void testGetDay() {
        assertTrue(bd1.getDay() == 1);
        assertTrue(bd2.getDay() == 6);
        assertTrue(bd3.getDay() == 31);
    }

    /**
     * Test of getYear method, of class BirthDate.
     */
    @Test
    public void testGetYear() {
        assertTrue(bd1.getYear() == 2000);
        assertTrue(bd2.getYear() == 2003);
        assertTrue(bd3.getYear() == 1921);
    }

    /**
     * Test of getAge method, of class BirthDate.
     */
    @Test
    public void testGetAge() {
        assertTrue(bd1.getAge() == 21);
        assertTrue(bd2.getAge() == 17);
        assertTrue(bd3.getAge() == 99);
    }

    /**
     * Test of toString method, of class BirthDate.
     */
    @Test
    public void testToString() {
        assertEquals(bd1.toString(), "1/1/2000");
        assertEquals(bd2.toString(), "10/6/2003");
        assertEquals(bd3.toString(), "12/31/1921");
    }

    /**
     * Test of equals method, of class BirthDate.
     *
     * @throws numberlist.BirthDateException
     */
    @Test
    public void testEquals() throws BirthDateException {
        assertTrue(bd1.equals(new BirthDate()));
        assertTrue(bd2.equals(new BirthDate(10, 6, 2003)));
        assertTrue(bd3.equals(new BirthDate(12, 31, 1921)));
        assertFalse(bd1.equals(new BirthDate(2, 1, 2000)));
        assertFalse(bd2.equals(new BirthDate(10, 5, 2003)));
        assertFalse(bd3.equals(new BirthDate(12, 31, 1920)));
    }

    /**
     * Test of validate method, of class BirthDate.
     */
    @Test
    public void testValidateInvalidMonth() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(15, 1, 2000);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == 15);
            assertTrue(bde.getDayValue() == 1);
            assertTrue(bde.getYearValue() == 2000);
        }
        assertTrue(err);
    }

    @Test
    public void testValidateInvalidNegMonth() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(-1, 1, 2000);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == -1);
            assertTrue(bde.getDayValue() == 1);
            assertTrue(bde.getYearValue() == 2000);
        }
        assertTrue(err);
    }

    @Test
    public void testValidateInvalidZeroMonth() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(0, 1, 2000);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == 0);
            assertTrue(bde.getDayValue() == 1);
            assertTrue(bde.getYearValue() == 2000);
        }
        assertTrue(err);
    }

    @Test
    public void testValidateInvalidDay1() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(1, 32, 2000);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == 1);
            assertTrue(bde.getDayValue() == 32);
            assertTrue(bde.getYearValue() == 2000);
        }
        assertTrue(err);
    }

    @Test
    public void testValidateInvalidDay2() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(2, 31, 2000);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == 2);
            assertTrue(bde.getDayValue() == 31);
            assertTrue(bde.getYearValue() == 2000);
        }
        assertTrue(err);
    }

    @Test
    public void testValidateInvalidNegDay() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(1, -1, 2000);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == 1);
            assertTrue(bde.getDayValue() == -1);
            assertTrue(bde.getYearValue() == 2000);
        }
        assertTrue(err);
    }

    @Test
    public void testValidateInvalidZeroDay() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(1, 0, 2000);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == 1);
            assertTrue(bde.getDayValue() == 0);
            assertTrue(bde.getYearValue() == 2000);
        }
        assertTrue(err);
    }

    @Test
    public void testValidateInvalidYear() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(1, 1, 2009);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == 1);
            assertTrue(bde.getDayValue() == 1);
            assertTrue(bde.getYearValue() == 2009);
        }
        assertTrue(err);
    }

    @Test
    public void testValidateTooSmallYear() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(1, 1, 1899);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == 1);
            assertTrue(bde.getDayValue() == 1);
            assertTrue(bde.getYearValue() == 1899);
        }
        assertTrue(err);
    }

    @Test
    public void testValidateNegYear() {
        boolean err = false;
        try {
            BirthDate bd = new BirthDate(1, 1, -1000);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == 1);
            assertTrue(bde.getDayValue() == 1);
            assertTrue(bde.getYearValue() == -1000);
        }
        assertTrue(err);
    }

    /**
     * Test of copy method, of class BirthDate.
     */
    @Test
    public void testCopy() {
        BirthDate bd4 = bd2.copy();
        assertEquals(bd2.getMonth(), bd4.getMonth());
        assertEquals(bd2.getDay(), bd4.getDay());
        assertEquals(bd2.getYear(), bd4.getYear());
    }

    @Test
    public void testFailCopy() {
        boolean err = false;
        try {
            BirthDate bd5 = new BirthDate(-1, -1, -1);
            assertEquals(bd5.copy(), null);
        } catch (BirthDateException bde) {
            err = true;
            assertTrue(bde.getMonthValue() == -1);
            assertTrue(bde.getDayValue() == -1);
            assertTrue(bde.getYearValue() == -1);
        }
        assertTrue(err);
    }
    
    /**
     * Test of compareTo method, of class BirthDate.
     */
    @Test
    public void testCompareTo() {
        BirthDate bd6 = bd2.copy();  
        assertTrue(bd2.compareTo(bd6) == 0);
        assertTrue(bd2.compareTo(bd1) > 0);
        assertTrue(bd1.compareTo(bd2) < 0);
    }
}
