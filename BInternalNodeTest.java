import student.TestCase;
/**
 *  This class tests the methods in the BInternalNode class.
 *
 *  @author karl88
 *  @author sfbahr
 *  @version Oct 18, 2014
 */
public class BInternalNodeTest extends TestCase
{
    private BInternalNode node;
    private KVPair pair1;
    private KVPair pair2;
    private BNode left;
    private BNode center;
    private BNode right;


    /**
     * This sets up the test class.
     */
    public void setUp()
    {
        node = new BInternalNode(pair1, pair2, left,
                center, right, false);
    }

    /**
     * This tests the isLeaf() method.
     */
    public void testIsLeaf()
    {
        assertFalse(node.isLeaf());
    }

    /**
     * This tests the getLeftChild() and setLeftChild() methods.
     */
    public void testGetLeftChild()
    {
        node.setLeftChild(left);
        assertEquals(left, node.getLeftChild());
    }

    /**
     * This tests the getMiddleChild() and setMiddleChild() methods.
     */
    public void testGetMiddleChild()
    {
        node.setMiddleChild(left);
        assertEquals(center, node.getMiddleChild());
    }

    /**
     * This tests the getRightChild() and setRightChild() methods.
     */
    public void testGetRightChild()
    {
        node.setRightChild(left);
        assertEquals(right, node.getRightChild());
    }

    /**
     * This tests the isPromoted() and setIsPromoted() methods.
     */
    public void testIsPromoted()
    {
        node.setIsPromoted(true);
        assertTrue(node.isPromoted());
        node.setIsPromoted(false);
        assertFalse(node.isPromoted());
    }
}
