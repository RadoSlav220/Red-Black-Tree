import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RBTNodeTests {

    private final int DEFAULT_VALUE = 15;
    private RBTNode node = new RBTNode(DEFAULT_VALUE, null);
    private RBTNode childNode = new RBTNode(DEFAULT_VALUE, null);

    @Test
    public void constructor_setRoot_expectsParentEqualToNull() {
        node.setValue(5);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        assertNull(node.getParent());
        assertEquals(5, node.getValue());
        assertEquals(Color.BLACK, node.getColor());
        assertEquals(Color.BLACK, node.getLeft().getColor());
        assertEquals(Color.BLACK, node.getRight().getColor());
    }

    @Test
    public void constructor_setChildNode_expectsParentEqualToRoot() {
        node.setValue(5);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);
        childNode.setValue(6);
        node.setRight(childNode);
        childNode.setParent(node);
        childNode.setRight(null);
        childNode.setLeft(null);

        assertEquals(5, childNode.getParent().getValue());
        assertEquals(6, childNode.getValue());
        assertEquals(Color.RED, childNode.getColor());
        assertEquals(Color.BLACK, childNode.getLeft().getColor());
        assertEquals(Color.BLACK, childNode.getRight().getColor());
    }

    @Test
    public void getValue_setRoot_expectsRootValue() {
        node.setValue(20873);
        node.setRight(null);
        node.setLeft(null);
        node.setParent(null);

        assertNull(node.getParent());
        assertNull(node.getLeft());
        assertNull(node.getRight());
        assertEquals(5, node.getValue());
        assertEquals(Color.BLACK, node.getColor());
    }

    @Test
    public void getValue_setParentAndChild_expectsParentAndChildValue() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);
        childNode.setValue(6234);
        node.setRight(childNode);
        childNode.setParent(node);
        childNode.setRight(null);
        childNode.setLeft(null);

        assertEquals(500, childNode.getParent().getValue());
        assertNull(childNode.getParent().getParent());

        assertEquals(6234, childNode.getValue());
    }

    @Test
    public void getParent_setParentAndChild_expectsParent() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);
        childNode.setValue(6234);
        node.setRight(childNode);
        childNode.setParent(node);
        childNode.setRight(null);
        childNode.setLeft(null);

        assertEquals(node, childNode.getParent());
    }

    @Test
    public void getParent_setRoot_expectsNull() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        assertNull(node.getParent());
    }

    @Test
    public void getLeft_setRootWithoutChildren_expectsNull() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        assertNull(node.getLeft());
    }

    @Test
    public void getLeft_setRootWithChildren_expectsLeftChild() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        childNode.setValue(3);
        node.setLeft(childNode);
        childNode.setParent(node);
        childNode.setRight(null);
        childNode.setLeft(null);

        assertEquals(childNode, node.getLeft());
    }

    @Test
    public void getRight_setRootWithoutChildren_expectsNull() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        assertNull(node.getRight());
    }

    @Test
    public void getRight_setRootWithChildren_expectsRightChild() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        childNode.setValue(3345);
        node.setRight(childNode);
        childNode.setParent(node);
        childNode.setRight(null);
        childNode.setLeft(null);

        assertEquals(childNode, node.getRight());
    }

    @Test
    public void getColor_setRootWithoutChildren_expectsBlackRootWithBlackChildren() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        assertEquals(Color.BLACK, node.getColor());
        assertEquals(Color.BLACK, node.getRight().getColor());
        assertEquals(Color.BLACK, node.getLeft().getColor());
    }

    @Test
    public void getColor_setRootWithChild_expectsBlackRootWithBlackChild() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        childNode.setValue(3345);
        node.setRight(childNode);
        childNode.setParent(node);
        childNode.setRight(null);
        childNode.setLeft(null);

        assertEquals(Color.BLACK, node.getColor());
        assertEquals(Color.RED, node.getRight().getColor());
        assertEquals(Color.BLACK, node.getLeft().getColor());
    }

    @Test
    public void setLeft_setRootWithLeftChild_expectsLeftChild() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        childNode.setValue(333);
        node.setLeft(childNode);
        childNode.setParent(node);
        childNode.setRight(null);
        childNode.setLeft(null);

        assertEquals(childNode, node.getLeft());
        assertEquals(Color.RED, node.getLeft().getColor());
    }

    @Test
    public void setRight_setRootWithRightChild_expectsRightChild() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        childNode.setValue(3333);
        node.setRight(childNode);
        childNode.setParent(node);
        childNode.setRight(null);
        childNode.setLeft(null);

        assertEquals(childNode, node.getRight());
        assertEquals(Color.RED, node.getRight().getColor());
    }

    @Test
    public void setParent_setRootWithRightChild_expectsRightChild() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        childNode.setValue(3333);
        node.setRight(childNode);
        childNode.setParent(node);
        childNode.setRight(null);
        childNode.setLeft(null);

        assertEquals(node, childNode.getParent());
        assertEquals(Color.BLACK, childNode.getParent().getColor());
    }

    @Test
    public void setValue_setRootValueAndChangeIt_expectsRootValue() {
        node.setValue(500);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);

        assertEquals(500, node.getValue());

        node.setValue(600);

        assertEquals(600, node.getValue());
    }

    @Test
    public void setColor_setNode_expectsDifferentColorNode() {
        node.setValue(500);
        node.setParent(new RBTNode(DEFAULT_VALUE, null));

        node.setColor(Color.BLACK);

        assertEquals(Color.BLACK, node.getColor());

        node.setColor(Color.RED);

        assertEquals(Color.RED, node.getColor());
    }
}
