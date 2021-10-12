package linda_tracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import numberlist.BirthDateException;
import numberlist.IndexException;
import numberlist.objectlist.BirthDate;
import numberlist.objectlist.Money;
import numberlist.objectlist.NumericArrayList;
import numberlist.primitivelist.IntegerArrayList;

/**
 * This class represents the business layer for the program. All calculations
 * are processed here, as well as the initialization of the parallel lists.
 *
 * @author Samuel Adrian Kosasih
 * @author Joyce Weng
 * @author Phuoc Nguyen
 *
 * @version %I%, %G%
 * @since JDK 8u271
 *
 * @see java.io.File
 * @see java.io.FileInputStream
 * @see java.io.FileOutputStream
 * @see java.io.ObjectInputStream
 * @see java.io.ObjectOutputStream
 * @see java.util.ArrayList
 * @see numberlist.BirthDateException
 * @see numberlist.IndexException
 * @see numberlist.objectlist.BirthDate
 * @see numberlist.objectlist.Money
 * @see numberlist.objectlist.NumericArrayList
 * @see numberlist.primitivelist.IntegerArrayList
 */
public class SubscriptionServices {

    //fields
    private ArrayList<String> names;
    private NumericArrayList birthDates;
    private IntegerArrayList ages;
    private ArrayList<Boolean> premiums;
    private IntegerArrayList months;
    private NumericArrayList subTotals;
    private int count;

    /**
     * Default constructor, initializes lists and reads from existing file.
     */
    public SubscriptionServices() {
        this.names = new ArrayList<>();
        this.birthDates = new NumericArrayList();
        this.ages = new IntegerArrayList();
        this.premiums = new ArrayList<>();
        this.months = new IntegerArrayList();
        this.subTotals = new NumericArrayList();
        readCollection();
    }

    /**
     * Adds the customer to our data layer with information sent by the
     * presentation layer.
     *
     * @param name the customer name
     * @param month the month of the customer's date of birth
     * @param day the day of the customer's date of birth
     * @param year the year of the customer's date of birth
     * @param premium customer's type of subscription (premium or standard)
     * @param noOfMonths total months customer have subscribed to the streaming
     * services
     * 
     * @throws numberlist.IndexException
     * @throws numberlist.BirthDateException
     */
    public void addCustomer(String name, int month, int day, int year, boolean premium,
            int noOfMonths) throws IndexException, BirthDateException {
        BirthDate bd = new BirthDate(month, day, year);
        this.names.add(name);
        this.birthDates.add(bd);
        this.premiums.add(premium);
        this.months.add(noOfMonths);
        this.ages.add(bd.getAge());
        this.subTotals.add(subTotals.getCount(), calculateTotal(premium, noOfMonths));
        count++;
        writeCollection();
    }

    /**
     * Updates customer information with data sent by the presentation layer
     *
     * @param index the index of the chosen customer
     * @param name the customer name
     * @param month the month of the customer's date of birth
     * @param day the day of the customer's date of birth
     * @param year the year of the customer's date of birth
     * @param premium customer's type of subscription (premium or standard)
     * @param noOfMonths total months customer have subscribed to the streaming
     * services
     * @throws numberlist.IndexException
     * @throws numberlist.BirthDateException
     */
    public void updateCustomer(int index, String name, int month, int day, int year,
            boolean premium, int noOfMonths) throws IndexException, BirthDateException {
        BirthDate bd = new BirthDate(month, day, year);
        this.names.set(index, name);
        this.birthDates.set(index, bd);
        this.premiums.set(index, premium);
        this.months.set(index, noOfMonths);
        this.ages.set(index, bd.getAge());
        this.subTotals.set(index, calculateTotal(premium, noOfMonths));
        writeCollection();
    }

    /**
     * Removes a specific customer from the list.
     *
     * @param index the index of the chosen customer
     * @throws numberlist.IndexException
     * @throws numberlist.BirthDateException
     */
    public void deleteCustomer(int index) throws IndexException, BirthDateException {
        names.remove(index);
        birthDates.remove(index);
        ages.remove(index);
        premiums.remove(index);
        months.remove(index);
        subTotals.remove(index);
        count--;
        writeCollection();
    }

    /**
     * Calculates the total amount of money the customer has spent on the
     * subscription service.
     *
     * @param premium the type of subscription
     * @param months the number of months the user has subscribed
     * @return a Money object representing the total amount of money the
     * customer has spent
     */
    private Money calculateTotal(boolean premium, int months) {
        if (premium) {
            return new Money((long) (months * 12.00), (byte) 0);
        } else {
            return new Money((long) (months * 9.00), (byte) 0);
        }
    }

