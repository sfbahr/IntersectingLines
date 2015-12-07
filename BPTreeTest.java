import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import student.TestCase;

/**
 * This method tests the methods implemented in the
 * BPTree class.
 *
 * @author karl88
 * @author sfbahr
 * @version Oct 18, 2014
 */
public class BPTreeTest extends TestCase
{
    private BPTree tree;

    /**
     * This methods sets up the BPtree for testing.
     */
    public void setUp()
    {
        tree = new BPTree();
        systemOut().clearHistory();
    }

    /**
     * This method tests the insert method.
     *
     * @throws FileNotFoundException for when the file DNE
     */
    public void testInsert() throws FileNotFoundException
    {
        tree.insert(new KVPair(new Handle(0), new Handle(5)));
        tree.print();
        assertTrue(systemOut().getHistory().endsWith("0 5\n"));
        systemOut().clearHistory();

        //checks that a duplicate is not inserted again
        tree.insert(new KVPair(new Handle(0), new Handle(5)));
        tree.print();
        assertEquals("Printing 2-3 tree:\n0 5\n",
                systemOut().getHistory());
        systemOut().clearHistory();

        tree.insert(new KVPair(new Handle(9), new Handle(14)));
        tree.insert(new KVPair(new Handle(9), new Handle(14)));
        tree.insert(new KVPair(new Handle(0), new Handle(5)));
        tree.print();
        assertEquals("Printing 2-3 tree:\n0 5 9 14\n",
                systemOut().getHistory());
        systemOut().clearHistory();

        Scanner scanner = new Scanner(new File("testTreeInsert1Output.txt"));
        String correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree = new BPTree();
        tree.insert(new KVPair(new Handle(0), new Handle(5)));
        tree.insert(new KVPair(new Handle(1), new Handle(7)));
        tree.insert(new KVPair(new Handle(2), new Handle(7)));
        tree.insert(new KVPair(new Handle(3), new Handle(7)));
        systemOut().clearHistory();
        tree.print();
        assertEquals(systemOut().getHistory(), correct);


        scanner = new Scanner(new File("testTreeInsert2Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree = new BPTree();
        tree.insert(new KVPair(new Handle(3), new Handle(7)));
        tree.insert(new KVPair(new Handle(2), new Handle(7)));
        tree.insert(new KVPair(new Handle(1), new Handle(7)));
        tree.insert(new KVPair(new Handle(0), new Handle(7)));
        systemOut().clearHistory();
        tree.print();
        assertEquals(systemOut().getHistory(), correct);

        scanner = new Scanner(new File("testTreeInsert3Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree = new BPTree();
        tree.insert(new KVPair(new Handle(0), new Handle(1)));
        tree.insert(new KVPair(new Handle(0), new Handle(2)));
        tree.insert(new KVPair(new Handle(1), new Handle(1)));
        tree.insert(new KVPair(new Handle(0), new Handle(4)));
        systemOut().clearHistory();
        tree.print();
        assertEquals(systemOut().getHistory(), correct);

        scanner = new Scanner(new File("testTreeInsert4Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree = new BPTree();
        tree.insert(new KVPair(new Handle(9), new Handle(0)));
        tree.insert(new KVPair(new Handle(67), new Handle(0)));
        tree.insert(new KVPair(new Handle(34), new Handle(0)));
        tree.insert(new KVPair(new Handle(12), new Handle(0)));
        tree.insert(new KVPair(new Handle(35), new Handle(0)));
        tree.insert(new KVPair(new Handle(7), new Handle(0)));
        tree.insert(new KVPair(new Handle(1000), new Handle(0)));
        tree.insert(new KVPair(new Handle(1), new Handle(0)));
        tree.insert(new KVPair(new Handle(123), new Handle(0)));
        tree.insert(new KVPair(new Handle(149), new Handle(0)));
        tree.insert(new KVPair(new Handle(56), new Handle(0)));
        tree.insert(new KVPair(new Handle(14), new Handle(0)));
        tree.insert(new KVPair(new Handle(20), new Handle(0)));
        systemOut().clearHistory();
        tree.print();
        assertEquals(systemOut().getHistory(), correct);


        scanner = new Scanner(new File("testTreeInsert5Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree = new BPTree();
        tree.insert(new KVPair(new Handle(9), new Handle(15)));
        tree.insert(new KVPair(new Handle(15), new Handle(9)));
        tree.insert(new KVPair(new Handle(17), new Handle(9)));
        tree.insert(new KVPair(new Handle(23), new Handle(15)));
        tree.insert(new KVPair(new Handle(9), new Handle(0)));
        tree.insert(new KVPair(new Handle(15), new Handle(0)));
        systemOut().clearHistory();
        tree.print();
        assertEquals(systemOut().getHistory(), correct);
    }


    /**
     * This method tests the delete method from the BPTree class.
     * It uses various combinations of deletions and insertions to
     * ensure that the delete method is fully functional.
     *
     * @throws FileNotFoundException when the file DNE
     */
    public void testDelete() throws FileNotFoundException
    {
        KVPair pair1 = new KVPair(new Handle(1), new Handle(0));
        KVPair pair2 = new KVPair(new Handle(2), new Handle(0));
        KVPair pair3 = new KVPair(new Handle(3), new Handle(0));
        KVPair pair4 = new KVPair(new Handle(4), new Handle(0));
        KVPair pair5 = new KVPair(new Handle(5), new Handle(0));
        KVPair pair6 = new KVPair(new Handle(6), new Handle(0));
        KVPair pair7 = new KVPair(new Handle(7), new Handle(0));
        KVPair pair8 = new KVPair(new Handle(8), new Handle(0));
        KVPair pair9 = new KVPair(new Handle(9), new Handle(0));
        KVPair pair10 = new KVPair(new Handle(10), new Handle(0));

        tree.insert(pair1);
        tree.delete(new KVPair(new Handle(1), new Handle(5)));
        tree.print();
        assertEquals(systemOut().getHistory(), "Printing 2-3 tree:\n1 0\n");
        systemOut().clearHistory();

        tree.delete(new KVPair(new Handle(5), new Handle(0)));
        tree.print();
        assertEquals(systemOut().getHistory(), "Printing 2-3 tree:\n1 0\n");
        systemOut().clearHistory();

        tree.delete(new KVPair(new Handle(1), new Handle(0)));
        tree.print();
        assertEquals(systemOut().getHistory(), "Printing 2-3 tree:\n");
        systemOut().clearHistory();

        //Deletion of one element of a BPTree that is a leaf
        tree.insert(pair1);
        tree.insert(pair2);
        tree.delete(pair2);
        tree.print();
        assertEquals(systemOut().getHistory(), "Printing 2-3 tree:\n1 0\n");
        systemOut().clearHistory();

        //Delete a KVPair that isn't in the tree (nothing should happen)
        tree.delete(pair2);
        tree.print();
        assertEquals(systemOut().getHistory(), "Printing 2-3 tree:\n1 0\n");
        systemOut().clearHistory();

        //Deletion of the left element of a BPTree that is a leaf
        tree.insert(pair2);
        tree.delete(pair1);
        tree.print();
        assertEquals(systemOut().getHistory(), "Printing 2-3 tree:\n2 0\n");
        systemOut().clearHistory();

        //Left leaf borrow from middle leaf
        tree.insert(pair3);
        tree.insert(pair4);
        tree.delete(pair2);
        tree.print();
        assertEquals(systemOut().getHistory(),
            "Printing 2-3 tree:\n4 0\n  3 0\n  4 0\n");
        systemOut().clearHistory();

        //Left leaf borrow from middle leaf and update ancestor
        Scanner scanner = new Scanner(new File("testTreeDelete1Output.txt"));
        String correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair5);
        tree.insert(pair6);
        tree.insert(pair7);
        tree.delete(pair5);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Merge left leaf into middle leaf and update ancestor
        scanner = new Scanner(new File("testTreeDelete2Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair8);
        tree.insert(pair9);
        tree.delete(pair6);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Middle leaf borrow from right leaf
        scanner = new Scanner(new File("testTreeDelete3Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair6);
        tree.insert(pair10);
        tree.delete(pair8);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Right leaf merge into middle
        scanner = new Scanner(new File("testTreeDelete4Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.delete(pair10);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Miscellaneous leaf-borrowing & simple deletion, updating ancestors
        scanner = new Scanner(new File("testTreeDelete5Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair5);
        tree.insert(pair8);
        tree.insert(pair10);
        tree.delete(pair4);
        tree.delete(pair3);
        tree.delete(pair9);
        tree.delete(pair7);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Full deletion of the tree
        scanner = new Scanner(new File("testTreeDelete6Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.delete(pair5);
        tree.delete(pair8);
        tree.delete(pair1);
        tree.delete(pair10);
        tree.delete(pair2);
        tree.delete(pair6);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Delete a KVPair from an empty tree
        scanner = new Scanner(new File("testTreeDelete6Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.delete(pair10);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Cause an underflow at the root, root is internal
        scanner = new Scanner(new File("testTreeDelete8Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair3);
        tree.insert(pair4);
        tree.insert(pair6);
        tree.insert(pair7);
        tree.delete(pair4);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Delete a left KVPair in a leaf node
        scanner = new Scanner(new File("testTreeDelete9Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair1);
        tree.delete(pair1);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Delete a right KVPair in a leaf node
        scanner = new Scanner(new File("testTreeDelete10Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair1);
        tree.delete(pair3);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Delete a pair in a left child
        scanner = new Scanner(new File("testTreeDelete11Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair1);
        tree.delete(pair3);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Cause an underflow in a left leaf
        scanner = new Scanner(new File("testTreeDelete12Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.delete(pair1);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Cause an underflow in a right leaf
        //Make a middleLeaf merge where a rightLeaf doesn't exist
        scanner = new Scanner(new File("testTreeDelete13Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair1);
        tree.insert(pair2);
        tree.delete(pair7);
        tree.delete(pair6);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Make a not leftmost leaf merge with the middle leaf of its parent
        scanner = new Scanner(new File("testTreeDelete14Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair6);
        tree.insert(pair3);
        tree.insert(pair4);
        tree.insert(pair5);
        tree.delete(pair4);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //Cause a chain of merges that updates an ancestor
        scanner = new Scanner(new File("testTreeDelete7Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair3);
        tree.insert(pair4);
        tree.insert(pair5);
        tree.insert(pair6);
        tree.insert(pair7);
        tree.insert(pair8);
        tree.insert(pair9);
        tree.delete(pair5);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //the test machine, yeah I broke that code
        scanner = new Scanner(new File("testTreeDelete15Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree.insert(pair5);
        tree.insert(pair10);
        tree.delete(pair6);
        tree.delete(pair5);
        tree.delete(pair4);
        tree.delete(pair7);
        tree.delete(pair3);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();

        //the test machine2, yeah I broke that code
        scanner = new Scanner(new File("testTreeDelete16Output.txt"));
        correct = scanner.useDelimiter("\\Z").next() + "\n";
        scanner.close();
        correct = correct.replaceAll("\\r", "");
        tree = new BPTree();
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair3);
        tree.insert(pair6);
        tree.insert(pair7);
        tree.insert(pair8);
        tree.insert(pair9);
        tree.insert(pair4);
        tree.insert(pair5);
        tree.delete(pair8);
        tree.delete(pair9);
        tree.print();
        assertEquals(correct, systemOut().getHistory());
        systemOut().clearHistory();
    }
    
    public void testFindRange()
    {
    	tree.insertWithDupes(new KVPair<VerticalLine>(new VerticalLine(new Point(5, 10), new Point(5, 35), true)));
    	tree.insertWithDupes(new KVPair<VerticalLine>(new VerticalLine(new Point(8, 10), new Point(8, 35), true)));
    	tree.insertWithDupes(new KVPair<VerticalLine>(new VerticalLine(new Point(3, 10), new Point(3, 35), true)));
    	tree.insertWithDupes(new KVPair<VerticalLine>(new VerticalLine(new Point(15, 10), new Point(15, 35), true)));
    	tree.insertWithDupes(new KVPair<VerticalLine>(new VerticalLine(new Point(17, 10), new Point(17, 35), true)));
    	tree.insertWithDupes(new KVPair<VerticalLine>(new VerticalLine(new Point(9, 10), new Point(9, 35), true)));
    	tree.insertWithDupes(new KVPair<VerticalLine>(new VerticalLine(new Point(9, 11), new Point(9, 36), true)));
    	tree.insertWithDupes(new KVPair<VerticalLine>(new VerticalLine(new Point(6, 10), new Point(6, 35), true)));
    	tree.insertWithDupes(new KVPair<VerticalLine>(new VerticalLine(new Point(6, 10), new Point(6, 35), true)));
    	tree.print();    	
    	
    	List<Point> intersections = tree.findRange(5, 15, 20);
    	assertTrue(intersections.contains(new Point(5, 20)));
    	assertTrue(intersections.contains(new Point(6, 20)));
    	assertTrue(intersections.contains(new Point(8, 20)));
    	assertTrue(intersections.contains(new Point(9, 20)));
    	assertTrue(intersections.contains(new Point(15, 20)));
    	assertEquals(intersections.size(), 7);
    }
}

