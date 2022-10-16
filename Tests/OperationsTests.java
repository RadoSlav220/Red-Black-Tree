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
        Node expected = new RBTNode(5, null, Color.BLACK);

        //Act
        root = Operations.insert(null, 5);

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
    void insert_insertSecondSmallerNode_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5, null, Color.BLACK);
        Node expected = new RBTNode(5, null, Color.BLACK);
        expected.setLeft(new RBTNode(3, expected, Color.RED));

        //Act
        root = Operations.insert(root, 3);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }


    /*
            |               |
            5       =>      7
                          //
                          5
     */
    @Test
    void insert_insertSecondBiggerNode_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5, null, Color.BLACK);
        Node expected = new RBTNode(7, null, Color.BLACK);
        expected.setLeft(new RBTNode(5, expected, Color.RED));

        //Act
        root = Operations.insert(root, 7);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }


    /*
         |                |
         5       =>       3
        //              /  \
       3               2    5
    */
    @Test
    void insert_insertThirdSmallestNode_wellOrderedTree(){
        //Arrange
        Node root = new RBTNode(5, null, Color.BLACK);
        root.setLeft(new RBTNode(3, root, Color.RED));

        Node expected = new RBTNode(3, null, Color.BLACK);
        expected.setLeft(new RBTNode(2, expected, Color.BLACK));
        expected.setRight(new RBTNode(5, expected, Color.BLACK));

        //Act
        root = Operations.insert(root, 2);

        //Assert
        assertTrue(TreeComparator.equalTrees(root, expected));
    }


    /*
                |                               |
                16                             16
           /         \                   /            \
          8           28       =>       8              28
         /  \     //      \           //  \        //      \
        4    12   24       36        4     12     24        36
       //        /  \     //        /  \         /  \      //
       2        18   26  32        3    6       18   26   32
                                  //   //
                                  2    5
    */
    @Test
    public void insert_valuesInTree_expectsValues() {
        // Arrange
        Node root = getTreeRoot();
        Node expectedRoot = getExpectedTreeRootForInsert();

        // Act
        Operations.insert(root, 5);
        Operations.insert(root, 6);
        Operations.insert(root, 3);

        // Assert
        assertTrue(TreeComparator.equalTrees(root, expectedRoot));
    }
    /*
        !!! IMPORTANT !!!
        In each graphical representation of the trees a single slash (/) means
        Black link and a double slash (//) means Red link.
    */


    /*
                |                                  |
                16                                 16
           /         \                    /                  \
          8           28       =>        8                    28
         /  \     //      \           /    \              /       \
        4    12   24       36        4      12           24        36
       //        /  \     //       //  \   /   \        /  \      /   \
       2        18   26  32        2    5  10   13     18   26   32    40
                                 /   \
                                 1    3
    */

    @Test
    public void insert_manyValuesInTree_expectsToPerformEveryRotationAndKeepTreeBalanced() {
        // Arrange
        Node root = getTreeRoot();
        Node expectedRoot = getExpectedTreeRootForSecondInsert();

        // Act
        Operations.insert(root, 13);
        Operations.insert(root, 10);
        Operations.insert(root, 1);
        Operations.insert(root, 3);
        Operations.insert(root, 5);
        Operations.insert(root, 40);

        // Assert
        assertTrue(TreeComparator.equalTrees(root, expectedRoot));
    }


    /*
                |                             |
                16                            16
           /         \                   /          \
          8           28       =>       8            28
         /  \     //      \           /  \      //      \
        4    12   24       36        4     12   24        36
       //        /  \     //       //          /  \
       2        18   26  32        2          18   26

    */
    @Test
    public void delete_treeRootAndValueToBeRemoved_treeRootWithoutTheRemovedValue() {
        // Arrange
        Node root = getTreeRoot();
        Node expectedRoot = getExpectedTreeRootForRemove();

        //Act, Assert
        assertEquals(32, Operations.delete(root, 32).getValue());
        assertEquals(null, Operations.delete(root, 100));

        assertTrue(TreeComparator.equalTrees(root, expectedRoot));
    }


    /*
        !!! IMPORTANT !!!
        In each graphical representation of the trees a single slash (/) means
        Black link and a double slash (//) means Red link.
    */


    /*
                |                             |
                16                            12
           /         \                   /          \
          8           28       =>       4            28
         /  \     //      \            /  \      //      \
        4    12   24       36        2     8     24        36
       //        /  \     //                    /  \      //
       2        18   26  32                    18   26    32

    */
    // Left-left case
    @Test
    public void delete_treeRootToBeRemoved_treeRootWithoutTheRemovedValue() {
        // Arrange
        Node root = getTreeRoot();
        Node expectedRoot = getExpectedTreeRootForRemovingRoot();

        //Act, Assert
        assertEquals(16, Operations.delete(root, 16).getValue());

        assertTrue(TreeComparator.equalTrees(root, expectedRoot));
    }

    private Node getTreeRoot(){
        Node root = new RBTNode(16, null);
        root.setLeft(new RBTNode(8, root, Color.BLACK));
        root.setRight(new RBTNode(28, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(4, root.getLeft(), Color.BLACK));
        root.getLeft().setRight(new RBTNode(12, root.getLeft(), Color.BLACK));
        root.getLeft().getLeft().setLeft(new RBTNode(2, root.getLeft().getLeft(), Color.RED));
        root.getRight().setLeft(new RBTNode(24, root.getRight(), Color.RED));
        root.getRight().setRight(new RBTNode(36, root.getRight(), Color.BLACK));
        root.getRight().getLeft().setLeft(new RBTNode(18, root.getRight().getLeft(), Color.BLACK));
        root.getRight().getLeft().setRight(new RBTNode(26, root.getRight().getLeft(), Color.BLACK));
        root.getRight().getRight().setLeft(new RBTNode(32, root.getRight().getRight(), Color.RED));
        return root;
    }

    private Node getExpectedTreeRootForInsert(){
        Node root = new RBTNode(16, null, Color.BLACK);
        root.setLeft(new RBTNode(8, root, Color.BLACK));
        root.setRight(new RBTNode(28, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(4, root.getLeft(), Color.RED));
        root.getLeft().setRight(new RBTNode(12, root.getLeft(), Color.BLACK));
        root.getLeft().getLeft().setLeft(new RBTNode(3, root.getLeft().getLeft(), Color.BLACK));
        root.getLeft().getLeft().getLeft().setLeft(new RBTNode(2, root.getLeft().getLeft().getLeft(), Color.RED));
        root.getLeft().getLeft().setRight(new RBTNode(6, root.getLeft().getLeft(), Color.BLACK));
        root.getLeft().getLeft().getRight().setLeft(new RBTNode(5, root.getLeft().getLeft().getRight(), Color.RED));
        root.getRight().setLeft(new RBTNode(24, root.getRight(), Color.RED));
        root.getRight().setRight(new RBTNode(36, root.getRight(), Color.BLACK));
        root.getRight().getLeft().setLeft(new RBTNode(18, root.getRight().getLeft(), Color.BLACK));
        root.getRight().getLeft().setRight(new RBTNode(26, root.getRight().getLeft(), Color.BLACK));
        root.getRight().getRight().setLeft(new RBTNode(32, root.getRight().getRight(), Color.RED));
        return root;
    }

    private Node getExpectedTreeRootForSecondInsert(){
        Node root = new RBTNode(16, null, Color.BLACK);
        root.setLeft(new RBTNode(8, root, Color.BLACK));
        root.setRight(new RBTNode(28, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(4, root.getLeft(), Color.BLACK));
        root.getLeft().setRight(new RBTNode(12, root.getLeft(), Color.BLACK));
        root.getLeft().getLeft().setLeft(new RBTNode(2, root.getLeft().getLeft(), Color.RED));
        root.getLeft().getLeft().getLeft().setLeft(new RBTNode(1, root.getLeft().getLeft().getLeft(), Color.BLACK));
        root.getLeft().getLeft().getLeft().setRight(new RBTNode(3, root.getLeft().getLeft().getLeft(), Color.BLACK));
        root.getLeft().getLeft().setRight(new RBTNode(5, root.getLeft().getLeft(), Color.BLACK));
        root.getLeft().getRight().setLeft(new RBTNode(10, root.getLeft().getRight(), Color.BLACK));
        root.getLeft().getRight().setRight(new RBTNode(13, root.getLeft().getRight(), Color.BLACK));
        root.getRight().setLeft(new RBTNode(24, root.getRight(), Color.BLACK));
        root.getRight().setRight(new RBTNode(36, root.getRight(), Color.BLACK));
        root.getRight().getLeft().setLeft(new RBTNode(18, root.getRight().getLeft(), Color.BLACK));
        root.getRight().getLeft().setRight(new RBTNode(26, root.getRight().getLeft(), Color.BLACK));
        root.getRight().getRight().setLeft(new RBTNode(32, root.getRight().getRight(), Color.BLACK));
        root.getRight().getRight().setRight(new RBTNode(40, root.getRight().getRight(), Color.BLACK));
        return root;
    }

    private Node getExpectedTreeRootForRemove(){
        Node root = new RBTNode(16, null);
        root.setLeft(new RBTNode(8, root, Color.BLACK));
        root.setRight(new RBTNode(28, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(4, root.getLeft(), Color.BLACK));
        root.getLeft().setRight(new RBTNode(12, root.getLeft(), Color.BLACK));
        root.getLeft().getLeft().setLeft(new RBTNode(2, root.getLeft().getLeft(), Color.RED));
        root.getRight().setLeft(new RBTNode(24, root.getRight(), Color.RED));
        root.getRight().setRight(new RBTNode(36, root.getRight(), Color.BLACK));
        root.getRight().getLeft().setLeft(new RBTNode(18, root.getRight().getLeft(), Color.BLACK));
        root.getRight().getLeft().setRight(new RBTNode(26, root.getRight().getLeft(), Color.BLACK));
        return root;
    }

    private Node getExpectedTreeRootForRemovingRoot(){
        Node root = new RBTNode(12, null);
        root.setLeft(new RBTNode(4, root, Color.BLACK));
        root.setRight(new RBTNode(28, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(2, root.getLeft(), Color.BLACK));
        root.getLeft().setRight(new RBTNode(8, root.getLeft(), Color.BLACK));
        root.getRight().setLeft(new RBTNode(24, root.getRight(), Color.RED));
        root.getRight().setRight(new RBTNode(36, root.getRight(), Color.BLACK));
        root.getRight().getLeft().setLeft(new RBTNode(18, root.getRight().getLeft(), Color.BLACK));
        root.getRight().getLeft().setRight(new RBTNode(26, root.getRight().getLeft(), Color.BLACK));
        root.getRight().getRight().setLeft(new RBTNode(32, root.getRight().getRight(), Color.RED));
        return root;
    }
}
