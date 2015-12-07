import java.util.LinkedList;
import java.util.List;

/**
 * This is the instantiation of the BPTree and all of its helper methods.
 * It handles, insert, delete, underflowed cases and print methods
 * for the 2-3+ tree.
 *
 *  @author karl88
 *  @author sfbahr
 *  @version Oct 18, 2014
 */
public class BPTree
{
    private BNode root;
    private boolean insertSuccess;
    private boolean deleteSuccess;

    // ----------------------------------------------------------
    /**
     * Create a new BPTree object.
     */
    public BPTree()
    {
        root = null;
    }

    /**
     * This is the constructor for the print method which
     * prints the 2-3+ tree and its Key/Value pairs.
     */
    public void print()
    {
        System.out.println("Printing 2-3 tree:");
        print(root, 0);
    }

    /**
     * This is the implementation of the print method.
     *
     * @param tempRoot the temporary root
     * @param depth the depth of the Key/Value pairs in the nodes
     */
    private void print(BNode tempRoot, int depth)
    {
        if (tempRoot == null)
        {
            return;
        }
        for (int i = 0; i < depth; i++)
        {
            System.out.print("  ");
        }
        System.out.println(tempRoot.toString());

        if (tempRoot.isLeaf())
        {
            return;
        }
        print(((BInternalNode) tempRoot).getLeftChild(), ++depth);
        print(((BInternalNode) tempRoot).getMiddleChild(), depth);
        print(((BInternalNode) tempRoot).getRightChild(), depth);
    }


    /**
     * This method finds the leaf with the correct Key/Value pair.
     * Looks in the current pair and two spots ahead to ensure that
     * it is returning the correct value.
     *
     * @param pair Key/Value pair
     * @param tempLeaf the temporary leaf being accessed
     * @return the correct leaf
     */
    public BLeafNode leafWithPair(KVPair pair, BLeafNode tempLeaf)
    {
        if (pair == null || tempLeaf == null)
        {
            return null;
        }

        if (tempLeaf.getLeftKVPair().compareTo(pair) == 0 ||
                (tempLeaf.getRightKVPair() != null &&
                tempLeaf.getRightKVPair().compareTo(pair) == 0))
        {
            return tempLeaf;
        }
        if (tempLeaf.getNext() != null)
        {
            return tempLeaf.getNext();
        }
        return null;
    }
    
    public List<Point> findRange(int lowerBound, int upperBound, int y)
    {
    	return findRange(this.root, lowerBound, upperBound, y);
    }
    
    private List<Point> findRange(BNode currentNode, int lowerBound, int upperBound, int y)
    {
    	if (!currentNode.isLeaf())//internal
    	{
    		BInternalNode currentInternal = (BInternalNode)currentNode;
    		KVPair<VerticalLine> left = currentInternal.getLeftKVPair();
    		KVPair<VerticalLine> right = currentInternal.getRightKVPair();
    		if (left.getValue().getX() > lowerBound)
    		{
    			return findRange(currentInternal.getLeftChild(), lowerBound, upperBound, y);
    		}
    		else if (right == null)
    		{
    			return findRange(currentInternal.getMiddleChild(), lowerBound, upperBound, y);
    		}
    		else if (right.getValue().getX() > lowerBound)
    		{
    			return findRange(currentInternal.getMiddleChild(), lowerBound, upperBound, y);
    		}
    		else
    		{
    			return findRange(currentInternal.getRightChild(), lowerBound, upperBound, y);
    		}
    	}
    	else//leaf
    	{
    		List<Point> intersections = new LinkedList<Point>();
    		BLeafNode currentLeaf = (BLeafNode)currentNode;
    		while (currentLeaf != null)
    		{
    			int leftX = ((KVPair<VerticalLine>) currentLeaf.getLeftKVPair()).getValue().getX();
        		Integer rightX = null;
        		if (currentLeaf.getRightKVPair() != null) {
        			rightX = ((KVPair<VerticalLine>) currentLeaf.getRightKVPair()).getValue().getX();
        		}
        		if (leftX >= lowerBound && leftX <= upperBound)
        		{
        			intersections.add(new Point(leftX, y));
        		}
        		if (rightX != null && rightX >= lowerBound && rightX <= upperBound)
        		{
        			intersections.add(new Point(rightX, y));
        		}
        		if (rightX != null && rightX > upperBound)
        		{
        			return intersections;
        		}
        		else
        		{
        			currentLeaf = currentLeaf.getNext();
        		}
    		}
    		return intersections;
    	}
    }

