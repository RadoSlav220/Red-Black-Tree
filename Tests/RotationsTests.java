import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RotationsTests {
    /*
        !!! IMPORTANT !!!
        In each graphical representation of the trees a single slash (/) means
        Black link and a double slash (//) means Red link.
    */


    /*
              |                     |
             24                    28
           /   \\                //   \
          18    28       =>     24    36
               /  \            /  \
              26  36          18  26
    */
    @Test
    void left_treeRoot_leftRotatedTreeRoot(){
        //Arrange
        Node root = new RBTNode(24, null, Color.BLACK);
        root.setLeft(new RBTNode(18, root, Color.BLACK));
        root.setRight(new RBTNode(28, root, Color.RED));
        root.getRight().setLeft(new RBTNode(26, root.getRight(), Color.BLACK));
        root.getRight().setRight(new RBTNode(36, root.getRight(), Color.BLACK));

        Node expected = new RBTNode(28, null, Color.BLACK);
        expected.setLeft(new RBTNode(24, expected, Color.RED));
        expected.setRight(new RBTNode(36, expected, Color.BLACK));
        expected.getLeft().setLeft(new RBTNode(18, expected.getLeft(), Color.BLACK));
        expected.getLeft().setRight(new RBTNode(26, expected.getLeft(), Color.BLACK));

        //Act
        Node result = Rotations.left(root);

        //Assert
        assertTrue(equalTrees(result, expected), "The tree must be properly rotated.");
    }


    /*
              |                     |
             28                    24
           //   \                /   \\
          24    36       =>     18    28
         /  \                        /  \
        18  26                      26  36
    */
    @Test
    void right_treeRoot_rightRotatedTreeRoot(){
        //Arrange
        Node root = new RBTNode(28, null, Color.BLACK);
        root.setLeft(new RBTNode(24, root, Color.RED));
        root.setRight(new RBTNode(36, root, Color.BLACK));
        root.getLeft().setLeft(new RBTNode(18, root.getLeft(), Color.BLACK));
        root.getLeft().setRight(new RBTNode(26, root.getLeft(), Color.BLACK));

        Node expected = new RBTNode(24, null, Color.BLACK);
        expected.setLeft(new RBTNode(18, expected, Color.BLACK));
        expected.setRight(new RBTNode(28, expected, Color.RED));
        expected.getRight().setLeft(new RBTNode(26, expected.getRight(), Color.BLACK));
        expected.getRight().setRight(new RBTNode(36, expected.getRight(), Color.BLACK));

        //Act
        Node result = Rotations.left(root);

        //Assert
        assertTrue(equalTrees(result, expected), "The tree must be properly rotated.");
    }


    /*
              |                    ||
             24                    24
           //  \\       =>       /   \
          18    26              18   26
    */
    @Test
    void flipColors_treeRoot_flippedColorsTreeRoot(){
        //Arrange
        Node root = new RBTNode(24, null, Color.BLACK);
        root.setLeft(new RBTNode(18, root, Color.RED));
        root.setRight(new RBTNode(26, root, Color.RED));

        Node expected = new RBTNode(24, null, Color.RED);
        expected.setLeft(new RBTNode(18, expected, Color.BLACK));
        expected.setRight(new RBTNode(26, expected, Color.BLACK));

        //Act
        Node result = Rotations.flipColors(root);

        //Assert
        assertTrue(equalTrees(result, expected), "The colors must be properly flipped.");
    }

    @Test
    void testTest(){
        Node root1 = new RBTNode (5, null);
        root1.setLeft(new RBTNode(4, root1));
        root1.setRight(new RBTNode(7, root1));

        Node root2 = new RBTNode (5, null);
        root2.setLeft(new RBTNode(4, root2));
        root2.setRight(new RBTNode(7, root2));

        assertTrue (equalTrees(root1, root2));
    }

    private boolean equalTrees(Node root1, Node root2){
        ArrayList <Node> nodes1 = getNodes(root1);
        ArrayList <Node> nodes2 = getNodes(root2);
        if (nodes1.size() != nodes2.size()){
            return false;
        }
        for (int i=0; i<nodes1.size(); ++i){
            //If written using gererics, != must be replaced with 'equals()'
            if (nodes1.get(i).getValue() != nodes2.get(i).getValue()){
                return false;
            }
        }
        return true;
    }

    private ArrayList <Node> getNodes(Node root){
        ArrayList <Node> result = new ArrayList<>();
        Queue <Node> spis = new LinkedList<>();
        spis.add(root);

        while (!spis.isEmpty()){
            Node current = spis.peek();
            spis.remove();
            result.add(current);
            if (current.getLeft() != null){
                spis.add(current.getLeft());
            }
            if (current.getRight() != null){
                spis.add(current.getRight());
            }
        }

        return result;
    }
}
