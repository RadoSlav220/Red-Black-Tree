import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RBTNodeTests {

    private final int DEFAULT_VALUE = 15;
    private RBTNode node = new RBTNode(DEFAULT_VALUE);
    private RBTNode childNode = new RBTNode(DEFAULT_VALUE + 10, node);

    @Test
    public void constructor_setRoot_expectsParentEqualToNull() {
        // Act, Assert
        assertNull(node.getParent());
        assertEquals(15, node.getValue());
        assertEquals(Color.BLACK, node.getColor());
    }

    @Test
    public void constructor_setChildNode_expectsParentEqualToRoot() {
        // Arrange
        node.setRight(childNode);

        // Assert
        assertEquals(15, childNode.getParent().getValue());
        assertEquals(25, childNode.getValue());
        assertEquals(Color.RED, childNode.getColor());
        assertEquals(Color.BLACK, childNode.getParent().getColor());
    }

    @Test
    public void getValue_setRoot_expectsRootValue() {
        // Arrange
        RBTNode node = new RBTNode(20873);

        // Assert
        assertNull(node.getParent());
        assertNull(node.getLeft());
        assertNull(node.getRight());
        assertEquals(20873, node.getValue());
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
        // Arrange
        RBTNode root = new RBTNode(44);

        // Assert
        assertEquals(Color.BLACK, root.getColor());
        assertNull(root.getRight());
        assertNull(root.getLeft());
    }

    @Test
    public void getColor_setRootWithChild_expectsBlackRootWithRedChild() {
        //Assert
        assertEquals(Color.BLACK, node.getColor());
        assertEquals(Color.RED, node.getRight().getColor());
    }

    @Test
    public void setLeft_setRootWithLeftChild_expectsLeftChild() {
        // Arrange
        childNode = new RBTNode(2, node);

        // Act, Assert
        assertEquals(childNode, node.getLeft());
        assertEquals(Color.RED, node.getLeft().getColor());
    }

    @Test
    public void setRight_setRootWithRightChild_expectsRightChild() {
        // Arrange
        node.setValue(500);
        childNode.setValue(3333);

        // Act, Assert
        assertEquals(childNode, node.getRight());
        assertEquals(Color.RED, node.getRight().getColor());
    }

    @Test
    public void setParent_setRootWithRightChild_expectsRightChild() {
        //Assert
        assertEquals(node, childNode.getParent());
        assertEquals(Color.RED, node.getRight().getColor());
        assertEquals(childNode.getValue(), node.getRight().getValue());
    }

    @Test
    public void setValue_setRootValueAndChangeIt_expectsRootValue() {
        //Act
        node.setValue(500);

        //Assert
        assertEquals(500, node.getValue());

        //Act
        node.setValue(600);

        //Assert
        assertEquals(600, node.getValue());
    }

    @Test
    public void setColor_setNode_expectsDifferentColorNode() {
        // Arrange
        node.setValue(500);
        node.setParent(new RBTNode(DEFAULT_VALUE, null));

        // Act
        node.setColor(Color.BLACK);

        // Assert
        assertEquals(Color.BLACK, node.getColor());

        // Act
        node.setColor(Color.RED);

        // Assert
        assertEquals(Color.RED, node.getColor());
    }
}