    /**
     * Gets the String value of a specific customer's name
     *
     * @param index the index of the chosen customer
     * @return a string representing the customer's name
     */
    public String getName(int index) {
        return names.get(index);
    }

    /**
     * Gets the value of a specific customer's date of Birth
     *
     * @param index the index of the chosen customer
     * @return a BirthDate object of the customer's date of birth
     * @throws numberlist.IndexException
     */
    public BirthDate getBirthDate(int index) throws IndexException {
        return (BirthDate) birthDates.getValue(index);
    }

    /**
     * Gets the value of a specific customer's age
     *
     * @param index the index of the chosen customer
     * @return an int representing the customer age
     * @throws numberlist.IndexException
     */
    public int getAge(int index) throws IndexException {
        return (int) ages.getValue(index);
    }

    /**
     * Gets the Boolean value of a specific customer's subscription type
     *
     * @param index the index of the chosen customer
     * @return true if customer has premium, otherwise, false
     */
    public boolean isPremium(int index) {
        return premiums.get(index);
    }

    /**
     * Gets the value of a specific customer's subscription duration in months.
     *
     * @param index the index of the chosen customer
     * @return an int representing the number of months
     * @throws numberlist.IndexException
     */
    public int getMonths(int index) throws IndexException {
        return (int) months.getValue(index);
    }

    /**
     * Gets the value of a specific customer's total money spent.
     *
     * @param index the index of the chosen customer
     * @return a Money object representing total money spent
     * @throws numberlist.IndexException
     */
    public Money getSubTotal(int index) throws IndexException {
        return (Money) subTotals.getValue(index);
    }

