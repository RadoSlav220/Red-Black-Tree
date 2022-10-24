public class Rotations {

    public static Node left(Node root){
        Node newRoot = root.getRight();
        Node newRootParent = root.getParent() != null ? root.getParent() : null;

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
}
