import java.util.Collection;

public class RedBlackTree {
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
}