    /**
     * Gets the value of the number of customers currently in the list.
     *
     * @return an int for the count value
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Calculates the average age of all customers stored in the list.
     *
     * @return a double value representing the average age
     * @throws numberlist.IndexException
     */
    public double getAverageAges() throws IndexException {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += ages.getValue(i);
        }
        return total / count;
    }

    /**
     * Counts the number of customers with premium subscription type.
     *
     * @return the number of premium subscribers
     */
    public int getPremiumUsers() {
        int premCount = 0;
        for (int i = 0; i < count; i++) {
            premCount += premiums.get(i) ? 1 : 0;
        }
        return premCount;
    }

    /**
     * Counts the number of customers with standard subscription type.
     *
     * @return the number of standard subscribers
     */
    public int getStandardUsers() {
        return premiums.size() - getPremiumUsers();
    }

    /**
     * Calculates the average duration of all customer subscription periods in
     * months
     *
     * @return a double value representing the average months
     * @throws numberlist.IndexException
     */
    public double getAverageMonths() throws IndexException {
        return getTotalMonths() / count;
    }

    /**
     * Accumulates the subscription periods, in months, of all customers.
     *
     * @return the total number of months subscribed by all customers
     * @throws numberlist.IndexException
     */
    public int getTotalMonths() throws IndexException {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += months.getValue(i);
        }
        return total;
    }

    /**
     * Calculates the average payment contributed by all customers.
     *
     * @return a Money object representing the average payment
     * @throws numberlist.IndexException
     */
    public Money getAveragePayment() throws IndexException {
        return new Money(getTotalPayment().getDollars() / count,
                (byte) (getTotalPayment().getDollars() % count * 10));
    }

    /**
     * Accumulates payment contributions of all customers.
     *
     * @return the total money contributed by all customers
     * @throws numberlist.IndexException
     */
    public Money getTotalPayment() throws IndexException {
        long total = 0;
        for (int i = 0; i < count; i++) {
            total += getSubTotal(i).getDollars();
        }
        return new Money(total, (byte) 0);
    }

    /**
     * Sorts the list in respect to the customer ages, from the youngest to the
     * oldest. Uses bubble sort, due to its simplicity and speed. Since we are
     * only comparing using integers of 1s and 0s, bubble sort ensures the whole
     * list is sorted.
     *
     * @throws numberlist.IndexException
     */
    public void sortAge() throws IndexException {
        for (int i = 0; i < ages.getCount(); i++) {
            for (int j = 0; j < ages.getCount() - 1; j++) {
                if (ages.getValue(j) > ages.getValue(j + 1)) {
                    swap(j, j + 1);
                }
            }
        }
    }

    /**
     * Sorts the list in respect to the customer subscription duration, from the
     * shortest to the longest. Uses bubble sort, due to its simplicity and
     * speed. Since we are only comparing using integers of 1s and 0s, bubble
     * sort ensures the whole list is sorted.
     *
     * @throws numberlist.IndexException
     */
    public void sortMonths() throws IndexException {
        for (int i = 0; i < months.getCount(); i++) {
            for (int j = 0; j < months.getCount() - 1; j++) {
                if (months.getValue(j) > months.getValue(j + 1)) {
                    swap(j, j + 1);
                }
            }
        }
    }

    /**
     * Sorts the list in respect to the customer spending, from the least to the
     * most. Uses bubble sort, due to its simplicity and speed. Since we are
     * only comparing using integers of 1s and 0s, bubble sort ensures the whole
     * list is sorted.
     *
     * @throws numberlist.IndexException
     */
    public void sortSubtotal() throws IndexException {
        for (int i = 0; i < months.getCount(); i++) {
            for (int j = 0; j < months.getCount() - 1; j++) {
                if (((Money) subTotals.getValue(j)).
                        compareTo((Money) subTotals.getValue(j + 1)) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    /**
     * Sorts the list in respect to the customer name (A - Z). Uses bubble sort,
     * due to its simplicity and speed. Since we are only comparing using
     * integers of 1s and 0s, bubble sort ensures the whole list is sorted.
     *
     * @throws numberlist.IndexException
     */
    public void sortName() throws IndexException {
        for (int i = 0; i < names.size(); i++) {
            for (int j = 0; j < names.size() - 1; j++) {
                if (names.get(j).compareTo(names.get(j + 1)) > 0) {
                    swap(j + 1, j);
                }
            }
        }

    }

    /**
     * A swap method to help in the sorting process. Swaps all values in all
     * lists at one call.
     *
     * @param i the first index
     * @param j the second index
     * @throws numberlist.IndexException
     */
    public void swap(int i, int j) throws IndexException {
        String sTemp;
        BirthDate bdTemp;
        long ageTemp;
        boolean premTemp;
        long monTemp;
        Money subTemp;

        sTemp = names.get(i);
        bdTemp = (BirthDate) birthDates.getValue(i);
        ageTemp = ages.getValue(i);
        premTemp = premiums.get(i);
        monTemp = months.getValue(i);
        subTemp = (Money) subTotals.getValue(i);

        names.set(i, names.get(j));
        birthDates.set(i, birthDates.getValue(j));
        ages.set(i, ages.getValue(j));
        premiums.set(i, premiums.get(j));
        months.set(i, months.getValue(j));
        subTotals.set(i, subTotals.getValue(j));

        names.set(j, sTemp);
        birthDates.set(j, bdTemp);
        ages.set(j, ageTemp);
        premiums.set(j, premTemp);
        months.set(j, monTemp);
        subTotals.set(j, subTemp);
    }

    /**
     * Writes an object file named "customers.ser" filled with the information
     * of each list.
     *
     * @return true if saved, otherwise, false
     */
    public boolean writeCollection() {
        boolean success = true;
        try (FileOutputStream fos = new FileOutputStream("customers.ser");
                ObjectOutputStream output = new ObjectOutputStream(fos)) {
            output.writeObject(names);
            output.writeObject(birthDates);
            output.writeObject(ages);
            output.writeObject(premiums);
            output.writeObject(months);
            output.writeObject(subTotals);
        } catch (Exception ex) {
            System.out.println("Cannot write to files." + ex.getMessage());
            success = false;
        }
        return success;
    }

    /**
     * Read an object file named "customers.ser" and initializes list with
     * information from that file.
     *
     * @return true if saved, otherwise, false
     */
    public final boolean readCollection() {
        boolean success = true;
        File ser = new File("customers.ser");
        if (ser.exists()) {
            try (FileInputStream fis = new FileInputStream("customers.ser");
                    ObjectInputStream input = new ObjectInputStream(fis)) {
                names = (ArrayList<String>) input.readObject();
                birthDates = (NumericArrayList) input.readObject();
                ages = (IntegerArrayList) input.readObject();
                premiums = (ArrayList<Boolean>) input.readObject();
                months = (IntegerArrayList) input.readObject();
                subTotals = (NumericArrayList) input.readObject();
                count = names.size();
            } catch (Exception ex) {
                System.out.println("Cannot read from file." + ex.getMessage());
                success = false;
            }
        }
        return success;
    }
}
