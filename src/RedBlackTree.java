import java.util.Collection;

public class RedBlackTree {
    private Node root;
    private int size;

    public RedBlackTree(){
        root = null;
        size = 0;
    }

    public RedBlackTree(Collection<Integer> arr){

    }

    public int size() {
        return this.size;
    }

    public boolean empty(){
        return this.size == 0;
    }

    public void clear(){
        this.root = null;
        this.size = 0;
    }

    public void swap(RedBlackTree other){
        Node tempRoot = this.root;
        this.root = other.root;
        other.root = tempRoot;

        int tempSize = this.size;
        this.size = other.size;
        other.size = tempSize;
    }

    public boolean add(int value){
        return true;
    }

    public boolean remove(int value){
        if (this.size == 0) {
            return false;
        }
        return true;
    }

    public boolean contains(int value){
        return contains(root, value);
    }

    private boolean contains(Node currentNode, int value){
        if (currentNode == null) {
            return false;
        }
        if (currentNode.getValue() == value) {
            return true;
        }

        if (currentNode.getValue() > value) {
            return contains(currentNode.getRight(), value);
        } else{
            return contains(currentNode.getLeft(), value);
        }
    }
}
