package numberlist.primitivelist;

import numberlist.IndexException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the JUnit Testing for the RealArrayList Class.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 * 
 * @version 4.1
 */
public class RealArrayListTest {

    private RealArrayList list;

    @Before
    public void setUp() {
        list = new RealArrayList();
    }

    /**
     * Test of add method, of class RealArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testAddFirst() throws IndexException {
        list.add(0, 123.5d);
        assertEquals(1, list.getCount());
        assertEquals(123.5d, list.getValue(0), 0.01);
    }

    /**
     * Test of RemoveAll method, of class RealArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveAllAndMiddle() throws IndexException {
        list.add(34.5);
        list.add(89.5);
        list.add(89.5);
        list.add(89.5);
        list.add(20.75);
        list.removeAll(89.5);
        assertEquals(2, list.getCount());
        assertEquals(20.75, list.getValue(1), 0.01);
    }

    @Test
    public void testRemoveFirst() throws IndexException {
        list.add(34.5);
        list.add(89.5);
        list.add(89.5);
        list.add(89.5);
        list.add(20.75);
        list.removeAll(34.5);
        assertEquals(4, list.getCount());
        assertEquals(89.5, list.getValue(0), 0.01);
    }

    @Test
    public void testRemoveLast() {
        list.add(34.5);
        list.add(89.5);
        list.add(89.5);
        list.add(20.75);
        list.add(20.75);
        list.removeAll(20.75);
        assertEquals(3, list.getCount());
    }

    @Test
    public void testRemoveNonexistentValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        list.remove(123L);
        assertEquals(5, list.getCount());
        for (int i = 0; i < 5; i++) {
            assertEquals(i * 10, list.getValue(i), 0.01);
        }
    }

    /**
     * Test of FindLastIndex method, of class RealArrayList.
     */
    @Test
    public void testFindLastIndex() {
        list.add(89.5);
        list.add(86.5);
        list.add(34.5);
        list.add(34.5);
        assertEquals(3, list.findLastIndex(34.5));
    }

    @Test
    public void testfindLastIndexMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i * 10.0);
        }
        assertTrue(list.findFirstIndex(20.0) == 2);
    }

    @Test
    public void testfindLastIndexSame() throws IndexException {
        list.add(1.0);
        list.add(1.0);
        list.add(1.0);
        list.add(1.0);
        list.add(1.0);
        list.add(1.0);
        assertTrue(list.findLastIndex(1.0) == 5);
    }

    @Test
    public void testfindLastIndexNonExist() throws IndexException {
        list.add(1.0);
        list.add(2.0);
        list.add(3.0);
        assertTrue(list.findLastIndex(4.0) == -1);
    }
}
