package numberlist.primitivelist;

import numberlist.IndexException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the JUnit Testing for the LongArrayList Class.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 * 
 * @version 4.1
 */
public class LongArrayListTest {

    LongArrayList list;

    @Before
    public void setUp() {
        list = new LongArrayList();
    }

    /**
     * Test of add method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testAddFirst() throws IndexException {
        list.add(0, 123L);
        assertEquals(1, list.getCount());
        assertEquals(123, list.getValue(0));
    }

    @Test
    public void testAddLots() throws IndexException {
        for (int i = 0; i < 1000; i++) {
            list.add(i, i * 10);
        }
        assertEquals(1000, list.getCount());
        for (int i = 0; i < 1000; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
    }

    @Test
    public void testAddMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        list.add(2, 123);
        assertEquals(6, list.getCount());
        assertEquals(0, list.getValue(0));
        assertEquals(10, list.getValue(1));
        assertEquals(123, list.getValue(2));
        assertEquals(20, list.getValue(3));
        assertEquals(30, list.getValue(4));
        assertEquals(40, list.getValue(5));
    }

    @Test
    public void testAddLotsMiddle() throws IndexException {
        for (int i = 0; i < 100; i++) {
            list.add(i, i * 10);
        }
        list.add(15, 123);
        assertEquals(101, list.getCount());
        for (int i = 0; i < 15; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
        assertEquals(123, list.getValue(15));
        for (int i = 16; i < 100; i++) {
            assertEquals((i - 1) * 10, list.getValue(i));
        }
    }

    @Test
    public void testAddOutOfRange() throws IndexException {
        boolean err = false;
        try {
            list.add(100, 12);
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
            list.add(i, i * 10);
        }
        long value = list.remove(0);
        assertEquals(0, value);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals((i + 1) * 10, list.getValue(i));
        }
    }

    @Test
    public void testremoveLastIndex() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        long value = list.remove(4);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
    }

    /**
     * Test of remove method with value, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveMiddleValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        for (int i = 5; i < 10; i++) {
            list.add(i, (i - 5) * 10);
        }
        for (int i = 10; i < 15; i++) {
            list.add(i, (i - 10) * 10);
        }
        list.remove(20L);
        assertEquals(14, list.getCount());
        for (int i = 0; i < 2; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
        for (int i = 2; i < 4; i++) {
            assertEquals((i + 1) * 10, list.getValue(i));
        }
        for (int i = 4; i < 9; i++) {
            assertEquals((i - 4) * 10, list.getValue(i));
        }
        for (int i = 9; i < 14; i++) {
            assertEquals((i - 9) * 10, list.getValue(i));
        }
    }

    @Test
    public void testRemoveNonexistentValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        list.remove(123L);
        assertEquals(5, list.getCount());
        for (int i = 0; i < 5; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
    }

    @Test
    public void testRemoveFirstValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, (i + 1) * 10);
        }
        list.remove(10L);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals((i + 2) * 10, list.getValue(i));
        }
    }

    @Test
    public void testRemoveLastValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        list.remove(40L);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
    }

    @Test
    public void testRemoveIndexOutOfRange() throws IndexException {
        boolean err = false;
        try {
            list.remove(100);
        } catch (IndexException ie) {
            err = true;
            assertSame(-1, ie.getMin());
            assertSame(-1, ie.getMax());
            assertSame(100, ie.getValue());
        }
        assertTrue(err);
    }

    /**
     * Test of getValue method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetOne() throws IndexException {
        list.add(0, 123);
        assertEquals(123, list.getValue(0));
    }

    @Test
    public void testGetFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(0, list.getValue(0));
    }

    @Test
    public void testGetLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(40, list.getValue(4));
    }

    @Test
    public void testGetMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(20, list.getValue(2));
    }

    @Test
    public void testGetValueIndexOutOfRange() throws IndexException {
        boolean err = false;
        try {
            list.add(-1, 12);
        } catch (IndexException ie) {
            err = true;
            assertSame(0, ie.getMin());
            assertSame(0, ie.getMax());
            assertSame(-1, ie.getValue());
        }
        assertTrue(err);
    }

    /**
     * Test of findFirstIndex method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testFindOne() throws IndexException {
        list.add(0, 123);
        assertEquals(0, list.findFirstIndex(123));

    }

    public void testFindFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(0, list.findFirstIndex(0));
    }

    @Test
    public void testFindLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(4, list.findFirstIndex(40));
    }

    @Test
    public void testFindMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(2, list.findFirstIndex(20));
    }

    @Test
    public void testFindNonexistent() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(-1, list.findFirstIndex(123));
    }

    /**
     * Test of getCount method, of class LongArrayList.
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
     * Test of toString method, of class LongArrayList.
     */
    @Test
    public void testToStringEmpty() {
        assertEquals("[ ]", list.toString());
    }

    @Test
    public void testToString() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals("[ 0, 10, 20, 30, 40 ]", list.toString());
    }
    
    /**
     * Test of set method, of class LongArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testSetOne() throws IndexException {
        list.add(0, 1);
        list.set(0, 123);
        assertTrue(list.getCount() == 1);
        assertTrue(list.getValue(0) == 123);
    }

    @Test
    public void testSetFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        long temp = list.set(0, 123);
        assertTrue(temp == 0);
        assertTrue(list.getCount() == 5);
        assertTrue(list.getValue(0) == 123);
    }

    @Test
    public void testSetMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        long temp = list.set(2, 123);
        assertTrue(temp == 20);
        assertTrue(list.getCount() == 5);
        assertTrue(list.getValue(2) == 123);
    }

    @Test
    public void testSetLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        long temp = list.set(4, 123);
        assertTrue(temp == 40);
        assertTrue(list.getCount() == 5);
        assertTrue(list.getValue(4) == 123);
    }

    @Test
    public void testSetOutOfRange2() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        boolean err = false;
        try {
            list.set(-1, 233);
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
            list.set(-1, 233);
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
