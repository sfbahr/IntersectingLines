/**
 *  This class instantiates the Internal Nodes of the B+ tree.
 *  It contains getters and setters for the relevant information
 *  about the internal nodes needed to manipulate the tree.
 *
 *  @author karl88
 *  @author sfbahr
 *  @version Oct 18, 2014
 */
public class BInternalNode extends BNode
{
    private BNode leftChild;
    private BNode middleChild;
    private BNode rightChild;
    private boolean isPromoted;


    /**
     * Create a new BInternalNode object, passing in the necessary
     * parameters.
     *
     * @param lKVPair left KV pair
     * @param rKVPair right KV pair
     * @param left left node
     * @param center center node
     * @param right right node
     * @param setIsPromoted boolean operator for promoted
     */
    public BInternalNode(KVPair lKVPair, KVPair rKVPair, BNode left,
        BNode center, BNode right, boolean setIsPromoted)
    {
        setLeftKVPair(lKVPair);
        setRightKVPair(rKVPair);

        leftChild = left;
        middleChild = center;
        rightChild = right;

        isPromoted = setIsPromoted;

        setMinPair(null);
    }

    /**
     * Boolean method returns whether or not the node is a leaf.
     * @return if it is a leaf or not
     */
    public boolean isLeaf()
    {
        return false;
    }

    /**
     * This method is a getter for the left child of a node.
     * @return the left child
     */
    public BNode getLeftChild()
    {
        return leftChild;
    }

    /**
     * This method is a setter for the left child of a node.
     * @param newLeftChild the new left child of the node
     */
    public void setLeftChild(BNode newLeftChild)
    {
        leftChild = newLeftChild;
    }

    /**
     * This method is a getter for the middle child of a node.
     * @return middleChild the middle child of the given node
     */
    public BNode getMiddleChild()
    {
        return middleChild;
    }

    /**
     * This method is a setter for the middle child of a node.
     * @param newMiddleChild the new middle child of a given node
     */
    public void setMiddleChild(BNode newMiddleChild)
    {
        middleChild = newMiddleChild;
    }

    /**
     * This method is a getter method for the right child of a node.
     *@return rightChild the right child of a given node
     */
    public BNode getRightChild()
    {
        return rightChild;
    }

    /**
     * This method is a setter for the right child of a node.
     * @param newRightChild the new right child of a given node
     */
    public void setRightChild(BNode newRightChild)
    {
        rightChild = newRightChild;
    }

    /**
     * This is a boolean method that determines whether or not a certain
     * node is promoted up the tree or not.
     * @return isPromoted the boolean operator
     */
    public boolean isPromoted()
    {
        return isPromoted;
    }

    /**
     * This method is a setter for the boolean operation of promotion
     * within the tree.
     *
     * @param setIsPromoted the boolean operator for promotion within the tree
     */
    public void setIsPromoted(boolean setIsPromoted)
    {
        isPromoted = setIsPromoted;
    }
}