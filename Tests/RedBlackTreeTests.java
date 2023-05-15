import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeTests {

    private final int[] nodesValues = {16, 8, 28, 4, 12, 24, 36, 2, 18, 26, 32};
    private RedBlackTree getTree(){
        RedBlackTree tree = new RedBlackTree();
        for (int i : nodesValues){
            tree.add(i);
        }
        return tree;
    }

    @Test
    void defaultConstructor_createATree_emptyTreeShouldBeCreated(){
        //Arrange
        RedBlackTree tree = new RedBlackTree();

        //Assert
        assertEquals(0, tree.size(), "The tree must be empty.");
        assertTrue(tree.empty());
    }

    @Test
    void contains_emptyTree_emptyTreeShouldNotContainAnyNumber(){
        //Arrange, Act
        RedBlackTree tree = new RedBlackTree();

        //Assert
        assertFalse(tree.contains(5));
    }

    @Test
    void clear_nonEmptyTree_afterClearItMustBeEmpty(){
        //Arrange
        RedBlackTree tree = getTree();

        //Act
        tree.clear();

        //Assert
        assertEquals(0, tree.size(), "After clear the size must be 0.");
        assertTrue(tree.empty(), "After clear the tree must be empty.");
        assertFalse(tree.contains(16), "After clear the tree must not contain 16.");
    }

    @Test
    void swap_swapTwoTrees_TreesDataShouldBeSwapped(){
        //Arrange
        RedBlackTree tree1 = new RedBlackTree();
        int[] values1 = {4, 15, -21, 0};
        for (int i : values1){
            tree1.add(i);
        }
        RedBlackTree tree2 = new RedBlackTree();
        int[] values2 = {1, 15, -2, 6, 8};
        for (int i : values2){
            tree2.add(i);
        }

        //Act
        tree1.swap(tree2);

        //Assert
        assertEquals(values2.length, tree1.size(), "Size of tree1 should be the size of values2.");
        for (int i : values2){
            assertTrue(tree1.contains(i), "All elements in values2 should be contained in tree1.");
        }
        assertEquals(values1.length, tree2.size(), "Size of tree2 should be the size of values1.");
        for (int i : values1){
            assertTrue(tree2.contains(i), "All elements in values1 should be contained in tree2.");
        }
    }

    @Test
    void add_addNewElement_SizeMustBeIncreased(){
        //Arrange
        RedBlackTree tree = getTree();
        int startSize = tree.size();
        final int newElement = 129093;

        //Act
        tree.add(newElement);

        //Assert
        assertEquals(startSize + 1, tree.size(), "Size must be increased by 1.");
    }

    @Test
    void add_addNewElement_theTreeMustContainTheNewElement(){
        //Arrange
        RedBlackTree tree = getTree();
        final int newElement = 129093;

        //Act
        boolean successful = tree.add(newElement);

        //Assert
        assertTrue(successful);
        assertTrue(tree.contains(newElement), "The tree must contain the new element");
    }

    @Test
    void add_tryAddingAnExistingElement_NothingShouldBeChanged(){
        //Arrange
        RedBlackTree tree = getTree();
        int startSize = tree.size();

        //Act
        boolean successful = tree.add(28);

        //Assert
        assertFalse(successful, "Addition must fail.");
        assertEquals(startSize, tree.size(), "Size must not be changed.");
        for (int i : nodesValues){
            assertTrue(tree.contains(i), "All elements must be there unchanged.");
        }
    }

    @Test
    void remove_removeAnExistingElement_SizeMustBeDecreased(){
        //Arrange
        RedBlackTree tree = getTree();
        int startSize = tree.size();
        final int elementToRemove = 28;

        //Act
        boolean successful = tree.remove(elementToRemove);

        //Assert
        assertTrue(successful);
        assertEquals(startSize - 1, tree.size(), "Size must be decreased by 1.");
    }

    @Test
    void remove_removeAnExistingElement_OnlyTheRemovedElementShouldNoLongerBeThere(){
        //Arrange
        RedBlackTree tree = getTree();
        final int elementToRemove = 28;

        //Act
        boolean successful = tree.remove(elementToRemove);

        //Assert
        assertTrue(successful);
        assertFalse(tree.contains(28), "The element should no longer be there.");
        for (int i : nodesValues){
            if (i != elementToRemove){
                assertTrue(tree.contains(i), "All other element should be still there.");
            }
        }
    }

    @Test
    void remove_removeAnUnexistingElementFromNonEmptyTree_NothingShouldBeChanged(){
        //Arrange
        RedBlackTree tree = getTree();
        int startSize = tree.size();
        final int elementToRemove = -200;

        //Act
        boolean successful = tree.remove(elementToRemove);

        //Assert
        assertFalse(successful);
        assertEquals(startSize, tree.size(), "Size must not be changed.");
        for (int i : nodesValues){
            assertTrue(tree.contains(i), "All elements must be still there.");
        }
    }

    @Test
    void remove_removeAnUnexistingElementFromEmptyTree_NothingShouldBeChanged(){
        //Arrange
        RedBlackTree tree = new RedBlackTree();

        //Act, Assert
        assertFalse(tree.remove(23), "Must not be able to remove any element since the tree is empty.");
        assertTrue(tree.empty(), "Tree must still be empty.");
    }

    @Test
    public void iterator_testFor() {
        RedBlackTree tree = new RedBlackTree();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);
        tree.add(8);

        int i = 1;
        for (int el : tree) {
            assertEquals(el, i);
            i++;
        }
    }

}
