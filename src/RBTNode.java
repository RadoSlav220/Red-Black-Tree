public class RBTNode implements Node {

    private int value;
    private Color color;
    private Node parent;
    private Node left;
    private Node right;


    public RBTNode (int value) {
        this(value, null, Color.BLACK);
    }

    public RBTNode (int value, Node parent) {
        this(value, parent, Color.RED);
    }

    public RBTNode (int value, Node parent, Color color) {
        this.value = value;
        this.parent = parent;
        this.color = color;
        this.left = this.right = null;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public Node getParent() {
        return this.parent;
    }

    @Override
    public Node getLeft() {
        return this.left;
    }

    @Override
    public Node getRight() {
        return this.right;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setLeft(RBTNode newLeft) {
        this.left = newLeft;
    }

    @Override
    public void setRight(RBTNode newRight) {
        this.right = newRight;
    }

    @Override
    public void setParent(RBTNode parent) {
        this.parent = parent;
    }

    @Override
    public void setValue(int value){
        this.value = value;
    }

    @Override
    public void changeColor(Color color) {
        this.color = color;
    }
}
