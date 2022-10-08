import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OperationsTests {

    /*
        !!! IMPORTANT !!!
        In each graphical representation of the trees a single slash (/) means
        Black link and a double slash (//) means Red link.
    */


    /*
                |                             |
                16                            16
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
                |                             |
                16                            16
           /         \                   /          \
          8           28       =>       8            28
         /  \     //      \           //  \      //      \
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
        assertEquals(32, Operations.delete(root, 32));
        assertEquals(null, Operations.delete(root, 100));

        assertTrue(TreeComparator.equalTrees(root, expectedRoot));
    }

    // TODO: Delete root
    @Test
    public void delete_treeRootToBeRemoved_treeRootWithoutTheRemovedValue() {
        // Arrange
        Node root = getTreeRoot();
        Node expectedRoot = getExpectedTreeRootForRemovingRoot();

        //Act, Assert
        assertEquals(16, Operations.delete(root, 16));

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
        Node root = new RBTNode(16, null);
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
}
