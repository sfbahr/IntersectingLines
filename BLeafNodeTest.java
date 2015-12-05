import student.TestCase;
/**
 *  This class tests the methods in the BLeafNode class.
 *
 *  @author karl88
 *  @author sfbahr
 *  @version Oct 18, 2014
 */
public class BLeafNodeTest extends TestCase
{
    private BLeafNode leaf1;
    private BLeafNode leaf2;
    private BLeafNode leaf3;
    private KVPair pair1;
    private KVPair pair2;
    private KVPair pair3;

    /**
     * This sets up the test class.
     */
    public void setUp()
    {
        pair1 = new KVPair(new Handle(1), new Handle(10));
        pair2 = new KVPair(new Handle(2), new Handle(11));
        pair3 = new KVPair(new Handle(3), new Handle(12));
        KVPair pair4 = new KVPair(new Handle(4), new Handle(13));
        KVPair pair5 = new KVPair(new Handle(5), new Handle(14));
        KVPair pair6 = new KVPair(new Handle(6), new Handle(15));
        leaf1 = new BLeafNode(pair1, pair2);
        leaf2 = new BLeafNode(pair3, pair4);
        leaf3 = new BLeafNode(pair5, pair6);
    }

    /**
     * This tests the isLeaf() method.
     */
    public void testIsLeaf()
    {
        assertTrue(leaf1.isLeaf());
    }

    /**
     * This tests the getPrev() and setPrev() methods.
     */
    public void testGetPrev()
    {
        leaf2.setPrev(leaf1);
        assertEquals(leaf1, leaf2.getPrev());
    }

    /**
     * This tests the getNext() and setNext() methods.
     */
    public void testGetNext()
    {
        leaf2.setNext(leaf3);
        assertEquals(leaf3, leaf2.getNext());
    }

    /**
     * This tests the getLeftKVPair() and setLeftKVPair() methods.
     */
    public void testGetLeftKVPair()
    {
        leaf2.setLeftKVPair(pair2);
        assertEquals(pair2, leaf2.getLeftKVPair());
    }

    /**
     * This tests the getRightKVPair() and setRightKVPair() methods.
     */
    public void testGetRightKVPair()
    {
        leaf2.setRightKVPair(pair3);
        assertEquals(pair3, leaf2.getRightKVPair());
    }

    /**
     * This tests the toString() method.
     */
    public void testToString()
    {
        assertEquals("3 12 4 13", leaf2.toString());
        leaf2.setLeftKVPair(null);
        assertEquals(" 4 13", leaf2.toString());
        leaf2.setLeftKVPair(pair3);
        leaf2.setRightKVPair(null);
        assertEquals("3 12", leaf2.toString());
    }

    /**
     * This tests the isUnderflowed() and setIsUnderflowed() methods.
     */
    public void testIsUnderflowed()
    {
        leaf2.setIsUnderflowed(true);
        assertTrue(leaf2.isUnderflowed());
        leaf2.setIsUnderflowed(false);
        assertFalse(leaf2.isUnderflowed());
    }

    /**
     * This tests the minPair() and setMinPair() methods.
     */
    public void testMinPair()
    {
        leaf2.setMinPair(pair1);
        assertEquals(pair1, leaf2.minPair());
    }
}