    /**
     * This method returns the next Key/Value pair to the
     * leaf/node being accessed.
     *
     * @param pair the pair being accessed
     * @param tempLeaf a temporary leaf
     * @return the next Key/Value pair
     */
    private KVPair nextPair(KVPair pair, BLeafNode tempLeaf)
    {
        if (pair == tempLeaf.getRightKVPair())
        {
            if (tempLeaf.getNext() != null)
            {
                tempLeaf = tempLeaf.getNext();
                pair = tempLeaf.getLeftKVPair();
            }
            else
            {
                return null;
            }
        }
        else
        {
            if (tempLeaf.getRightKVPair() != null)
            {
                pair = tempLeaf.getRightKVPair();
            }
            else
            {
                if (tempLeaf.getNext() != null)
                {
                    tempLeaf = tempLeaf.getNext();
                    pair = tempLeaf.getLeftKVPair();
                }
                else
                {
                    return null;
                }
            }
        }
        return pair;
    }

    /**
     * Does nothing if a duplicate, otherwise returns True
     * for a successful insert.
     *
     * @param newPair the pair being inserted
     * @return whether or not the insert was successful
     */
    public boolean insert(KVPair newPair)
    {
        insertSuccess = true;
        root = insert(newPair, root);
        return insertSuccess;
    }

    /**
     * Does nothing if a duplicate, otherwise inserts the new
     * Key/Value pair in the correct location.
     *
     * @param newPair the pair being inserted
     * @param tempRoot a temporary root
     * @return the new tree state
     */
    private BNode insert(KVPair newPair, BNode tempRoot)
    {
        if (tempRoot == null)
        {
            return new BLeafNode(newPair, null);
        }

        if (tempRoot.isLeaf())
        {
            if (tempRoot.getLeftKVPair().compareTo(newPair) == 0 ||
                    (tempRoot.getRightKVPair() != null &&
                    tempRoot.getRightKVPair().compareTo(newPair) == 0))
            {
                //newPair is already in the tree, do not modify anything
                insertSuccess = false;
                return tempRoot;
            }

            if (tempRoot.getRightKVPair() == null)
            {
                if (tempRoot.getLeftKVPair().compareTo(newPair) < 0)
                {
                    tempRoot.setRightKVPair(newPair);
                }
                else
                {
                    tempRoot.setRightKVPair(tempRoot.getLeftKVPair());
                    tempRoot.setLeftKVPair(newPair);
                }
                return tempRoot;
            }
            KVPair max;
            KVPair mid;
            if (tempRoot.getRightKVPair().compareTo(newPair) < 0)
            {
                max = newPair;
                mid = tempRoot.getRightKVPair();
            }
            else
            {
                if (tempRoot.getLeftKVPair().compareTo(newPair) < 0)
                {
                    max = tempRoot.getRightKVPair();
                    mid = newPair;
                }
                else
                {
                    max = tempRoot.getRightKVPair();
                    mid = tempRoot.getLeftKVPair();
                    tempRoot.setLeftKVPair(newPair);
                }
            }
            tempRoot.setRightKVPair(null);

            //The leaf node that is created to handle the overflow
            BLeafNode newLeaf = new BLeafNode(mid, max);

            if (((BLeafNode) tempRoot).getNext() != null)
            {
                newLeaf.setNext(((BLeafNode) tempRoot).getNext());
                ((BLeafNode) tempRoot).getNext().setPrev(newLeaf);
            }
            ((BLeafNode) tempRoot).setNext(newLeaf);
            newLeaf.setPrev((BLeafNode) tempRoot);

            return new BInternalNode(mid, null, tempRoot, newLeaf, null, true);
        }

        //Equivalent of tempRoot, only casted so that this casting does not have
        //to happen every single time
        BInternalNode tempInternal = (BInternalNode) tempRoot;
        if (tempInternal.getLeftKVPair().compareTo(newPair) > 0)
        {
            BNode returnedRoot = insert(newPair, tempInternal.getLeftChild());
            if (returnedRoot.isLeaf() ||
                !((BInternalNode) returnedRoot).isPromoted())
            {
                tempInternal.setLeftChild(returnedRoot);
                return tempInternal;
            }
            return handlePromotedNode(tempInternal,
                (BInternalNode) returnedRoot);
        }
        else if (tempInternal.getRightKVPair() == null)
        {
            BNode returnedRoot = insert(newPair, tempInternal.getMiddleChild());
            if (returnedRoot.isLeaf() ||
                !((BInternalNode) returnedRoot).isPromoted())
            {
                tempInternal.setMiddleChild(returnedRoot);
                return tempInternal;
            }
            return handlePromotedNode(tempInternal,
                (BInternalNode) returnedRoot);
        }
        else if (tempInternal.getRightKVPair().compareTo(newPair) > 0)
        {
            BNode returnedRoot = insert(newPair, tempInternal.getMiddleChild());
            if (returnedRoot.isLeaf() ||
                !((BInternalNode) returnedRoot).isPromoted())
            {
                tempInternal.setMiddleChild(returnedRoot);
                return tempInternal;
            }
            return handlePromotedNode(tempInternal,
                (BInternalNode) returnedRoot);
        }
        BNode returnedRoot = insert(newPair, tempInternal.getRightChild());
        if (returnedRoot.isLeaf() ||
            !((BInternalNode) returnedRoot).isPromoted())
        {
            tempInternal.setRightChild(returnedRoot);
            return tempInternal;
        }
        return handlePromotedNode(tempInternal, (BInternalNode) returnedRoot);
    }

