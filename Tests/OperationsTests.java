import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OperationsTests {

    /*
        !!! IMPORTANT !!!
        In each graphical representation of the trees a single slash (/) means
        Black link and a double slash (//) means Red link.
    */

    @Test
    void insert_emptyRoot_realRoot(){
        //Arrange, Act
        Node root = null;
        Node expected = new RBTNode(5);

        //Act
        root = Operations.insert(root, 5);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
            |               |
            5       =>      5
                          //
                          3
     */
    @Test
    void insert_insertSmallerNode_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5);
        Node expected = new RBTNode(5, null, Color.BLACK);
        expected.setLeft(new RBTNode(3, expected, Color.RED));

        //Act
        root = Operations.insert(root, 3);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }


    /*
            |               |
            5       =>      5
                             \\
                               7
     */
    @Test
    void insert_insertBiggerNode_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5, null, Color.BLACK);
        Node expected = new RBTNode(5, null, Color.BLACK);
        expected.setRight(new RBTNode(7, expected, Color.RED));

        //Act
        root = Operations.insert(root, 7);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }


    /*
         |                |
         5       =>       3
        //              //  \\
       3               2      5
    */
    //Left-left case
    @Test
    void insert_leftLeftCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5);
        root.setLeft(new RBTNode(3, root, Color.RED));

        Node expected = new RBTNode(3);
        expected.setLeft(new RBTNode(2, expected, Color.RED));
        expected.setRight(new RBTNode(5, expected, Color.RED));

        //Act
        root = Operations.insert(root, 2);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }


    /*
         |                |
         5       =>       4
        //              //  \\
       3               3      5
    */
    //Left-right case
    @Test
    void insert_leftRightCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5, null, Color.BLACK);
        root.setLeft(new RBTNode(3, root, Color.RED));

        Node expected = new RBTNode(4, null, Color.BLACK);
        expected.setLeft(new RBTNode(3, expected, Color.RED));
        expected.setRight(new RBTNode(5, expected, Color.RED));

        //Act
        root = Operations.insert(root, 4);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }


    /*
         |                   |
         5       =>          10
          \\               //  \\
           10             5      20
    */
    //Right-right case
    @Test
    void insert_rightRightCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5, null, Color.BLACK);
        root.setRight(new RBTNode(10, root, Color.RED));

        Node expected = new RBTNode(10, null, Color.BLACK);
        expected.setLeft(new RBTNode(5, expected, Color.RED));
        expected.setRight(new RBTNode(20, expected, Color.RED));

        //Act
        root = Operations.insert(root, 20);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
         |                   |
         5       =>          7
          \\               //  \\
           10             5      10
    */
    //Right-left case
    @Test
    void insert_rightLeftCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5, null, Color.BLACK);
        root.setRight(new RBTNode(10, root, Color.RED));

        Node expected = new RBTNode(7, null, Color.BLACK);
        expected.setLeft(new RBTNode(5, expected, Color.RED));
        expected.setRight(new RBTNode(10, expected, Color.RED));

        //Act
        root = Operations.insert(root, 7);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }


    /*
         |                   |
         5       =>          5
       //  \\               /  \
       3    10             3    10
                          //
                          2
    */
    //Red uncle case
    @Test
    void insert_redUncleCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5, null, Color.BLACK);
        root.setLeft(new RBTNode(3, root, Color.RED));
        root.setRight(new RBTNode(10, root, Color.RED));

        Node expected = new RBTNode(5, null, Color.BLACK);
        expected.setLeft(new RBTNode(3, expected, Color.BLACK));
        expected.setRight(new RBTNode(10, expected, Color.BLACK));
        expected.getLeft().setLeft(new RBTNode(2, expected.getLeft(), Color.RED));

        //Act
        root = Operations.insert(root, 2);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
                  |
                  44
           /              \
          34                55
         /  \           /       \\
       20    39        45        60
           //  \\               /   \
          35    43            57     68
                            //  \\     \\
                           56    59     70
    */
    @Test
    public void insert_createTreeFromBeginning_expectsToGoThroughAllCases() {
        // Arrange
        Node root = null;
        Node expectedRoot = getTreeRoot();

        // Act
        root = Operations.insert(root, 20);
        root = Operations.insert(root, 34);
        root = Operations.insert(root, 55);
        root = Operations.insert(root, 44);
        root = Operations.insert(root, 60);
        root = Operations.insert(root, 35);
        root = Operations.insert(root, 45);
        root = Operations.insert(root, 59);
        root = Operations.insert(root, 68);
        root = Operations.insert(root, 39);
        root = Operations.insert(root, 70);
        root = Operations.insert(root, 43);
        root = Operations.insert(root, 56);
        root = Operations.insert(root, 57);

        // Assert
        assertTrue(TreeComparator.equalTrees(root, expectedRoot));
    }

    /*
         |                 |
         30       =>       30
        /  \              /  \
       20   40           10   40
      //
     10
    */
    //Simple case - child node is red
    @Test
    void delete_simpleCaseChildNodeIsRed_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(30, null, Color.BLACK);
        root.setLeft(new RBTNode(20, root, Color.BLACK));
        root.setRight(new RBTNode(40, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(10, root.getLeft(), Color.RED));

        Node expected = new RBTNode(30, null, Color.BLACK);
        expected.setLeft(new RBTNode(10, expected, Color.BLACK));
        expected.setRight(new RBTNode(40, expected, Color.BLACK));

        //Act
        root = Operations.delete(root, 20);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
         |                 |
         30       =>       30
        /  \              /  \
       20   40           20   40
      //
     10
    */
    //Simple case - node is red
    @Test
    void delete_simpleCaseNodeIsRed_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(30, null, Color.BLACK);
        root.setLeft(new RBTNode(20, root, Color.BLACK));
        root.setRight(new RBTNode(40, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(10, root.getLeft(), Color.RED));

        Node expected = new RBTNode(30, null, Color.BLACK);
        expected.setLeft(new RBTNode(20, expected, Color.BLACK));
        expected.setRight(new RBTNode(40, expected, Color.BLACK));

        //Act
        root = Operations.delete(root, 10);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
         |                 |
         30       =>       20
        /  \              /  \
       20   40           10   30
      //
     10
    */
    //Double black node - Left-left Case
    @Test
    void delete_doubleBlackNodeLeftLeftCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(30, null, Color.BLACK);
        root.setLeft(new RBTNode(20, root, Color.BLACK));
        root.setRight(new RBTNode(40, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(10, root.getLeft(), Color.RED));

        Node expected = new RBTNode(20, null, Color.BLACK);
        expected.setLeft(new RBTNode(10, expected, Color.BLACK));
        expected.setRight(new RBTNode(30, expected, Color.BLACK));

        //Act
        root = Operations.delete(root, 40);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
         |                 |
         30       =>       25
        /  \              /  \
       20   40           20   30
        \\
         25
    */
    //Double black node - Left-right Case
    @Test
    void delete_doubleBlackNodeLeftRightCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(30, null, Color.BLACK);
        root.setLeft(new RBTNode(20, root, Color.BLACK));
        root.setRight(new RBTNode(40, root, Color.BLACK));
        root.getLeft().setRight(new RBTNode(25, root.getLeft(), Color.RED));

        Node expected = new RBTNode(25, null, Color.BLACK);
        expected.setLeft(new RBTNode(20, expected, Color.BLACK));
        expected.setRight(new RBTNode(30, expected, Color.BLACK));

        //Act
        root = Operations.delete(root, 40);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
         |                 |
         30       =>       40
        /  \              /  \
       20   40           30   50
              \\
               50
    */
    //Double black node - Right-right Case
    @Test
    void delete_doubleBlackNodeRightRightCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(30, null, Color.BLACK);
        root.setLeft(new RBTNode(20, root, Color.BLACK));
        root.setRight(new RBTNode(40, root, Color.BLACK));
        root.getRight().setRight(new RBTNode(50, root.getRight(), Color.RED));

        Node expected = new RBTNode(40, null, Color.BLACK);
        expected.setLeft(new RBTNode(30, expected, Color.BLACK));
        expected.setRight(new RBTNode(50, expected, Color.BLACK));

        //Act
        root = Operations.delete(root, 20);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
         |                 |
         30       =>       35
        /  \              /  \
       20   40           30   40
          //
         35
    */
    //Double black node - Right-left Case
    @Test
    void delete_doubleBlackNodeRightLeftCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(30, null, Color.BLACK);
        root.setLeft(new RBTNode(20, root, Color.BLACK));
        root.setRight(new RBTNode(40, root, Color.BLACK));
        root.getRight().setLeft(new RBTNode(35, root.getRight(), Color.RED));

        Node expected = new RBTNode(35, null, Color.BLACK);
        expected.setLeft(new RBTNode(30, expected, Color.BLACK));
        expected.setRight(new RBTNode(40, expected, Color.BLACK));

        //Act
        root = Operations.delete(root, 20);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
         |                 |
         20       =>       10
       //  \              /  \
      10   30            5    20
     /  \                    //
    5    15                 15
    */
    //Red sibling node - Left Case
    @Test
    void delete_redSiblingNodeLeftCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(20, null, Color.BLACK);
        root.setLeft(new RBTNode(10, root, Color.RED));
        root.setRight(new RBTNode(30, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(5, root.getLeft(), Color.BLACK));
        root.getLeft().setRight(new RBTNode(15, root.getLeft(), Color.BLACK));

        Node expected = new RBTNode(10, null, Color.BLACK);
        expected.setLeft(new RBTNode(5, expected, Color.BLACK));
        expected.setRight(new RBTNode(20, expected, Color.BLACK));
        expected.getRight().setLeft(new RBTNode(15, expected.getRight(), Color.RED));

        //Act
        root = Operations.delete(root, 30);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }

    /*
         |                   |
         20       =>         30
       /   \\              /    \
      10    30            20     35
           /  \            \\
          25   35           25
    */
    //Red sibling node - Right Case
    @Test
    void delete_redSiblingNodeRightCase_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(20, null, Color.BLACK);
        root.setLeft(new RBTNode(10, root, Color.BLACK));
        root.setRight(new RBTNode(30, root, Color.RED));
        root.getRight().setLeft(new RBTNode(25, root.getRight(), Color.BLACK));
        root.getRight().setRight(new RBTNode(35, root.getRight(), Color.BLACK));

        Node expected = new RBTNode(30, null, Color.BLACK);
        expected.setLeft(new RBTNode(20, expected, Color.BLACK));
        expected.setRight(new RBTNode(35, expected, Color.BLACK));
        expected.getLeft().setRight(new RBTNode(25, expected.getLeft(), Color.RED));

        //Act
        root = Operations.delete(root, 10);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }


    /*
                    |                                        |
                    44                                       39
             /              \                              /    \\
            34                55                          36      57
           /  \           /       \\         =>          //      /  \
         20    39        45        60                    35     55   59
             //  \\               /   \                          \\
            35    43            57     68                         56
                              //  \\     \\
                             56    59     70
      */
    @Test
    public void delete_treeRootAndValueToBeRemoved_treeRootWithoutTheRemovedValue() {
        // Arrange
        Node root = getTreeRoot();
        Node expectedRoot = getExpectedTreeRootForRemove();

        //Act
        root = Operations.delete(root, 20);
        root = Operations.delete(root, 60);
        root = Operations.delete(root, 68);
        root = Operations.delete(root, 70);
        root = Operations.delete(root, 45);
        root = Operations.delete(root, 43);
        root = Operations.insert(root, 36);
        root = Operations.delete(root, 34);
        root = Operations.delete(root, 44);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expectedRoot));
    }



    private Node getTreeRoot(){
        Node root = new RBTNode(44, null, Color.BLACK);
        root.setLeft(new RBTNode(34, root, Color.BLACK));
        root.setRight(new RBTNode(55, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(20, root.getLeft(), Color.BLACK));
        root.getLeft().setRight(new RBTNode(39, root.getLeft(), Color.BLACK));
        root.getLeft().getRight().setLeft(new RBTNode(35, root.getLeft().getRight(), Color.RED));
        root.getLeft().getRight().setRight(new RBTNode(43, root.getLeft().getRight(), Color.RED));
        root.getRight().setLeft(new RBTNode(45, root.getRight(), Color.BLACK));
        root.getRight().setRight(new RBTNode(60, root.getRight(), Color.RED));
        root.getRight().getRight().setLeft(new RBTNode(57, root.getRight().getRight(), Color.BLACK));
        root.getRight().getRight().setRight(new RBTNode(68, root.getRight().getRight(), Color.BLACK));
        root.getRight().getRight().getRight().setRight(new RBTNode(70, root.getRight().getRight().getRight(), Color.RED));
        root.getRight().getRight().getLeft().setLeft(new RBTNode(56, root.getRight().getRight().getLeft(), Color.RED));
        root.getRight().getRight().getLeft().setRight(new RBTNode(59, root.getRight().getRight().getLeft(), Color.RED));
        return root;
    }

    private Node getExpectedTreeRootForRemove(){
        Node root = new RBTNode(39, null, Color.BLACK);
        root.setLeft(new RBTNode(36, root, Color.BLACK));
        root.setRight(new RBTNode(57, root, Color.RED));
        root.getLeft().setLeft(new RBTNode(35, root.getLeft(), Color.RED));
        root.getRight().setLeft(new RBTNode(55, root.getRight(), Color.BLACK));
        root.getRight().setRight(new RBTNode(59, root.getRight(), Color.BLACK));
        root.getRight().getLeft().setRight(new RBTNode(56, root.getRight().getLeft(), Color.RED));
        return root;
    }

}
