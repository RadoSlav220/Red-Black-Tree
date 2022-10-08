public class Rotations {
    private static void swapColors(Node node1, Node node2){
        Color c = node1.getColor();
        node1.setColor(node2.getColor());
        node2.setColor(c);
    }

    public static Node left(Node root){
        Node newRoot = root.getRight();
        swapColors(root, newRoot);

        Node newRootParent = root.getParent();
        if (newRootParent != null){
            if (newRootParent.getValue() > root.getValue()) {
                newRootParent.setLeft((RBTNode) newRoot);
            } else {
                newRootParent.setRight((RBTNode) newRoot);
            }
        }

        root.setRight((RBTNode) newRoot.getLeft());
        newRoot.getLeft().setParent((RBTNode) root);
        newRoot.setLeft((RBTNode) root);
        newRoot.setParent((RBTNode) newRootParent);
        root.setParent((RBTNode) newRoot);

        return newRoot;
    }

    public static Node right(Node root){
        Node newRoot = root.getLeft();
        swapColors(root, newRoot);

        Node newRootParent = root.getParent();
        if (newRootParent != null){
            if (newRootParent.getValue() > root.getValue()) {
                newRootParent.setLeft((RBTNode) newRoot);
            } else {
                newRootParent.setRight((RBTNode) newRoot);
            }
        }

        root.setParent((RBTNode) newRoot);
        root.setLeft((RBTNode) newRoot.getRight());
        newRoot.getRight().setParent((RBTNode) root);
        newRoot.setRight((RBTNode) root);
        newRoot.setParent((RBTNode) newRootParent);

        return newRoot;
    }

    public static Node flipColors(Node root){
        root.flipColor();
        root.getLeft().flipColor();
        root.getRight().flipColor();
        return root;
    }
}
