package numberlist.primitivelist;

import numberlist.IndexException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the JUnit Testing for the DoubleArrayList Class.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 * 
 * @version 4.1
 */
public class DoubleArrayListTest {

    private DoubleArrayList list;

    @Before
    public void setUp() {
        list = new DoubleArrayList();
    }

    /**
     * Test of add method, of class DoubleArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testAddFirst() throws IndexException {
        boolean err = false;
        int index = 99;

        try {
            if (index < 0 || index > list.getCount() - 1) {
                throw new IndexException(0, list.getCount(), index);
            }
            list.add(index, 12.5);

        } catch (IndexException ie) {
            err = true;
            assertSame(0, ie.getMin());
            assertSame(0, ie.getMax());
            assertSame(99, ie.getValue());
        }
        assertTrue(err);
        list.add(0, 123.5d);
        assertEquals(1, list.getCount());
        assertEquals(123.5d, list.getValue(0), 0.01);
    }

    @Test
    public void testAddLots() throws IndexException {
        for (int i = 0; i < 1000; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        assertEquals(1000, list.getCount());
        for (int i = 0; i < 1000; i++) {
            assertEquals(i * 10 + 0.5d, list.getValue(i), 0.01);
        }
    }

    @Test
    public void testAddMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        list.add(2, 123.5d);
        assertEquals(6, list.getCount());
        assertEquals(0.5d, list.getValue(0), 0.01);
        assertEquals(10.5d, list.getValue(1), 0.01);
        assertEquals(123.5d, list.getValue(2), 0.01);
        assertEquals(20.5d, list.getValue(3), 0.01);
        assertEquals(30.5d, list.getValue(4), 0.01);
        assertEquals(40.5d, list.getValue(5), 0.01);
    }

    @Test
    public void testAddLotsMiddle() throws IndexException {
        for (int i = 0; i < 100; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        list.add(15, 123.5d);
        assertEquals(101, list.getCount());
        for (int i = 0; i < 15; i++) {
            assertEquals(i * 10 + 0.5d, list.getValue(i), 0.01);
        }
        assertEquals(123.5, list.getValue(15), 0.01);
        for (int i = 16; i < 100; i++) {
            assertEquals((i - 1) * 10 + 0.5d, list.getValue(i), 0.01);
        }
    }

    @Test
    public void testAddOutOfRange() throws IndexException {
        boolean err = false;
        try {
            list.add(100, 12.5);
        } catch (IndexException ie) {
            err = true;
            assertSame(0, ie.getMin());
            assertSame(0, ie.getMax());
            assertSame(100, ie.getValue());
        }
        assertTrue(err);
    }

    /**
     * Test of remove method with index, of class LongArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveFirstIndex() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        double value = list.remove(0);
        assertEquals(0.5d, value, 0.01);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals((i + 1) * 10 + 0.5d, list.getValue(i), 0.01);
        }
    }

    @Test
    public void testremoveLastIndex() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        double value = list.remove(4);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals(i * 10 + 0.5d, list.getValue(i), 0.01);
        }
    }

    /**
     * Test of remove method with value, of class DoubleArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveMiddleValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        for (int i = 5; i < 10; i++) {
            list.add(i, (i - 5) * 10 + 0.5d);
        }
        for (int i = 10; i < 15; i++) {
            list.add(i, (i - 10) * 10 + 0.5d);
        }
        list.remove(20.5d);
        assertEquals(14, list.getCount());
        for (int i = 0; i < 2; i++) {
            assertEquals(i * 10 + 0.5d, list.getValue(i), 0.01);
        }
        for (int i = 2; i < 4; i++) {
            assertEquals((i + 1) * 10 + 0.5d, list.getValue(i), 0.01);
        }
        for (int i = 4; i < 9; i++) {
            assertEquals((i - 4) * 10 + 0.5d, list.getValue(i), 0.01);
        }
        for (int i = 9; i < 14; i++) {
            assertEquals((i - 9) * 10 + 0.5d, list.getValue(i), 0.01);
        }
    }

    @Test
    public void testRemoveNonexistentValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        list.remove(123.5d);
        assertEquals(5, list.getCount());
        for (int i = 0; i < 5; i++) {
            assertEquals(i * 10 + 0.5d, list.getValue(i), 0.01);
        }
    }

    @Test
    public void testRemoveFirstValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, (i + 1) * 10 + 0.5d);
        }
        list.remove(10.5d);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals((i + 2) * 10 + 0.5d, list.getValue(i), 0.01);
        }
    }

    @Test
    public void testRemoveLastValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        list.remove(40.5d);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals(i * 10 + 0.5d, list.getValue(i), 0.01);
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

    /**
     * Test of getValue method, of class DoubleArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetOne() throws IndexException {
        list.add(0, 123);
        assertEquals(123, list.getValue(0), 0.01);
    }

    @Test
    public void testGetFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(0, list.getValue(0), 0.01);
    }

    @Test
    public void testGetLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(40, list.getValue(4), 0.01);
    }

    @Test
    public void testGetMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        assertEquals(20.5d, list.getValue(2), 0.01);
    }

    @Test
    public void testGetValueIndexOutOfRange() throws IndexException {
        boolean err = false;
        try {
            list.add(-1, 12.5);
        } catch (IndexException ie) {
            err = true;
            assertSame(0, ie.getMin());
            assertSame(0, ie.getMax());
            assertSame(-1, ie.getValue());
        }
        assertTrue(err);
    }

    /**
     * Test of findFirstIndex method, of class DoubleArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testFindOne() throws IndexException {
        list.add(0, 123.5d);
        assertEquals(0, list.findFirstIndex(123.5d));

    }

    public void testFindFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        assertEquals(0, list.findFirstIndex(0.5d), 0.01);
    }

    @Test
    public void testFindLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        assertEquals(4, list.findFirstIndex(40.5), 0.01);
    }

    @Test
    public void testFindMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        assertEquals(2, list.findFirstIndex(20.5d));
    }

    @Test
    public void testFindNonexistent() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        assertEquals(-1, list.findFirstIndex(123.5d));
    }

    /**
     * Test of getCount method, of class DoubleArrayList.
     */
    @Test
    public void testGetCountEmpty() {
        assertEquals(0, list.getCount());
    }

