public class Operations {

    public static Node delete(Node root, int value){
        RBTNode found = (RBTNode) findNode(root, value);
        if (found == null) {
            return null;
        }
        RBTNode leaf = found.getLeft() != null ? (RBTNode) findLeaf(found.getLeft()) : found;
        swapValues(found, leaf);

        RBTNode leafParent = (RBTNode) leaf.getParent();
        RBTNode sibling = null;

        if (leaf.getColor() == Color.RED || leafParent.getColor() == Color.RED) {
            if (isRightChild(leafParent.getValue(), leaf.getValue())) {
                leafParent.setRight(null);
            } else {
                leafParent.setLeft(null);
            }
            leafParent.setColor(Color.BLACK);
            return leaf;
        } else {
            // remove node
            if (isRightChild(leafParent.getValue(), leaf.getValue())) {
                leafParent.setRight(null);
                sibling = (RBTNode) leafParent.getLeft();
            } else {
                leafParent.setLeft(null);
                sibling = (RBTNode) leafParent.getRight();
            }

            if (sibling.getColor() == Color.BLACK && sibling == leafParent.getRight()) {
                // Right-left case
                if (sibling.getLeft() != null) {
                    if (sibling.getLeft().getColor() == Color.RED) {
                        sibling = (RBTNode) Rotations.right(sibling);
                        sibling = (RBTNode) Rotations.left(sibling.getParent());
                    }
                } else if (areBothChildrenOfSiblingBlack(sibling)) {
                        sibling.flipColor();
                        sibling = (RBTNode) Rotations.left(sibling);
                        if (sibling.getLeft().getRight() != null) {
                            sibling = (RBTNode) Rotations.left(sibling.getLeft());
                            sibling.setColor(Color.BLACK);
                            sibling.getLeft().setColor(Color.RED);
                        }
                }
            } else if (sibling.getColor() == Color.BLACK && sibling == leafParent.getLeft()) {
                // Left-left case
                if (sibling.getLeft() != null) {
                    if (sibling.getLeft().getColor() == Color.RED) {
                        sibling = (RBTNode) Rotations.right(sibling.getParent());
                        sibling.setColor(Color.BLACK);
                        if (sibling.getLeft() != null) {
                            sibling.getLeft().setColor(Color.BLACK);
                        }
                        if (sibling.getRight() != null) {
                            sibling.getRight().setColor(Color.BLACK);
                        }
                    }
                } else if (areBothChildrenOfSiblingBlack(sibling)) {
                    sibling.flipColor();
                    sibling = (RBTNode) Rotations.right(sibling);
                    if (sibling.getRight().getLeft() != null) {
                        sibling.getRight().getLeft().setColor(Color.RED);
                    }
                }
            } else {
                sibling = (RBTNode) Rotations.right(sibling);
                if (sibling.getRight().getLeft() != null) {
                    sibling.getRight().getLeft().setColor(Color.RED);
                }
            }
        }

        return leaf;
    }

    public static Node insert (Node root, int value){
        if (root == null){
            return new RBTNode(value);
        }

        Node parent = linkNewNode(root, value);
        if (parent == null){
            throw new IllegalArgumentException("This value is already in the tree.");
        }

        while (parent != null){
            root = balanceTree(parent);
            parent = root.getParent();
        }
        root.setColor(Color.BLACK);
        return root;
    }

    private static Node linkNewNode (Node root, int value) {
        if (root.getValue() > value) {
            if (root.getLeft() == null){
                root.setLeft(new RBTNode(value, root));
                return root;
            } else {
                return linkNewNode(root.getLeft(), value);
            }
        } else if (root.getValue() < value){
            if (root.getRight() == null){
                root.setRight(new RBTNode(value, root));
                return root;
            } else {
                return linkNewNode(root.getRight(), value);
            }
        } else {
            return null;
        }
    }

    private static Node balanceTree (Node root) {
        if (checkFlipColors(root)){
            root = Rotations.flipColors(root);
            return balanceTree(root);
        } else if (checkLeftRotation(root)) {
            root = Rotations.left(root);
            return balanceTree(root);
        } else if (checkRightRotation(root)) {
            root = Rotations.right(root);
            return balanceTree(root);
        }
        return root;
    }

    private static boolean checkLeftRotation(Node root){
        return checkSingleBlackRightChild(root) ||
               (root.getRight() != null &&
               root.getRight().getColor() == Color.RED);
    }

    private static boolean checkSingleBlackRightChild(Node root){
        return root.getLeft() == null &&
               root.getRight() != null &&
               root.getRight().getColor() == Color.BLACK;
    }

    private static boolean checkRightRotation(Node root){
        return checkSingleBlackLeftChild(root) ||
               (root.getLeft() != null &&
                root.getLeft().getColor() == Color.RED &&
                root.getLeft().getLeft() != null &&
                root.getLeft().getLeft().getColor() == Color.RED);
    }

    private static boolean checkSingleBlackLeftChild(Node root){
        return root.getRight() == null &&
               root.getLeft() != null &&
               root.getLeft().getColor() == Color.BLACK;
    }

    private static boolean checkFlipColors(Node root){
        return  root.getLeft() != null &&
                root.getRight() != null &&
                root.getLeft().getColor() == Color.RED &&
                root.getRight().getColor() == Color.RED;
    }

    private static Node findNode (Node root, int value) {
        if (root == null){
            return null;
        }

        if (root.getValue() == value) {
            return root;
        } else if (root.getValue() > value){
            return findNode(root.getLeft(), value);
        } else {
            return findNode(root.getRight(), value);
        }
    }

    private static Node findLeaf (Node node) {
        if (node.getRight() == null) {
            return node;
        }

        return findLeaf(node.getRight());
    }

    private static void swapValues (Node node1, Node node2) {
        int temp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp);
    }

    private static boolean isRightChild (int parentValue, int childValue) {
        return parentValue < childValue;
    }

    private static boolean areBothChildrenOfSiblingBlack (Node sibling) {
        return ((sibling.getLeft() != null && sibling.getRight() != null
                && sibling.getLeft().getColor() == Color.BLACK
                && sibling.getRight().getColor() == Color.BLACK) ||
                (sibling.getLeft() == null && sibling.getRight() == null));
    }
}
