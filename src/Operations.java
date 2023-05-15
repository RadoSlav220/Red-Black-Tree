public class Operations {

    public static Node insert (Node root, int value){
        if (root == null){
            return new RBTNode(value);
        }

        Node child = linkNewNode(root, value);
        if (child == null){
            throw new IllegalArgumentException("This value is already in the tree.");
        }

        root = balanceTreeInsert(root, child);

        return root;
    }

    public static Node delete(Node root, int value){
        Node nodeToBeDeleted = findNode(root, value);
        if (nodeToBeDeleted == null) {
            throw new IllegalArgumentException("This value is not in the tree.");
        }

        Node leaf = nodeToBeDeleted.getLeft() != null ? (RBTNode) findRightMostLeaf(nodeToBeDeleted.getLeft()) : nodeToBeDeleted;
        swapValues(nodeToBeDeleted, leaf);

        if (leaf == root){
            return null;
        }

        //Check if the leaf is red or has a red child. In this case deletion is easy.
        if (deleteRed(leaf)){
            return root;
        }

        root = balanceTreeDelete(root, leaf);

        //The deletion itself.
        if (isRightChild(leaf.getParent(), leaf)){
            leaf.getParent().setRight(null);
        } else {
            leaf.getParent().setLeft(null);
        }

        return root;
    }

/******************************** Insert helpers *************************************/

    private static Node linkNewNode (Node root, int value) {
        if (root.getValue() > value) {
            if (root.getLeft() == null){
                root.setLeft(new RBTNode(value, root));
                return root.getLeft();
            } else {
                return linkNewNode(root.getLeft(), value);
            }
        } else if (root.getValue() < value){
            if (root.getRight() == null){
                root.setRight(new RBTNode(value, root));
                return root.getRight();
            } else {
                return linkNewNode(root.getRight(), value);
            }
        } else {
            return null;
        }
    }

    private static Node balanceTreeInsert (Node root, Node node) {
        Node parent = node.getParent();
        Node uncle = node.getUncle();
        Node grandparent = node.getGrandParent();

        if (node == root){
            node.setColor(Color.BLACK);
            return root;
        }

        if (isRed(parent)){
            if (isRed(uncle)){
                parent.setColor(Color.BLACK);
                uncle.setColor(Color.BLACK);
                grandparent.setColor(Color.RED);
                return balanceTreeInsert(root, grandparent);
            } else { //Uncle is black
                if (isLeftLeftCase(node)) {
                    Rotations.right(grandparent);
                    swapColors(parent, grandparent);
                } else if (isLeftRightCase(node)) {
                    Rotations.left(parent);
                    Rotations.right(grandparent);
                    swapColors(node, grandparent);
                }else if (isRightRightCase(node)) {
                    Rotations.left(grandparent);
                    swapColors(parent, grandparent);
                }else if (isRightLeftCase(node)) {
                    Rotations.right(parent);
                    Rotations.left(grandparent);
                    swapColors(node, grandparent);
                }

                //After rotations root may be changed.
                //If changed, new root is the parent of the rotated grandparent.
                return root == grandparent ? grandparent.getParent() : root;
            }
        }
        return root;
    }

    private static boolean isRed(Node node){
        return node != null && node.getColor() == Color.RED;
    }

    //We are sure that there is grandParent, otherwise there will be no rotation.
    private static boolean isLeftLeftCase(Node root) {
        return root.getGrandParent().getValue() > root.getParent().getValue() &&
               root.getParent().getValue() > root.getValue();
    }

    private static boolean isLeftRightCase(Node root) {
        return root.getGrandParent().getValue() > root.getParent().getValue() &&
               root.getParent().getValue() < root.getValue();
    }

    private static boolean isRightRightCase(Node root) {
        return root.getGrandParent().getValue() < root.getParent().getValue() &&
               root.getParent().getValue() < root.getValue();
    }

    private static boolean isRightLeftCase(Node root) {
        return root.getGrandParent().getValue() < root.getParent().getValue() &&
               root.getParent().getValue() > root.getValue();
    }

    private static void swapColors (Node node1, Node node2) {
        Color temp = node1.getColor();
        node1.setColor(node2.getColor());
        node2.setColor(temp);
    }

/******************************** Delete helpers *************************************/

    private static boolean deleteRed(Node leaf){
        if (isRed(leaf)) {
            if (isRightChild(leaf.getParent(), leaf)){
                leaf.getParent().setRight(null);
            } else {
                leaf.getParent().setLeft(null);
            }
            return true;
        } else if (isRed(leaf.getLeft())) {
            swapValues(leaf, leaf.getLeft());
            leaf.setLeft(null);
            return true;
        } else if (isRed(leaf.getRight())){
            swapValues(leaf, leaf.getRight());
            leaf.setRight(null);
            return true;
        }
        return false;
    }

    private static Node balanceTreeDelete(Node root, Node node){
        if (isRed(node.getSibling())){
            return redSiblingCase(root, node);
        } else {
            return blackSiblingCase(root, node);
        }
    }

    private static Node redSiblingCase(Node root, Node leaf) {
        Node parent = leaf.getParent();
        Node sibling = leaf.getSibling();

        if (isRightChild(parent, sibling)) {
            Rotations.left(parent);
            parent.setColor(Color.BLACK);
            sibling.setColor(Color.BLACK);
            parent.getRight().setColor(Color.RED);
        } else {
            Rotations.right(parent);
            parent.setColor(Color.BLACK);
            sibling.setColor(Color.BLACK);
            parent.getLeft().setColor(Color.RED);
        }

        //Previous parent is now child of the sibling
        return parent == root ? parent.getParent() : root;
    }

    private static Node blackSiblingCase(Node root, Node leaf){
        Node parent = leaf.getParent();
        Node sibling = leaf.getSibling();

        if (isDoubleBlackLeftLeftCase(sibling)) {
            Rotations.right(parent);
            swapColors(parent, parent.getParent());
            sibling.getLeft().setColor(Color.BLACK);
        } else if (isDoubleBlackLeftRightCase(sibling)) {
            Rotations.left(sibling);
            Rotations.right(parent);
            parent.getParent().setColor(parent.getColor());
            parent.setColor(Color.BLACK);
        } else if (isDoubleBlackRightRightCase(sibling)) {
            Rotations.left(parent);
            swapColors(parent, parent.getParent());
            sibling.getRight().setColor(Color.BLACK);
        } else if (isDoubleBlackRightLeftCase(sibling)) {
            Rotations.right(sibling);
            Rotations.left(parent);
            parent.getParent().setColor(parent.getColor());
            parent.setColor(Color.BLACK);
        } else if (areBothChildrenOfSiblingBlack(sibling)) {
            sibling.setColor(Color.RED);
            if (parent.getColor() == Color.RED || parent.getParent() == null){
                parent.setColor(Color.BLACK);
                return root;
            } else {
                return balanceTreeDelete(root, parent);
            }
        }

        //Previous parent is now child of the sibling/sibling's child
        return parent == root ? parent.getParent() : root;
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

    private static Node findRightMostLeaf (Node node) {
        if (node.getRight() == null) {
            return node;
        }

        return findRightMostLeaf(node.getRight());
    }

    private static void swapValues (Node node1, Node node2) {
        int temp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp);
    }

    private static boolean isRightChild (Node parent, Node child) {
        return parent.getRight() != null &&
               parent.getRight().getValue() == child.getValue();
    }

    private static boolean areBothChildrenOfSiblingBlack (Node sibling) {
        return !isRed(sibling.getLeft()) && !isRed(sibling.getRight());
    }

    private static boolean isDoubleBlackLeftLeftCase (Node node) {
        return node.getLeft() != null && node.getLeft().getColor() ==
                Color.RED && !isRightChild(node.getParent(), node);
    }

    private static boolean isDoubleBlackLeftRightCase (Node node) {
        return node.getRight() != null && node.getRight().getColor() ==
                Color.RED && !isRightChild(node.getParent(), node);
    }

    private static boolean isDoubleBlackRightRightCase (Node node) {
        return node.getRight() != null && node.getRight().getColor() ==
                Color.RED && isRightChild(node.getParent(), node);
    }

    private static boolean isDoubleBlackRightLeftCase (Node node) {
        return node.getLeft() != null && node.getLeft().getColor() ==
                Color.RED && isRightChild(node.getParent(), node);
    }
}
