CS 4104 Project 1 Phase 2:
Samuel Bahr
Maxwell Honosky
Anuj Pareek

Overview:
We created the IntersectingLines class as the main control flow of our program.
It starts by generating the random lines as described in the requirements.

Then it finds the intersections using our O(n^2) brute force algorithm,
followed by our proposed algorithm from phase 1 with some adjustments.
Our proposed algorithm utilizes a B+ tree (see Tree Implementation).

The files written exclusively for this project are:
-IntersectingLines (control flow)
-IntersectingLinesTest
-RandomLines (generates the list of random lines)
-BruteForceAlgorithm
-ProposedAlgorithm
-Event
-Line
	-VerticalLine
-Point
-Result

Tree Implementation:
We used Samuel's B+ Tree from 3114 for our tree implementation. That B+
implementation consists of the files:
-BPTree
-BNode
	-BInternalNode
	-BLeafNode
-DoublyLinkedList
	-Node
-Handle (only used for testing)
-KVPair

Since that B+ tree was hardcoded to use KVPairs, we modified KVPairs to be a
more generic wrapper that can holder either one or two objects as long as they
implement Comparable. In this way, we store VerticalLines in the BPTree wrapped
inside of KVPairs.

However, that B+ tree was originally designed to completely ignore duplicate 
values, so a field was added along with another constructor to accept duplicate
values. Unfortunately when duplicate values are allowed there are some weird
failures with the BPTree that can happen on some edge cases that make the 20000
line test fail about half of the time.

To prevent our program from failing half of the time, we only use the BPTree
that accepts duplicates if we find that there are actually duplicate points in
the results of the brute force. Note that we are only doing this for the sake of
stability due to the B+ tree implementation, and if we had a completely stable
B+ tree then we would always allow duplicates.

We have some test cases in IntersectingLinesTest to prove that our tree does in
fact report duplicate intersections when they occur.
