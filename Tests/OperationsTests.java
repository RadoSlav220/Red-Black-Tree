import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OperationsTests {

    RedBlackTree tree = new RedBlackTree();

    @Test
    public void insert_valuesInTree_expectsValues() {
        tree.add(5);
        tree.add(6);
        tree.add(3);

        assertTrue(tree.contains(5));
        assertTrue(tree.contains(6));
        assertTrue(tree.contains(3));
    }

    @Test
    public void delete_valuesFromTree_expectsFalse() {
        tree.add(5);
        tree.add(6);
        tree.add(3);

        tree.remove(5);
        tree.remove(6);
        tree.remove(3);

        assertFalse(tree.contains(5));
        assertFalse(tree.contains(6));
        assertFalse(tree.contains(3));
    }
}
