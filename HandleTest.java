/**
 * Tests the Handle class.
 *
 * @author karl88
 * @author sfbahr
 * @version Sep 17, 2014
 */
public class HandleTest extends student.TestCase
{
    /**
     * Tests the get method for handles.
     */
    public void testGet()
    {
        Handle testHandle = new Handle(2);
        assertEquals(2, testHandle.get());
    }

    /**
     * Tests the compareTo method for Handle the class.
     */
    public void testCompareTo()
    {
        Handle testHandle = new Handle(20);
        Handle testHandleLarge = new Handle(200);
        Handle testHandleSmall = new Handle(2);
        assertEquals(0, testHandle.compareTo(testHandle));
        assertEquals(-1, testHandle.compareTo(testHandleLarge));
        assertEquals(1, testHandle.compareTo(testHandleSmall));
    }
}

