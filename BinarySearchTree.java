// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate




/*
	Hyungtaek Kim
	CS 3345 504
	Project 2
 */
 
 
 
import java.lang.*; 
import java.util.LinkedList;


public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x ) throws Exception
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )  throws Exception
    {
        if( isEmpty( ) )
            throw new Exception( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t ) throws Exception
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t ) throws Exception
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;
    
    
    //////////////////////////////////////////////////////
    
    // helper method
    private int nodeCount() {
        return nodeCount(root);
    }
    
    // travel all the nodes and count 1 for each using recursion
    private int nodeCount(BinaryNode<AnyType> t) {
        if (t == null) {  // base case
            return 0;
        } else {
            return (1 + nodeCount(t.left) + nodeCount(t.right));
        }
        
    }
    
    private boolean isFull(){
        return isFull(root);
    }
    
    private boolean isFull(BinaryNode<AnyType> t) {
        
        // base case
        if(t == null) {  
            return true; 
        }
        
        // if t is a leaf
        if((t.left == null) && (t.right == null)) {
            return true;
        }
       
        // if t is not a leaf and has both right and left child
        if((t.left != null) && (t.right != null)) {
            return (isFull(t.left) && isFull(t.right));
        }
        
        //return false if t has only 1 child
        return false;    
    }
    
    
    private boolean compareStructure(BinarySearchTree<AnyType> t2) {
        return compareStructure(root, t2.root);
    }
    
    private boolean compareStructure(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
        
        // case if t1 and t2 reached end of tree at same recursion period
        if(t1 == null && t2 == null) {
            return true;
        } 
        
        // case if only one tree reached end of tree
        if(t1 == null || t2 == null) {
            return false;
        }
        
        return (compareStructure(t1.left, t2.left) && compareStructure(t1.right, t2.right));
        
    }

    private boolean equals (BinarySearchTree<AnyType> t2) {
        return equals (root, t2.root);
    }
    
    private boolean equals (BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
               
        //case if t1 & t2 both reached end of tree
        if(t1 == null && t2 == null) {
            return true;
        } 
        
        // if they are not end of tree, compare if they have same vaule and move to next child recursively
        if(t1 != null && t2 != null) {
            return (t1.element == t2.element && 
                    equals(t1.left, t2.left) && equals(t1.right, t2.right));
        }

        // if they have different value or structure
        return false;
    }
    
    private BinarySearchTree<AnyType> copy(){
        return copy(root);
    }
    
    //helper method for copy
    private BinarySearchTree<AnyType> copy (BinaryNode<AnyType> t) {
        if(t == null)
            return null;
        
        //construct new Tree 
        BinarySearchTree<AnyType> t2 = new BinarySearchTree<>();
        
        //copy original tree to this new tree
        t2.root = copyNodes(t); 
                
        return t2;         
    }
    
    private BinaryNode<AnyType> copyNodes (BinaryNode<AnyType> t) {
        
        //base case
        if(t == null)
            return null;
        
        //copy elements and do it for recursively right and left
        BinaryNode<AnyType> newNode = new BinaryNode<>(t.element);
        newNode.left = copyNodes(t.left);
        newNode.right = copyNodes(t.right);
        return newNode;
    }
    
    private BinarySearchTree<AnyType> mirror() {
        return mirror(root);
    }
    
    //helper method that returns new mirrored tree
    private BinarySearchTree<AnyType> mirror (BinaryNode<AnyType> t) {
        if(t == null)
            return null;
        
        BinarySearchTree<AnyType> t2 = new BinarySearchTree<>();
        t2.root = copyMirror(t);
                
        return t2;         
    }
    
    private BinaryNode<AnyType> copyMirror (BinaryNode<AnyType> t) {
        if(t == null)
            return null;
        
        //same as copy, but flip the left and right
        BinaryNode<AnyType> newNode = new BinaryNode<>(t.element);
        newNode.left = copyMirror(t.right);
        newNode.right = copyMirror(t.left);
        return newNode;
    }
    
    private boolean isMirror(BinarySearchTree t2) {
        return isMirror(root, t2.root);
    }
    
    private boolean isMirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
        
        
        
        if(t1 == null && t2 == null)
            return true;
        
        if(t1 == null || t2 == null)
            return false;
        
        return (  // exactly same as compareStructure method, but flip the left and right
                (t1.element == t2.element) && isMirror(t1.left, t2.right) &&
                isMirror(t1.right, t2.left)
                );
    } 
    
    private void rotateRight() {
        rotateRight(root);
    }
    
    private void rotateRight(BinaryNode<AnyType> t) {
        if(t == null)
            return;
        
        if(t.left == null) 
            throw new NullPointerException();
        
        //switch the position of two nodes
        BinaryNode<AnyType> newRoot = t.left;
        BinaryNode<AnyType> transfer = newRoot.right;
        
        newRoot.right = root;
        root.left = transfer;
        
        // set a new root
        root = newRoot;
    }
    
    private void rotateLeft() {
        rotateLeft(root);
    }
    
    private void rotateLeft(BinaryNode<AnyType> t) {
        if(t == null)
            return;
        
        if(t.right == null) 
            throw new NullPointerException();
        
        //switch the position of two nodes
        BinaryNode<AnyType> newRoot = t.right;
        BinaryNode<AnyType> transfer = newRoot.left;
        
        newRoot.left = root;
        root.right = transfer;
        
        // set a new root
        root = newRoot;
    }
    
    private void printByLevel()
    {
        int h = height(root);
        
        //pass height as parameter and print node with given height
        for(int i = 1; i <= h+1; i++) {
            printByLevel(root, i);
            System.out.println();
        }
    }
    
     private void printByLevel(BinaryNode<AnyType> t, int height)
    {
        // if t is null
        if( t == null )
            return;
        
        //print elements recursively with given height
        if(height == 1){
            System.out.print(t.element + "  ");
        } else if(height > 1) {
            printByLevel(t.left, height-1);
            printByLevel(t.right, height-1);
        }
        
      
    }
    
    //////////////////////////////////////////////////////


        // Test program
    public static void main( String [ ] args ) throws Exception
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        
        final int NUMS = 4000;
        final int GAP  =   37;

        
        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree( );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }

        t.makeEmpty();
        System.out.println();
        ///////////// clear T /////////////////
        
        
        //construct new t
        t.insert(5);        
        t.insert(3);
        t.insert(8);
        t.insert(4);
        t.insert(1);
        
        System.out.println("T: " );
        t.printByLevel();
        
        
        //test nodeCount
        System.out.println("Node count for T: " + t.nodeCount());
        
        System.out.println("Test if T is full: " + t.isFull());
        
        t.insert(2);
        System.out.println("Test if T is full after insert 2: " + t.isFull());
        System.out.println();
        
        
        //construct t2 which has same structure as t
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
        t2.insert(10);        
        t2.insert(15);
        t2.insert(5);
        t2.insert(2);
        t2.insert(7);
        
    
        
        System.out.println("Test if t & t2 have same structure: " + t.compareStructure(t2));        
        t.remove(2);
        System.out.println("Test if t & t2 after removing 2 in t: " + t.compareStructure(t2));
        System.out.println();
        
        
        //test equal
        System.out.println("Test if t & t2 equal: " + t.equals(t2));
        
        //copy t to t3
        BinarySearchTree<Integer> t3 = t.copy();
        System.out.println("Test if t & t3 equal: " + t.equals(t3));
        System.out.println();
        
        // test mirror
        t3.makeEmpty();
        t3 = t.mirror();
        
        //display trees using print by level       
        System.out.println("DIsplay Trees by level: ");
        
        System.out.println("tree t:");
        t.printByLevel();
        System.out.println();
        System.out.println("tree t3: ");  
        t3.printByLevel();
        System.out.println();
        
        //test isMirror
        System.out.println("Does t3 mirror or t?: " + t3.isMirror(t));
        System.out.println("Does t2 mirror or t?: " + t2.isMirror(t));
        
        //rotate Right
        System.out.println("T rotate right: ");  
        t.rotateRight();
        t.printByLevel();        
        System.out.println();
        t.printTree();
        
        //rotate Left
        System.out.println("T rotate Left: ");  
        t.rotateLeft();
        t.printByLevel();        
        System.out.println();
        t.printTree();
        
        //test print by level = tested at all previous tests
        
    }
}
