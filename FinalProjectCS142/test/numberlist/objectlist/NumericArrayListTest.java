package numberlist.objectlist;

import numberlist.IndexException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the JUnit Testing for the NumericArrayList Class.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 * 
 * @version 4.1
 */
public class NumericArrayListTest {

    NumericArrayList list;

    @Before
    public void setUp() {
        list = new NumericArrayList();
    }

    /**
     * Test of add method, of class NumericArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testAddFirst() throws IndexException {
        list.add(0, new Complex(1, 10));
        assertEquals(new Complex(1, 10), list.getValue(0));
    }

    @Test
    public void testAddLots() throws IndexException {
        for (int i = 0; i < 1000; i++) {
            list.add(i, new Complex(1, 10));
        }

        for (int i = 0; i < 1000; i++) {
            assertEquals(new Complex(1, 10), list.getValue(i));
        }
    }

    @Test
    public void testAddMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex(i, 10));
        }
        list.add(2, new Complex(1, 10));
        assertEquals(new Complex(0, 10), list.getValue(0));
        assertEquals(new Complex(1, 10), list.getValue(1));
        assertEquals(new Complex(1, 10), list.getValue(2));
        assertEquals(new Complex(2, 10), list.getValue(3));
        assertEquals(new Complex(3, 10), list.getValue(4));
        assertEquals(new Complex(4, 10), list.getValue(5));
    }

    @Test
    public void testAddLotsMiddle() throws IndexException {
        for (int i = 0; i < 100; i++) {
            list.add(i, new Complex((i * 10), i));
        }
        list.add(15, new Complex(11, 22));
        for (int i = 0; i < 15; i++) {
            assertEquals(new Complex((i * 10), i), list.getValue(i));
        }
        assertEquals(new Complex(11, 22), list.getValue(15));
        for (int i = 16; i < 100; i++) {
            assertEquals(new Complex((i - 1) * 10, i - 1), list.getValue(i));
        }
    }

    @Test
    public void testAddIndexOutOfRange() throws IndexException {
        boolean err = false;
        try {
            list.add(100, new Complex(1, 10));
        } catch (IndexException ie) {
            err = true;
            assertSame(0, ie.getMin());
            assertSame(0, ie.getMax());
            assertSame(100, ie.getValue());
        }
        assertTrue(err);
    }

    /**
     * Test of remove method with index, of class NumericArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveFirstIndex() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        Copiable value = list.remove(0);
        assertEquals(new Complex(0, 1), value);
        for (int i = 0; i < 4; i++) {
            assertEquals(new Complex(((i + 1) * 10), 1), list.getValue(i));
        }
    }

    @Test
    public void testremoveLastIndex() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex(i, 10));
        }
        Object value = list.remove(4);
        for (int i = 0; i < 4; i++) {
            assertEquals(new Complex(i, 10), list.getValue(i));
        }
    }

    @Test
    public void testRemoveMiddleValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        for (int i = 5; i < 10; i++) {
            list.add(i, new Complex(((i - 5) * 10), 1));
        }
        for (int i = 10; i < 15; i++) {
            list.add(i, new Complex(((i - 10) * 10), 1));
        }
        list.remove(new Complex(20, 1));
        for (int i = 0; i < 2; i++) {
            assertEquals(new Complex((i * 10), 1), list.getValue(i));
        }
        for (int i = 2; i < 4; i++) {
            assertEquals(new Complex(((i + 1) * 10), 1), list.getValue(i));
        }
        for (int i = 4; i < 9; i++) {
            assertEquals(new Complex(((i - 4) * 10), 1), list.getValue(i));
        }
        for (int i = 9; i < 14; i++) {
            assertEquals(new Complex(((i - 9) * 10), 1), list.getValue(i));
        }
    }

    @Test
    public void testRemoveNonexistentValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        list.remove(new Complex(999, 999));
        for (int i = 0; i < 5; i++) {
            assertEquals(new Complex((i * 10), 1), list.getValue(i));
        }
    }

    @Test
    public void testRemoveFirstValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex(((i + 1) * 10), 1));
        }
        list.remove(new Complex(10, 1));
        for (int i = 0; i < 4; i++) {
            assertEquals(new Complex(((i + 2) * 10), 1), list.getValue(i));
        }
    }

    @Test
    public void testRemoveLastValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        list.remove(new Complex(40, 1));
        for (int i = 0; i < 4; i++) {
            assertEquals(new Complex((i * 10), 1), list.getValue(i));
        }
    }

    @Test
    public void testRemoveIndexOutOfRange() throws IndexException {
        boolean err = false;
        try {
            list.remove(-1);
        } catch (IndexException ie) {
            err = true;
            assertSame(-1, ie.getMin());
            assertSame(-1, ie.getMax());
            assertSame(-1, ie.getValue());
        }
        assertTrue(err);
    }

    @Test
    public void testGetOne() throws IndexException {
        list.add(0, new Complex(1, 10));
        assertEquals(new Complex(1, 10), list.getValue(0));
    }

    @Test
    public void testGetFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        assertEquals(new Complex(0, 1), list.getValue(0));
    }

    @Test
    public void testGetLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        assertEquals(new Complex(40, 1), list.getValue(4));
    }

    @Test
    public void testGetMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        assertEquals(new Complex(20, 1), list.getValue(2));
    }

    /**
     * Test of findFirstIndex method, of class NumericArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testFindOne() throws IndexException {
        list.add(0, new Complex(1, 10));
        assertEquals(0, list.findFirstIndex(new Complex(1, 10)));
    }

    public void testFindFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        assertEquals(0, list.findFirstIndex(new Complex(0, 1)));
    }

    @Test
    public void testFindLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        assertEquals(4, list.findFirstIndex(new Complex(40, 1)));
    }

    @Test
    public void testFindMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        assertEquals(2, list.findFirstIndex(new Complex(20, 1)));
    }

    @Test
    public void testFindNonexistent() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        assertEquals(-1, list.findFirstIndex(new Complex(999, 999)));
    }

    /**
     * Test of getCount method, of class NumericArrayList.
     */
    @Test
    public void testGetCountEmpty() {
        assertEquals(0, list.getCount());
    }

