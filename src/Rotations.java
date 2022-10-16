public class Rotations {

    public static Node left(Node root){
        Node newRoot = root.getRight();
        newRoot.setColor(Color.BLACK);
        root.setColor(Color.RED);

        Node newRootParent = root.getParent();
        if (newRootParent != null){
            if (newRootParent.getValue() > root.getValue()) {
                newRootParent.setLeft((RBTNode) newRoot);
            } else {
                newRootParent.setRight((RBTNode) newRoot);
            }
        }

        if (newRoot.getLeft() != null){
            root.setRight((RBTNode) newRoot.getLeft());
            newRoot.getLeft().setParent((RBTNode) root);
        } else {
            root.setRight(null);
        }
        newRoot.setLeft((RBTNode) root);
        newRoot.setParent((RBTNode) newRootParent);
        root.setParent((RBTNode) newRoot);

        return newRoot;
    }

    public static Node right(Node root){

        Node newRoot = root.getLeft();
        newRoot.setColor(Color.BLACK);
        root.setColor(Color.RED);

        Node newRootParent = root.getParent();
        if (newRootParent != null){
            if (newRootParent.getValue() > root.getValue()) {
                newRootParent.setLeft((RBTNode) newRoot);
            } else {
                newRootParent.setRight((RBTNode) newRoot);
            }
        }

        if (newRoot.getRight() != null){
            root.setLeft((RBTNode) newRoot.getRight());
            newRoot.getRight().setParent((RBTNode) root);
        } else {
            root.setLeft(null);
        }
        newRoot.setRight((RBTNode) root);
        newRoot.setParent((RBTNode) newRootParent);
        root.setParent((RBTNode) newRoot);

        return newRoot;
    }

    public static Node flipColors(Node root){
        if (root.getParent() != null){
            //Do we have to worry about this case here?
            root.flipColor();
        }
        root.getLeft().flipColor();
        root.getRight().flipColor();
        return root;
    }
}
