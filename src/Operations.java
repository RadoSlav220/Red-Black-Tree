public class Operations {
    public static Node delete(Node root, int value){
        return null;
    }

    public static Node insert (Node root, int value){
        if (root.getParent() == null) {
            root = new RBTNode(value);
            return root;
        }
        if (root == null) {
            root = new RBTNode(value, root);
            if (root.getRight().getColor() == Color.RED && root.getLeft().getColor() != Color.RED) {
                root = Rotations.left(root);
            } else if (root.getLeft().getColor() == Color.RED && root.getLeft().getLeft().getColor() == Color.RED) {
                root = Rotations.right(root);
            } else if (root.getLeft().getColor() == Color.RED && root.getRight().getColor() == Color.RED) {
                root = Rotations.flipColors(root);
            }
            return root;
        } else if (root.getValue() == value) {
            return null;
        }

        if (root.getValue() > value) {
            insert(root.getRight(), value);
        } else {
            insert(root.getLeft(), value);
        }
        return root;
    }
}