    /**
     * Used for the insertion method to determine how overflow works.
     *
     * @param oldNode the old internal node handles an overflow
     * from the node below it, a promotion happens when there is an
     * overflow
     * @param promoted whether or not the node is promoted
     * @return the new state of the tree
     */
    private BInternalNode handlePromotedNode(BInternalNode oldNode,
            BInternalNode promoted)
    {
        KVPair promotedValue = promoted.getLeftKVPair();
        oldNode.setIsPromoted(false);

        if (oldNode.getRightKVPair() == null)
        {

            KVPair max;
            KVPair min;
            if (oldNode.getLeftKVPair().compareTo(promotedValue) < 0)
            {
                max = promotedValue;
                min = oldNode.getLeftKVPair();

                oldNode.setRightChild(promoted.getMiddleChild());
                //oldNode.setMiddleChild(promoted.getLeftChild());

            }
            else
            {
                max = oldNode.getLeftKVPair();
                min = promotedValue;

                oldNode.setRightChild(oldNode.getMiddleChild());
                oldNode.setMiddleChild(promoted.getMiddleChild());
                oldNode.setLeftChild(promoted.getLeftChild());
            }
            oldNode.setRightKVPair(max);
            oldNode.setLeftKVPair(min);

            return oldNode;
        }

        //TIME TO SPLIT
        //Find the max and mid values
        KVPair maxValue;
        KVPair midValue;
        BNode right;
        BNode midRight;
        BNode midLeft;
        BNode left;
        if (oldNode.getRightKVPair().compareTo(promotedValue) < 0)
        {
            maxValue = promotedValue;
            midValue = oldNode.getRightKVPair();

            right = promoted.getMiddleChild();
            midRight = promoted.getLeftChild();
            midLeft = oldNode.getMiddleChild();
            left = oldNode.getLeftChild();
        }
        else
        {
            if (oldNode.getLeftKVPair().compareTo(promotedValue) < 0)
            {
                maxValue = oldNode.getRightKVPair();
                midValue = promotedValue;

                right = oldNode.getRightChild();
                midRight = promoted.getMiddleChild();
                midLeft = promoted.getLeftChild();
                left = oldNode.getLeftChild();
            }
            else
            {
                maxValue = oldNode.getRightKVPair();
                midValue = oldNode.getLeftKVPair();

                oldNode.setLeftKVPair(promotedValue);
                right = oldNode.getRightChild();
                midRight = oldNode.getMiddleChild();
                midLeft = promoted.getMiddleChild();
                left = promoted.getLeftChild();
            }
        }

        oldNode.setLeftChild(left);
        oldNode.setMiddleChild(midLeft);
        oldNode.setRightChild(null);
        oldNode.setRightKVPair(null);
        //The internal node created to handle the overflow
        BInternalNode newInternal = new BInternalNode(maxValue, null, midRight,
                right, null, false);

        return new BInternalNode(midValue, null, oldNode, newInternal, null,
            true);
    }

