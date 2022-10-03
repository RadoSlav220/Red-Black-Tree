public interface Node {
    int getValue();
    Node getParent();
    Node getLeft();
    Node getRight();
    Color getColor();

    void setLeft();
    void setRight();
    void setParent();
}
