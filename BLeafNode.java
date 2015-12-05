/**
 *  This class instantiates the Leaf nodes that are to be used 
 *  within the implementation of this 2-3+ tree. It also contains 
 *  getter, setter, and boolean methods which help manipulate
 *  these specific nodes within the tree.
 *
 *  @author karl88
 *  @author sfbahr
 *  @version Oct 18, 2014
 */
public class BLeafNode extends BNode
{
    private BLeafNode prev;
    private BLeafNode next;
    
    // ----------------------------------------------------------
    /**
     * Create a new BLeftNode object.
     * @param lKVPair the left Key/Value pair
     * @param rKVPair the right Key/Value pair
     */
    public BLeafNode(KVPair lKVPair, KVPair rKVPair)
    {
        setLeftKVPair(lKVPair);
        setRightKVPair(rKVPair);
        prev = null;
        next = null;
        
        setMinPair(null);
    }
    
    /**
     * This is a boolean method that determines whether or not a given node
     * is  a leaf.
     * 
     * @return whether the node is a leaf or not
     */
    public boolean isLeaf()
    {
        return true;
    }
    
    /**
     * This method is a getter for the previous node to the 
     * given node in the list. 
     * 
     * @return prev the previous node
     */
    public BLeafNode getPrev()
    {
        return prev;
    }

    /**
     * This method is a setter for the previous node to the given
     * one in the list.
     * 
     * @param newPrev the new previous node
     */
    public void setPrev(BLeafNode newPrev)
    {
        prev = newPrev;
    }

    /**
     * This method is a setter for the next node to the given one 
     * in the list.
     * 
     * @return next the next node in the list
     */
    public BLeafNode getNext()
    {
        return next;
    }

    /**
     * This method is a setter for the next node in the list to 
     * the given one.
     * 
     * @param newNext the new next node in the list
     */
    public void setNext(BLeafNode newNext)
    {
        next = newNext;
    }
}