    /**
     * Does nothing when given a target that is not in the BPTree,
     * otherwise deletes the target value from the tree.
     *
     * @param target the value to be deleted
     * @return the success of the delete operation
     */
    public boolean delete(KVPair target)
    {
        deleteSuccess = true;
        root = delete(target, root);
        if (root != null && root.isUnderflowed())
        {
            if (!root.isLeaf())
            {
                root = ((BInternalNode) root).getLeftChild();
            }
            else
            {
                root = null;
            }
        }
        return deleteSuccess;
    }

    /**
     * Does nothing when given a target that is not in the BPTree,
     * otherwise deletes the target value.
     *
     * @param target the value to be deleted
     * @param tempRoot the temporary root
     * @return
     */
    private BNode delete(KVPair target, BNode tempRoot)
    {
        if (tempRoot == null)
        {
            return null;
        }

        //Prevents minPair changes from previous deletions from interfering with
        //this deletion
        tempRoot.setMinPair(null);
        //Prevents previous deletions underflow settings from affecting this
        //deletion
        tempRoot.setIsUnderflowed(false);
        if (tempRoot.isLeaf())
        {
            BLeafNode tempLeaf = (BLeafNode) tempRoot;
            //The target does not exist in this BPTree, return it as-is
            if (tempLeaf.getLeftKVPair().compareTo(target) != 0 &&
                    (tempLeaf.getRightKVPair() == null ||
                        tempLeaf.getRightKVPair().compareTo(target) != 0))
            {
                deleteSuccess = false;
                return tempLeaf;
            }
            if (tempLeaf.getRightKVPair() != null)
            {
                if (tempLeaf.getLeftKVPair().compareTo(target) == 0)
                {
                    tempLeaf.setLeftKVPair(tempLeaf.getRightKVPair());
                    tempLeaf.setMinPair(tempLeaf.getLeftKVPair());
                }
                tempLeaf.setRightKVPair(null);
                return tempLeaf;
            }

            tempLeaf.setIsUnderflowed(true);
            return tempLeaf;
        }

        BInternalNode tempInternal = (BInternalNode) tempRoot;
        //move into the left child
        if (tempInternal.getLeftKVPair().compareTo(target) > 0)
        {
            BNode returnedRoot = delete(target, tempInternal.getLeftChild());
            if (returnedRoot.isUnderflowed() && returnedRoot.isLeaf())
            {
                handleUnderflowedLeaf(tempInternal, 1);
            }
            tempInternal.setMinPair(tempInternal.getLeftChild().minPair());
            if (returnedRoot.isUnderflowed() && !returnedRoot.isLeaf())
            {
                handleUnderflowedInternal(tempInternal, 1);
            }

            return tempInternal;
        }
        //move into the middle child
        else if (tempInternal.getRightKVPair() == null)
        {
            BNode returnedRoot = delete(target, tempInternal.getMiddleChild());

            if (returnedRoot.isUnderflowed() && returnedRoot.isLeaf())
            {
                handleUnderflowedLeaf(tempInternal, 2);
            }
            //If the minimum KVPair in this child has changed, update the KVPair
            //in the parent correspondingly
            if (tempInternal.getMiddleChild() != null &&
                tempInternal.getMiddleChild().minPair() != null)
            {
                tempInternal.setLeftKVPair(
                    tempInternal.getMiddleChild().minPair());
            }
            if (returnedRoot.isUnderflowed() && !returnedRoot.isLeaf())
            {
                handleUnderflowedInternal(tempInternal, 2);
            }

            return tempInternal;
        }
        else if (tempInternal.getRightKVPair().compareTo(target) > 0)
        {
            BNode returnedRoot = delete(target, tempInternal.getMiddleChild());

            if (returnedRoot.isUnderflowed() && returnedRoot.isLeaf())
            {
                handleUnderflowedLeaf(tempInternal, 2);
            }

            if (tempInternal.getMiddleChild() != null &&
                tempInternal.getMiddleChild().minPair() != null)
            {
                tempInternal.setLeftKVPair(
                    tempInternal.getMiddleChild().minPair());
            }
            if (returnedRoot.isUnderflowed() && !returnedRoot.isLeaf())
            {
                handleUnderflowedInternal(tempInternal, 2);
            }

            return tempInternal;
        }
        //move into the right child
        BNode returnedRoot = delete(target, tempInternal.getRightChild());
        if (returnedRoot.isUnderflowed() && returnedRoot.isLeaf())
        {
            handleUnderflowedLeaf(tempInternal, 3);
        }

        if (tempInternal.getRightChild() != null &&
            tempInternal.getRightChild().minPair() != null)
        {
            tempInternal.setRightKVPair(tempInternal.getRightChild().minPair());
        }
        if (returnedRoot.isUnderflowed() && !returnedRoot.isLeaf())
        {
            handleUnderflowedInternal(tempInternal, 3);
        }

        return tempInternal;
    }

