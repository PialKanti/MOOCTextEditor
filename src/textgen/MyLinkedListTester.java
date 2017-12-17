/**
 *
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 */
public class MyLinkedListTester {

    private static final int LONG_LIST_LENGTH = 10;

    MyLinkedList<String> shortList;
    MyLinkedList<Integer> emptyList;
    MyLinkedList<Integer> longerList;
    MyLinkedList<Integer> list1;
    MyLinkedList<Integer> list2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // Feel free to use these lists, or add your own
        shortList = new MyLinkedList<String>();
        shortList.add("A");
        shortList.add("B");
        emptyList = new MyLinkedList<Integer>();
        longerList = new MyLinkedList<Integer>();
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            longerList.add(i);
        }
        list1 = new MyLinkedList<Integer>();
        list1.add(65);
        list1.add(21);
        list1.add(42);
        list2 = new MyLinkedList<Integer>();

    }


    /**
     * Test if the get method is working correctly.
     */
    /*You should not need to add much to this method.
     * We provide it as an example of a thorough test. */
    @Test
    public void testGet() {
        //test empty list, get should throw an exception
        try {
            emptyList.get(0);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        // test short list, first contents, then out of bounds
        assertEquals("Check first", "A", shortList.get(0));
        assertEquals("Check second", "B", shortList.get(1));

        try {
            shortList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            shortList.get(2);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        // test longer list contents
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
        }

        // test off the end of the longer array
        try {
            longerList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            longerList.get(LONG_LIST_LENGTH);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {
        }

    }


    /**
     * Test removing an element from the list.
     * We've included the example from the concept challenge.
     * You will want to add more tests.
     */
    @Test
    public void testRemove() {
        try {
            int a = list1.remove(-1);
            fail("index should be out of bound");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            int a = list1.remove(list1.size);
            fail("index should be out of bound");
        } catch (IndexOutOfBoundsException e) {

        }
        int a = list1.remove(0);
        assertEquals("Remove: lower bound check a is correct ", 65, a);
        int b = list1.remove(list1.size - 1);
        assertEquals("Remove: upper bound check a is correct ", 42, b);


        // TODO: Add more tests here
    }

    /**
     * Test adding an element into the end of the list, specifically
     * public boolean add(E element)
     */
    @Test
    public void testAddEnd() {
        // TODO: implement this test
        list1.add(500);
        int a = list1.get(list1.size - 1);
        assertEquals("Add: Add End a is correct ", 500, a);
        try {
            list1.add(null);
            fail("null element can't be added");
        } catch (NullPointerException e) {

        }
    }


    /**
     * Test the size of the list
     */
    @Test
    public void testSize() {
        // TODO: implement this test
        int a = list1.size;
        assertEquals("Set: Size a is correct ", 3, a);
    }


    /**
     * Test adding an element into the list at a specified index,
     * specifically:
     * public void add(int index, E element)
     */
    @Test
    public void testAddAtIndex() {
        // TODO: implement this test
        try {
            list1.add(-1, 500);
            fail("index should be out of bound");

        } catch (IndexOutOfBoundsException e) {

        }
        try {
            list1.add(list1.size, 500);
            fail("index should be out of bound");

        } catch (IndexOutOfBoundsException e) {

        }
    }

    /**
     * Test setting an element in the list
     */
    @Test
    public void testSet() {
        // TODO: implement this test
        int a = longerList.set(0, 0);
        assertEquals("Set:lower bound set check a is correct ", 0, a);
        int b = longerList.set(longerList.size - 1, 100);
        assertEquals("Set: upper bound set check a is correct ", 9, b);


    }


    // TODO: Optionally add more test methods.

}
