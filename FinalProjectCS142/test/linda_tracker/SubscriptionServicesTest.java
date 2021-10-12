package linda_tracker;

import numberlist.BirthDateException;
import numberlist.IndexException;
import numberlist.objectlist.BirthDate;
import numberlist.objectlist.Money;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is the JUnit Testing for the SubscriptionServices Class. WARNING:
 * Running this test will remove every customer that exists in current file.
 * Save the customers.ser file before continuing.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 */
public class SubscriptionServicesTest {

    SubscriptionServices list;

    public void setUp() throws Exception {
        list = new SubscriptionServices();
        deleteList();
        File ser = new File("customers.ser");
        if (ser.exists()) {
            ser.delete();
        }
    }

    /**
     * A method to help delete the list in every test
     */
    public void deleteList() throws Exception {
        for (int i = 0; i < list.getCount(); i++) {
            list.deleteCustomer(i);
        }
    }

    /**
     * Test of addCustomer method, of class SubscriptionServices.
     */
    @Test
    public void testAddCustomer() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, true, 10);
        assertEquals(list.getName(0), "John");
        assertEquals(list.getBirthDate(0), new BirthDate(1, 1, 2000));
        assertEquals(list.getAge(0), 21);
        assertTrue(list.isPremium(0));
        assertEquals(list.getMonths(0), 10);
        assertEquals(list.getSubTotal(0), new Money((long) 120, (byte) 0));
        list.addCustomer("Jane", 2, 28, 2008, false, 100);
        assertEquals(list.getName(1), "Jane");
        assertEquals(list.getBirthDate(1), new BirthDate(2, 28, 2008));
        assertEquals(list.getAge(1), 13);
        assertFalse(list.isPremium(1));
        assertEquals(list.getMonths(1), 100);
        assertEquals(list.getSubTotal(1), new Money((long) 900, (byte) 0));
        deleteList();
    }

    /**
     * Test of updateCustomer method, of class SubscriptionServices.
     */
    @Test
    public void testUpdateCustomer() throws Exception {
        list.addCustomer("John", 1, 1, 2000, true, 10);
        list.updateCustomer(0, "Jane", 2, 28, 2008, false, 100);
        assertEquals(list.getName(0), "Jane");
        assertEquals(list.getBirthDate(0), new BirthDate(2, 28, 2008));
        assertEquals(list.getAge(0), 13);
        assertFalse(list.isPremium(0));
        assertEquals(list.getMonths(0), 100);
        assertEquals(list.getSubTotal(0), new Money((long) 900, (byte) 0));
        deleteList();
    }

    /**
     * Test of deleteCustomer method, of class SubscriptionServices.
     */
    @Test
    public void testDeleteCustomer() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, true, 10);
        list.deleteCustomer(0);
        assertEquals(list.getCount(), 0);
    }

    /**
     * Test of getName method, of class SubscriptionServices.
     */
    @Test
    public void testGetName() throws Exception {
        list.addCustomer("John", 1, 1, 2000, true, 10);
        assertEquals(list.getName(0), "John");
        deleteList();
    }

    /**
     * Test of getBirthDate method, of class SubscriptionServices.
     */
    @Test
    public void testGetBirthDate() throws Exception {
        list.addCustomer("John", 1, 1, 2000, true, 10);
        assertEquals(list.getBirthDate(0), new BirthDate(1, 1, 2000));
        deleteList();
    }

    /**
     * Test of getAge method, of class SubscriptionServices.
     */
    @Test
    public void testGetAge() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, true, 10);
        assertEquals(list.getAge(0), 21);

    }

    /**
     * Test of isPremium method, of class SubscriptionServices.
     */
    @Test
    public void testIsPremium() throws Exception {
        list.addCustomer("John", 1, 1, 2000, true, 10);
        assertTrue(list.isPremium(0));
        deleteList();
    }

    /**
     * Test of getMonths method, of class SubscriptionServices.
     */
    @Test
    public void testGetMonths() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, true, 10);
        assertEquals(list.getMonths(0), 10);
        deleteList();
    }

    /**
     * Test of getSubTotal method, of class SubscriptionServices.
     */
    @Test
    public void testGetSubTotal() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, true, 10);
        assertEquals(list.getSubTotal(0), new Money((long) 120, (byte) 0));
    }

    /**
     * Test of getCount method, of class SubscriptionServices.
     */
    @Test
    public void testGetCount() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, true, 10);
        list.addCustomer("Jane", 2, 28, 2008, false, 100);
        list.addCustomer("Jake", 3, 22, 2004, true, 50);
        list.addCustomer("Justin", 4, 3, 2001, false, 20);
        assertEquals(list.getCount(), 4);
        deleteList();
    }

    /**
     * Test of getAverageAges method, of class SubscriptionServices.
     */
    @Test
    public void testGetAverageAges() throws Exception {
        list.addCustomer("John", 1, 1, 2000, true, 10);
        assertEquals(list.getAverageAges(), 21, 0.05);
        list.addCustomer("Jane", 2, 28, 2008, false, 100);
        list.addCustomer("Jake", 3, 22, 2004, true, 50);
        list.addCustomer("Justin", 4, 3, 2001, false, 20);
        assertEquals(list.getAverageAges(), 17.5, 0.05);
        deleteList();
    }

    /**
     * Test of getPremiumUsers method, of class SubscriptionServices.
     */
    @Test
    public void testGetPremiumUsers() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, true, 10);
        assertEquals(list.getPremiumUsers(), 1);
        list.addCustomer("Jane", 2, 28, 2008, false, 100);
        list.addCustomer("Jake", 3, 22, 2004, true, 50);
        list.addCustomer("Justin", 4, 3, 2001, false, 20);
        assertEquals(list.getPremiumUsers(), 2);
        list.addCustomer("Joplin", 5, 6, 1998, true, 70);
        assertEquals(list.getPremiumUsers(), 3);

    }

    /**
     * Test of getStandardUsers method, of class SubscriptionServices.
     */
    @Test
    public void testGetStandardUsers() throws Exception {
        list.addCustomer("John", 1, 1, 2000, false, 10);
        assertEquals(list.getStandardUsers(), 1);
        list.addCustomer("Jane", 2, 28, 2008, false, 100);
        list.addCustomer("Jake", 3, 22, 2004, true, 50);
        list.addCustomer("Justin", 4, 3, 2001, true, 20);
        assertEquals(list.getStandardUsers(), 2);
        list.addCustomer("Joplin", 5, 6, 1998, false, 70);
        assertEquals(list.getStandardUsers(), 3);
        deleteList();
    }

    /**
     * Test of getAverageMonths method, of class SubscriptionServices.
     */
    @Test
    public void testGetAverageMonths() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, false, 10);
        list.addCustomer("Jane", 2, 28, 2008, false, 100);
        list.addCustomer("Jake", 3, 22, 2004, true, 50);
        list.addCustomer("Justin", 4, 3, 2001, true, 20);
        list.addCustomer("Joplin", 5, 6, 1998, false, 70);
        assertEquals(list.getAverageMonths(), 50, 0.01);
        deleteList();
    }

    /**
     * Test of getTotalMonths method, of class SubscriptionServices.
     */
    @Test
    public void testGetTotalMonths() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, false, 10);
        list.addCustomer("Jane", 2, 28, 2008, false, 100);
        list.addCustomer("Jake", 3, 22, 2004, true, 50);
        list.addCustomer("Justin", 4, 3, 2001, true, 20);
        list.addCustomer("Joplin", 5, 6, 1998, false, 70);
        assertEquals(list.getTotalMonths(), 250);
        deleteList();
    }

    /**
     * Test of getAveragePayment method, of class SubscriptionServices.
     */
    @Test
    public void testGetAveragePayment() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, false, 2);
        list.addCustomer("Jane", 2, 28, 2008, false, 1);
        list.addCustomer("Jake", 3, 22, 2004, true, 1);
        list.addCustomer("Justin", 4, 3, 2001, true, 1);
        list.addCustomer("Joplin", 5, 6, 1998, false, 1);
        assertEquals(list.getAveragePayment(), new Money(12, (byte) 0));
        deleteList();
    }

    /**
     * Test of getTotalPayment method, of class SubscriptionServices.
     */
    @Test
    public void testGetTotalPayment() throws Exception {
        deleteList();
        list.addCustomer("John", 1, 1, 2000, false, 10);
        list.addCustomer("Jane", 2, 28, 2008, false, 100);
        list.addCustomer("Jake", 3, 22, 2004, true, 50);
        list.addCustomer("Justin", 4, 3, 2001, true, 20);
        list.addCustomer("Joplin", 5, 6, 1998, false, 70);
        assertEquals(list.getTotalPayment(), new Money(2460, (byte) 0));
        deleteList();
    }

    @Test
    public void testWriteAndReadCollection() {
        try {
            File ser = new File("customers.ser");
            assertFalse(ser.exists());
            list.addCustomer("Phuoc", 5, 13, 2001, true, 20);
            list.addCustomer("Joyce", 7, 24, 2001, true, 22);
            list.writeCollection();
            assertTrue(ser.exists());
            list = new SubscriptionServices();
            list.readCollection();
            assertEquals(list.getName(0), "Phuoc");
            assertEquals(list.getName(1), "Joyce");
        } catch (IndexException | BirthDateException ignored) {

        }
    }
}
