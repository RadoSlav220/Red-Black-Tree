import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RedBlackTree implements Iterable<Integer> {
    private Node root;
    private int size;

    public RedBlackTree() {
        root = null;
        size = 0;
    }

    public RedBlackTree (Collection<Integer> arr) {
        for (int i : arr){
            Operations.insert(root, i);
        }
        size = arr.size();
    }

    public int size() {
        return this.size;
    }

    public boolean empty(){
        return this.size == 0;
    }

    public void clear() {
        this.root = null;
        this.size = 0;
    }

    public void swap(RedBlackTree other) {
        Node tempRoot = this.root;
        this.root = other.root;
        other.root = tempRoot;

        int tempSize = this.size;
        this.size = other.size;
        other.size = tempSize;
    }

    public boolean add(int value){
        try {
            root = Operations.insert(root, value);
            ++size;
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    public boolean remove(int value) {
        try {
            root = Operations.delete(root, value);
            --size;
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    public boolean contains(int value){
        return contains(root, value);
    }

    public void printSorted(){
        printSortedHelper(root);
    }

    private void printSortedHelper(Node node){
        if (node == null){
            return;
        }
        printSortedHelper(node.getLeft());
        System.out.print(node.getValue() + " ");
        printSortedHelper(node.getRight());
    }

    private boolean contains(Node currentNode, int value){
        if (currentNode == null) {
            return false;
        }
        if (currentNode.getValue() == value) {
            return true;
        }

        if (currentNode.getValue() > value) {
            return contains(currentNode.getLeft(), value);
        } else{
            return contains(currentNode.getRight(), value);
        }
    }

    /**
     * Creates Iterator for current Red-Black Tree
     *
     * @return new RedBlackTreeIterator object
     */
    public Iterator<Integer> iterator() {
        return new RedBlackTreeIterator(this);
    }

    /**
     * Iterator
     */
    private class RedBlackTreeIterator implements Iterator<Integer> {

        ArrayList<Integer> inOrderTree = new ArrayList<Integer>();
        private int size;
        private int count;


        public RedBlackTreeIterator(RedBlackTree tree) {
            this.size = tree.size();
            this.count = 0;
            inorderTraversal(tree.root);
        }

        /**
         * Checks if the given array has a next element that can be traversed.
         *
         * @return boolean
         */
        public boolean hasNext() {
            return this.count < this.size;
        }

        /**
         * Returns the current element and sets the index to the next element.
         *
         * @return the current element
         */
        public Integer next() {
            if (!hasNext()){
                return null;
            }

            this.count++;
            return inOrderTree.get(count-1);
        }

        /**
         *  Inorder traversal
         *
         * @param node
         */
        private void inorderTraversal (Node node) {
            if (node == null) {
                return;
            }

            inorderTraversal(node.getLeft());
            this.inOrderTree.add(node.getValue());
            inorderTraversal(node.getRight());
        }
    }
}
