import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OperationsTests {
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

    @Test
    public void insert_valuesInTree_expectsValues() {
        Node root = new RBTNode(5);

        Operations.insert(root, 5);
        Operations.insert(root, 6);
        Operations.insert(root, 3);

        //To be written
    }

    @Test
    public void delete_treeRootAndValueToBeRemoved_treeRootWithoutTheRemovedValue() {

    }
}