    /**
     * This method handles underflowed leaf delete cases.
     *
     * @param parent the parent of the node having underflow
     * @param underflowedChildPos the position of the underflowed node
     */
    private void handleUnderflowedLeaf(
        BInternalNode parent, int underflowedChildPos)
    {
        BLeafNode leftLeaf = (BLeafNode) parent.getLeftChild();
        BLeafNode middleLeaf = (BLeafNode) parent.getMiddleChild();
        BLeafNode rightLeaf = (BLeafNode) parent.getRightChild();

        if (underflowedChildPos == 1)
        {
            //Left leaf can borrow from the middle leaf
            if (middleLeaf.getRightKVPair() != null)
            {
                leftLeaf.setLeftKVPair(middleLeaf.getLeftKVPair());
                middleLeaf.setLeftKVPair(middleLeaf.getRightKVPair());
                middleLeaf.setRightKVPair(null);

                parent.setLeftKVPair(middleLeaf.getLeftKVPair());
                leftLeaf.setMinPair(leftLeaf.getLeftKVPair());
                leftLeaf.setIsUnderflowed(false);
                return;
            }


            //Set the next and previous points around the left child
            if (leftLeaf.getPrev() != null)
            {
                leftLeaf.getPrev().setNext(middleLeaf);
            }
            middleLeaf.setPrev(leftLeaf.getPrev());

            //Left child disappears, middle child replaces it
            //Right child replaces middle child if it exists
            parent.setLeftChild(middleLeaf);
            parent.setMiddleChild(rightLeaf);
            parent.setRightChild(null);
            parent.setRightKVPair(null);
            //leftLeaf, middleLeaf, and rightLeaf are no longer accurate

            parent.getLeftChild().setMinPair(
                parent.getLeftChild().getLeftKVPair());
            if (parent.getMiddleChild() == null)
            {
                parent.setLeftKVPair(null);
                parent.setIsUnderflowed(true);
            }
            else
            {
                parent.setLeftKVPair(parent.getMiddleChild().getLeftKVPair());
            }
        }
        else if (underflowedChildPos == 2)
        {
            //Middle child can borrow from the left child
            if (leftLeaf.getRightKVPair() != null)
            {
                middleLeaf.setLeftKVPair(leftLeaf.getRightKVPair());
                leftLeaf.setRightKVPair(null);

                middleLeaf.setMinPair(middleLeaf.getLeftKVPair());
                middleLeaf.setIsUnderflowed(false);
                return;
            }


            //Middle child can borrow from the right child
            if (rightLeaf != null && rightLeaf.getRightKVPair() != null)
            {
                middleLeaf.setLeftKVPair(rightLeaf.getLeftKVPair());
                rightLeaf.setLeftKVPair(rightLeaf.getRightKVPair());
                rightLeaf.setRightKVPair(null);

                parent.setRightKVPair(rightLeaf.getLeftKVPair());

                middleLeaf.setMinPair(middleLeaf.getLeftKVPair());
                middleLeaf.setIsUnderflowed(false);
                return;
            }

            //Set the next and previous points around the middle child
            if (rightLeaf != null)
            {
                leftLeaf.setNext(rightLeaf);
                rightLeaf.setPrev(leftLeaf);
            }
            else
            {
                leftLeaf.setNext(middleLeaf.getNext());
                if (middleLeaf.getNext() != null)
                {
                    middleLeaf.getNext().setPrev(leftLeaf);
                }
            }

            //Middle child disappears, right child replaces it if it exists
            parent.setMiddleChild(rightLeaf);
            parent.setRightChild(null);
            parent.setRightKVPair(null);
            if (parent.getMiddleChild() == null)
            {
                parent.setLeftKVPair(null);
                parent.setIsUnderflowed(true);
            }
            else
            {
                parent.getMiddleChild().setMinPair(
                    parent.getMiddleChild().getLeftKVPair());
                parent.setLeftKVPair(parent.getMiddleChild().getLeftKVPair());
            }
        }
        else
        {
            //Right child can borrow from the middle child
            if (middleLeaf.getRightKVPair() != null)
            {
                rightLeaf.setLeftKVPair(middleLeaf.getRightKVPair());
                middleLeaf.setRightKVPair(null);

                rightLeaf.setMinPair(rightLeaf.getLeftKVPair());
                rightLeaf.setIsUnderflowed(false);
                return;
            }

            //Set the next and previous points around the right child
            if (rightLeaf.getNext() != null)
            {
                rightLeaf.getNext().setPrev(middleLeaf);
            }
            middleLeaf.setNext(rightLeaf.getNext());

            //Right child disappears
            parent.setRightChild(null);
            parent.setRightKVPair(null);
        }
    }