    @Test
    public void testGetCountPartFilled() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(5, list.getCount());
    }

    @Test
    public void testGetCountFilled() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, i * 10);
        }
        assertEquals(10, list.getCount());
    }

    /**
     * Test of toString method, of class DoubleArrayList.
     */
    @Test
    public void testToStringEmpty() {
        assertEquals("[ ]", list.toString());
    }

    @Test
    public void testToString() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10 + 0.5d);
        }
        assertEquals("[ 0.5, 10.5, 20.5, 30.5, 40.5 ]", list.toString());
    }
    
    /**
     * Test of set method, of class DoubleArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testSetOne() throws IndexException {
        list.add(0, 1.0);
        list.set(0, 123.0);
        assertTrue(list.getCount() == 1);
        assertTrue(list.getValue(0) == 123.0);
    }

    @Test
    public void testSetFirst2() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10.0);
        }
        double temp = list.set(0, 123.0);
        assertTrue(temp == 0.0);
        assertTrue(list.getCount() == 5);
        assertTrue(list.getValue(0) == 123.0);
    }

    @Test
    public void testSetMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10.0);
        }
        double temp = list.set(2, 123.0);
        assertTrue(temp == 20.0);
        assertTrue(list.getCount() == 5);
        assertTrue(list.getValue(2) == 123.0);
    }

    @Test
    public void testSetLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10.0);
        }
        double temp = list.set(4, 123.0);
        assertTrue(temp == 40.0);
        assertTrue(list.getCount() == 5);
        assertTrue(list.getValue(4) == 123.0);
    }

    @Test
    public void testSetOutOfRange() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10.0);
        }
        boolean err = false;
        try {
            list.set(-1, 233.0);
        } catch (IndexException ie) {
            err = true;
            assertSame(0, ie.getMin());
            assertSame(4, ie.getMax());
            assertSame(-1, ie.getValue());
        }
        assertTrue(err);
        assertTrue(list.getCount() == 5);
    }

    @Test
    public void testSetZeroCount() throws IndexException {
        boolean err = false;
        try {
            list.set(-1, 233.0);
        } catch (IndexException ie) {
            err = true;
            assertSame(-1, ie.getMin());
            assertSame(-1, ie.getMax());
            assertSame(-1, ie.getValue());
        }
        assertTrue(err);
        assertTrue(list.getCount() == 0);
    }
}
