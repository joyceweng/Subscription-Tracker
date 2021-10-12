package numberlist.primitivelist;

import numberlist.IndexException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the JUnit Testing for the IntegerArrayList Class.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 * 
 * @version 4.1
 */
public class IntegerArrayListTest {

    IntegerArrayList list;

    @Before
    public void setUp() {
        list = new IntegerArrayList();
    }

    /**
     * Test of add method, of class IntegerArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testAdd() throws IndexException {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertTrue(list.getCount() == 4);
        assertTrue(list.getValue(0) == 1);
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
     * Test of RemoveAll method, of class IntegerArrayList.
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveAll() throws IndexException {
        list.add(1);
        list.add(1);
        list.add(3);
        list.add(4);
        list.removeAll(1);
        assertTrue(list.getCount() == 2);
    }
    
    @Test
    public void testRemoveAllSame() throws IndexException {
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.removeAll(1);
        assertTrue(list.getCount() == 0);
    }
    
    @Test
    public void testRemoveAllMiddle() throws IndexException {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.removeAll(3);
        assertTrue(list.getCount() == 6);
    }
    
    @Test
    public void testRemoveAllEnd() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i * 10);
        }
        list.removeAll(40);
        assertTrue(list.getCount() == 4);
        for (int i = 0; i < 4; i++) {
            assertTrue(list.getValue(i) == i * 10);
        }
    }
    
    @Test
    public void testRemoveAllNonExist() throws IndexException {
        list.add(1);
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(3);
        list.add(1);
        list.removeAll(9);
        assertTrue(list.getCount() == 6);
    }

    /**
     * Test of FindLastIndex method, of class IntegerArrayList.
     */
    @Test
    public void testFindLastIndex() {
        list.add(89);
        list.add(86);
        list.add(34);
        list.add(34);
        assertEquals(3, list.findLastIndex(34));
        assertEquals(-1, list.findLastIndex(5));
    }
    
    @Test
    public void testfindLastIndexMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i * 10);
        }
        assertTrue(list.findFirstIndex(20) == 2);
    }
   
    @Test
    public void testfindLastIndexSame() throws IndexException {
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        assertTrue(list.findLastIndex(1) == 5);
    }

    @Test
    public void testfindLastIndexNonExist() throws IndexException {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.findLastIndex(4) == -1);
    }
}