    /**
     * Handles the underflowed internal cases when the delete method
     * is called on the tree.
     *
     * @param parent the parent of the node having an underflow
     * @param underflowedChildPos the position of the node having an underflow
     */
    private void handleUnderflowedInternal(
        BInternalNode parent, int underflowedChildPos)
    {
        BInternalNode leftInternal = (BInternalNode) parent.getLeftChild();
        BInternalNode middleInternal = (BInternalNode) parent.getMiddleChild();
        BInternalNode rightInternal = (BInternalNode) parent.getRightChild();

        if (underflowedChildPos == 1)
        {
            //Left node can borrow from the middle node
            if (middleInternal.getRightKVPair() != null)
            {
                //Update the KVPair pointers in the internal nodes
                leftInternal.setLeftKVPair(parent.getLeftKVPair());
                parent.setLeftKVPair(middleInternal.getLeftKVPair());
                middleInternal.setLeftKVPair(middleInternal.getRightKVPair());
                middleInternal.setRightKVPair(null);

                //Move the left child from the middle child to the middle of the
                //left child
                leftInternal.setMiddleChild(middleInternal.getLeftChild());
                middleInternal.setLeftChild(middleInternal.getMiddleChild());
                middleInternal.setMiddleChild(middleInternal.getRightChild());
                middleInternal.setRightChild(null);

                parent.getLeftChild().setMinPair(leftInternal.minPair());
                return;
            }

            //Merge the left child into the middle child and the old right child
            //becomes the middle child
            //Update the KVPairs in the internal nodes
            middleInternal.setRightKVPair(middleInternal.getLeftKVPair());
            middleInternal.setLeftKVPair(parent.getLeftKVPair());
            parent.setLeftKVPair(parent.getRightKVPair());
            parent.setRightKVPair(null);

            //Move the right child from the left child to the left child from
            //the middle child and move the others over
            middleInternal.setRightChild(middleInternal.getMiddleChild());
            middleInternal.setMiddleChild(middleInternal.getLeftChild());
            middleInternal.setLeftChild(leftInternal.getLeftChild());

            //Left child disappears
            parent.setLeftChild(middleInternal);
            parent.setMiddleChild(rightInternal);
            parent.setRightChild(null);

            parent.getLeftChild().setMinPair(leftInternal.minPair());

        }
        else if (underflowedChildPos == 2)
        {
            //Middle child can borrow from the left child
            if (leftInternal.getRightKVPair() != null)
            {
                //Update the KVPair pointers in the internal nodes
                middleInternal.setLeftKVPair(parent.getLeftKVPair());
                parent.setLeftKVPair(leftInternal.getRightKVPair());
                leftInternal.setRightKVPair(null);

                //Move the right child from the left child to the left child of
                //the middle child
                middleInternal.setMiddleChild(middleInternal.getLeftChild());
                middleInternal.setLeftChild(leftInternal.getRightChild());
                leftInternal.setRightChild(null);

                return;
            }


            //Middle child can borrow from the right child
            if (rightInternal != null && rightInternal.getRightKVPair() != null)
            {
                //Update the KVPair pointers in the internal nodes
                middleInternal.setLeftKVPair(parent.getRightKVPair());
                parent.setRightKVPair(rightInternal.getLeftKVPair());
                rightInternal.setLeftKVPair(rightInternal.getRightKVPair());
                rightInternal.setRightKVPair(null);

                //Move the left child from the right child to the middle child
                //of the middle child
                middleInternal.setMiddleChild(rightInternal.getLeftChild());
                rightInternal.setLeftChild(rightInternal.getMiddleChild());
                rightInternal.setMiddleChild(rightInternal.getRightChild());
                rightInternal.setRightChild(null);

                return;
            }

            //Middle child merges into the left child
            //Update the KVPairs in the internal nodes
            leftInternal.setRightKVPair(parent.getLeftKVPair());
            parent.setLeftKVPair(parent.getRightKVPair());
            parent.setRightKVPair(null);

            //Move the left child from the middle child into the right child of
            //the left child
            leftInternal.setRightChild(middleInternal.getLeftChild());

            //Middle child becomes what was the right child
            parent.setMiddleChild(rightInternal);
            parent.setRightChild(null);

        }
        else
        {
            //Right child can borrow from the middle child
            if (middleInternal.getRightKVPair() != null)
            {
                //Update the KVPair pointers in the internal nodes
                rightInternal.setLeftKVPair(parent.getRightKVPair());
                parent.setRightKVPair(middleInternal.getRightKVPair());
                middleInternal.setRightKVPair(null);

                //Move the right child from the left child to the left child of
                //the middle child
                rightInternal.setMiddleChild(rightInternal.getLeftChild());
                rightInternal.setLeftChild(middleInternal.getRightChild());
                middleInternal.setRightChild(null);

                return;
            }

            //Right child merges into the middle child

            //Update the KVPair pointers in the internal nodes
            middleInternal.setRightKVPair(parent.getRightKVPair());
            parent.setRightKVPair(null);

            //Move the left child from the right child to the right child of the
            //middle child
            middleInternal.setRightChild(rightInternal.getLeftChild());

            //Right child disappears
            parent.setRightChild(null);
        }
        if (parent.getMiddleChild() == null)
        {
            parent.setIsUnderflowed(true);
        }
        else
        {
            parent.setIsUnderflowed(false);
        }
    }
}
