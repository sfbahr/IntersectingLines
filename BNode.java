/**
 *  This method instantiates the parameters for the nodes 
 *  used in the 2-3+ tree, along with the Key/Value pairs 
 *  used to identify the values in the nodes. 
 *
 *  @author karl88
 *  @author sfbahr
 *  @version Oct 18, 2014
 */
public abstract class BNode
{
    private KVPair leftKVPair;
    private KVPair rightKVPair;
    private boolean isUnderflowed;

    //This is used to keep all KVPairs up to date after a deletion
    private KVPair minPair;
    
    /**
     * This is a singleton boolean which determines whether the 
     * node is a leaf or not.
     * 
     * @return whether or not the node is a leaf
     */
    public abstract boolean isLeaf();

    /**
     * This is a getter method for the left Key/Value pair of a node.
     * @return leftKVPair the left Key/Value pair
     */
    public KVPair getLeftKVPair()
    {
        return leftKVPair;
    }
    
    /**
     * This is a setter method for the left Key/Value pair of a node.
     * 
     * @param newLeftKVPair the new left Key/Value pair
     */
    public void setLeftKVPair(KVPair newLeftKVPair)
    {
        leftKVPair = newLeftKVPair;
    }

    /**
     * This is a getter method for the right Key/Value pair of a node.
     * @return rightKVPair the left Key/Value pair
     */
    public KVPair getRightKVPair()
    {
        return rightKVPair;
    }

    /**
     * This method is a setter method for the right 
     * Key/Value pair of a node.
     * 
     * @param newRightKVPair the new right Key/Value pair
     */
    public void setRightKVPair(KVPair newRightKVPair)
    {
        rightKVPair = newRightKVPair;
    }
    
    /**
     * This method returns the boolean identifier of whether or not a node
     * has been underflowed.
     * 
     * @return isUnderflowed the boolean state of underflow
     */
    public boolean isUnderflowed()
    {
        return isUnderflowed;
    }
    
    /**
     * This method is a setter for the boolean operation isUnderflowed.
     * @param setIsUnderflowed sets the boolean operator
     */
    public void setIsUnderflowed(boolean setIsUnderflowed)
    {
        isUnderflowed = setIsUnderflowed;
    }
    
    /**
     * This method is a toString method that builds a string 
     * to better observe the change of the Key/Value pairs.
     * 
     * @return the string representing the Key/Value pairs
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        if (leftKVPair != null)
        {
            builder.append(leftKVPair.toString());
        }
        if (rightKVPair != null)
        {
            builder.append(" " + rightKVPair.toString());
        }
        return builder.toString();
    }
    
    /**
     * This method is a getter for the minimum Key/Value pair, a 
     * comparison between two sets of pairs, helps determine how
     * the nodes change locations within the tree after operations
     * have been called on the tree. 
     * 
     * @return minPair the minimum pair of two Key/Value pairs
     */
    public KVPair minPair()
    {
        return minPair;
    }
    
    /**
     * This method is a setter method for the minimum Key/Value pair.
     * Helps set the value of minPair to the correct value based on 
     * the Key/Value pairs of the surrounding nodes. 
     *  
     * @param newMinPair the new minPair value
     */
    public void setMinPair(KVPair newMinPair)
    {
        minPair = newMinPair;
    }
}
