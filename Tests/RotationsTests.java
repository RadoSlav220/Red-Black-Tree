import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RotationsTests {

    /*
              |                     |
             24                    28
           /    \                /   \
          18    28       =>     24    36
               /  \            /  \
              26  36          18  26
    */
    @Test
    void left_treeRoot_leftRotatedTreeRoot(){
        //Arrange
        Node root = new RBTNode(24, null);
        root.setLeft(new RBTNode(18, root));
        root.setRight(new RBTNode(28, root));
        root.getRight().setLeft(new RBTNode(26, root.getRight()));
        root.getRight().setRight(new RBTNode(36, root.getRight()));

        Node expected = new RBTNode(28, null);
        expected.setLeft(new RBTNode(24, expected));
        expected.setRight(new RBTNode(36, expected));
        expected.getLeft().setLeft(new RBTNode(18, expected.getLeft()));
        expected.getLeft().setRight(new RBTNode(26, expected.getLeft()));

        //Act
        Node result = Rotations.left(root);

        //Assert
        assertTrue(TreeComparator.equalTrees(result, expected), "The tree must be properly rotated.");
    }


    /*
              |                     |
             28                    24
           /   \                 /    \
          24    36       =>     18    28
         /  \                        /  \
        18  26                      26  36
    */
    @Test
    void right_treeRoot_rightRotatedTreeRoot(){
        //Arrange
        Node root = new RBTNode(28, null);
        root.setLeft(new RBTNode(24, root));
        root.setRight(new RBTNode(36, root));
        root.getLeft().setLeft(new RBTNode(18, root.getLeft()));
        root.getLeft().setRight(new RBTNode(26, root.getLeft()));

        Node expected = new RBTNode(24, null);
        expected.setLeft(new RBTNode(18, expected));
        expected.setRight(new RBTNode(28, expected));
        expected.getRight().setLeft(new RBTNode(26, expected.getRight()));
        expected.getRight().setRight(new RBTNode(36, expected.getRight()));

        //Act
        Node result = Rotations.right(root);

        //Assert
        assertTrue(TreeComparator.equalTrees(result, expected), "The tree must be properly rotated.");
    }
}
