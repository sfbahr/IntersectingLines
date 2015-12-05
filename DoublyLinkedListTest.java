import student.TestCase;
/**
 * Tests for the {@link DoublyLinkedList} class.
 *
 * @author karl88
 * @author sfbahr
 * @version Sep 17, 2014
 */
public class DoublyLinkedListTest
    extends TestCase
{
    //~ Instance/static variables .............................................

    private DoublyLinkedList<String> list;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public DoublyLinkedListTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates two brand new, empty sets for each test method.
     */
    public void setUp()
    {
        list = new DoublyLinkedList<String>();
    }

    // ----------------------------------------------------------

    /**
     * Tests the getSize method.
     */
    public void testGetSize()
    {
        assertEquals(list.getSize(), 0);
        list.insertAfterCurrent("test1");
        list.insertAfterCurrent("test3");
        list.insertAfterCurrent("test2");
        assertEquals(list.getSize(), 3);
    }

    /**
     * Tests the insertAfterCurrent method.
     */
    public void testInsertAfterCurrent()
    {
        assertEquals(list.getSize(), 0);
        list.insertAfterCurrent("test1");
        list.insertAfterCurrent("test3");
        list.insertAfterCurrent("test2");
        assertEquals(list.getSize(), 3);
    }

    /**
     * Tests the insertBeforeCurrent method.
     */
    public void testInsertBeforeCurrent()
    {
        assertEquals(list.getSize(), 0);
        list.insertBeforeCurrent("test3");
        list.insertBeforeCurrent("test1");
        list.insertBeforeCurrent("test2");
        assertEquals(list.getSize(), 3);
    }


    /**
     * Tests the getCurrent method.
     */
    public void testGetCurrent()
    {
        assertNull(list.getCurrent());
        list.insertAfterCurrent("test");
        assertEquals("test", list.getCurrent());
    }

    /**
     * Tests the moveToFront method.
     */
    public void testMoveToFront()
    {
        list.moveToFront();
        list.insertBeforeCurrent("test3");
        list.insertBeforeCurrent("test1");
        list.insertBeforeCurrent("test2");
        list.moveToFront();
        assertEquals("test1", list.getCurrent());
        list.moveToFront();
        assertEquals("test1", list.getCurrent());
    }

    /**
     * Tests the moveToBack method.
     */
    public void testMoveToBack()
    {
        list.moveToBack();
        list.insertAfterCurrent("test1");
        list.insertAfterCurrent("test3");
        list.insertAfterCurrent("test2");
        list.moveToBack();
        assertEquals("test3", list.getCurrent());
        list.moveToBack();
        assertEquals("test3", list.getCurrent());
    }

    /**
     * Tests the moveToValue method.
     */
    public void testMoveToValue()
    {
        assertFalse(list.moveToValue("test"));
        list.insertAfterCurrent("test1");
        list.insertAfterCurrent("test4");
        list.insertAfterCurrent("test3");
        list.insertAfterCurrent("test2");
        list.moveToValue("test2");
        assertEquals("test2", list.getCurrent());
        list.moveToValue("invalid");
        assertEquals("test4", list.getCurrent());
    }

    /**
     * Tests the next method.
     */
    public void testNext()
    {
        assertFalse(list.next());
        list.insertAfterCurrent("test1");
        list.insertAfterCurrent("test3");
        list.insertAfterCurrent("test2");
        assertTrue(list.next());
        assertTrue(list.next());
        assertFalse(list.next());
    }

    /**
     * Tests the previous method.
     */
    public void testPrevious()
    {
        assertFalse(list.previous());
        list.insertBeforeCurrent("test3");
        list.insertBeforeCurrent("test1");
        list.insertBeforeCurrent("test2");
        assertTrue(list.previous());
        assertTrue(list.previous());
        assertFalse(list.previous());
    }

    /**
     * Tests the removeAndNext method.
     */
    public void testRemoveAndNext()
    {
        list.insertBeforeCurrent("test3");
        list.insertBeforeCurrent("test1");
        list.insertBeforeCurrent("test2");
        list.previous();
        list.removeAndNext();
        assertEquals("test3", list.getCurrent());
        list.removeAndNext();
        assertEquals("test1", list.getCurrent());
        list.removeAndNext();
        assertEquals(0, list.getSize());
        assertNull(list.getCurrent());
        list.removeAndNext();
        assertEquals(0, list.getSize());
        assertNull(list.getCurrent());
    }

    /**
     * Tests the removeAndPrevious method.
     */
    public void testRemoveAndPrevious()
    {
        list.insertBeforeCurrent("test3");
        list.insertBeforeCurrent("test1");
        list.insertBeforeCurrent("test2");
        list.previous();
        list.removeAndPrevious();
        assertEquals("test1", list.getCurrent());
        list.removeAndPrevious();
        assertEquals("test3", list.getCurrent());
        list.removeAndPrevious();
        assertEquals(0, list.getSize());
        assertNull(list.getCurrent());
        list.removeAndPrevious();
        assertEquals(0, list.getSize());
        assertNull(list.getCurrent());
    }


    /**
     * Tests that clear() properly clears the list or does nothing if the list
     * is clear.
     */
    public void testClear()
    {
        assertEquals(0, list.getSize());
        list.clear();
        assertEquals(0, list.getSize());
        list.insertBeforeCurrent("test1");
        list.insertBeforeCurrent("test2");
        list.insertBeforeCurrent("test3");
        list.clear();
        assertEquals(0, list.getSize());
    }

    /**
     * Tests the toString method.
     */
    public void testToString()
    {
        list.insertBeforeCurrent("test3");
        list.insertBeforeCurrent("test1");
        list.insertBeforeCurrent("test2");
        assertEquals(list.toString(), "test1 -> test2 -> test3");
    }
}