    @Test
    public void testGetValueIndexOutOfRange() throws IndexException {
        boolean err = false;
        try {
            list.add(-1, new Complex(999, 999));
        } catch (IndexException ie) {
            err = true;
            assertSame(0, ie.getMin());
            assertSame(0, ie.getMax());
            assertSame(-1, ie.getValue());
        }
        assertTrue(err);
    }

    /**
     * Test of toString method, of class NumericArrayList.
     */
    @Test
    public void testToStringEmpty() {
        assertEquals("[ ]", list.toString());
    }

    @Test
    public void testToString() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex((i * 10), 1));
        }
        assertEquals("[ 1.0i, 10.0 + 1.0i, 20.0 + 1.0i, 30.0 + 1.0i, 40.0 + 1.0i ]", list.toString());
    }

    /**
     * Test of copy method, of class NumericArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testCopy() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex(i * 10.0, i * 5.0));
        }

        NumericArrayList copyList = list.copy();
        System.out.println(copyList.toString());
        for (int i = 0; i < 5; i++) {
            assertTrue(list.getValue(i).equals(copyList.getValue(i)));
        }
    }

    /**
     * Test of set method, of class NumericArrayList.
     */
    @Test
    public void testSetFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Money(i, (byte) 50));
        }
        assertEquals(new Money(0, (byte) 50), list.set(
                0, new Money(99, (byte) 99)));
        assertEquals(list.getValue(0), new Money(99, (byte) 99));
        for (int i = 1; i < 5; i++) {
            assertEquals(new Money(i, (byte) 50), list.getValue(i));
        }
    }

    @Test
    public void testSetLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Money(i, (byte) 50));
        }
        assertEquals(new Money(4, (byte) 50), list.set(
                4, new Money(99, (byte) 99)));
        assertEquals(list.getValue(4), new Money(99, (byte) 99));
        for (int i = 0; i < 4; i++) {
            assertEquals(new Money(i, (byte) 50), list.getValue(i));
        }
    }

    @Test
    public void testSetEmpty() {
        boolean caught = false;
        try {
            list.set(9, new Money(9, (byte) 9));
        } catch (IndexException ie) {
            caught = true;
            assertSame(-1, ie.getMin());
            assertSame(-1, ie.getMax());
            assertSame(9, ie.getValue());
        }
        assertTrue(caught);
        assertEquals(0, list.getCount());
    }

    @Test
    public void testSetBadIndex() {
        boolean caught = false;
        for (int i = 0; i < 5; i++) {
            list.add(new Money(i, (byte) 50));
        }
        assertFalse(caught);
        try {
            list.set(9, new Money(9, (byte) 9));
        } catch (IndexException ie) {
            caught = true;
            assertSame(0, ie.getMin());
            assertSame(list.getCount() - 1, ie.getMax());
            assertSame(9, ie.getValue());
        }
        assertTrue(caught);
    }

    @Test
    public void testSetAtCount() {
        boolean caught = false;
        for (int i = 0; i < 5; i++) {
            list.add(new Money(i, (byte) 50));
        }
        assertFalse(caught);
        try {
            list.set(5, new Money(5, (byte) 5));
        } catch (IndexException ie) {
            caught = true;
            assertSame(0, ie.getMin());
            assertSame(list.getCount() - 1, ie.getMax());
            assertSame(5, ie.getValue());
        }
        assertTrue(caught);
    }

    @Test
    public void testSetNegetive() {
        boolean caught = false;
        for (int i = 0; i < 5; i++) {
            list.add(new Money(i, (byte) 50));
        }
        assertFalse(caught);
        try {
            list.set(-9, new Money(9, (byte) 9));
        } catch (IndexException ie) {
            caught = true;
            assertSame(0, ie.getMin());
            assertSame(list.getCount() - 1, ie.getMax());
            assertSame(-9, ie.getValue());
        }
        assertTrue(caught);
    }
}
