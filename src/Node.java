public interface Node {
    int getValue();
    Node getParent();
    Node getLeft();
    Node getRight();
    Color getColor();

    void setLeft(RBTNode node);
    void setRight(RBTNode node);
    void setParent(RBTNode parent);
    void setValue(int value);
    void setColor(Color color);
    void flipColor();
}
