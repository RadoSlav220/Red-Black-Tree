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

        if (this.parent != null) {
            if (this.parent.getValue() > this.value) {
                this.parent.setLeft(this);
            } else {
                this.parent.setRight(this);
            }
        }
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
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void flipColor(){
        this.color = this.color == Color.BLACK ? Color.RED : Color.BLACK;
    }
}
