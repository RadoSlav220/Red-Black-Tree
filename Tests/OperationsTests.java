import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OperationsTests {

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
