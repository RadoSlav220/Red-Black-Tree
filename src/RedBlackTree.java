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

    public boolean add(Node parent, Node currentNode, int value){
        if (this.size == 0) {
            this.root = new RBTNode(value);
            return true;
        }
        if (currentNode == null) {
            currentNode = new RBTNode(value, parent);
            this.size++;
            if (parent.getRight().getColor() == Color.RED && parent.getLeft().getColor() != Color.RED) {
                parent = Rotations.left(parent);
            } else if (parent.getLeft().getColor() == Color.RED && parent.getLeft().getLeft().getColor() == Color.RED) {
                parent = Rotations.right(parent);
            } else if (parent.getLeft().getColor() == Color.RED && parent.getRight().getColor() == Color.RED) {
                parent = Rotations.flipColors(parent);
            }
            return true;
        } else if (currentNode.getValue() == value) {
            return false;
        }

        if (currentNode.getValue() > value) {
            add(currentNode, currentNode.getRight(), value);
        } else {
            add(currentNode, currentNode.getLeft(), value);
        }
        return true;
    }

    public boolean remove(Node parent, Node currentNode, int value){
        if (this.size == 0) {
            return false;
        }
        return true;
    }

    public boolean contains(Node currentNode, int value){
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
