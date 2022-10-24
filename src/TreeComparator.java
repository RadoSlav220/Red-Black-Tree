import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeComparator {
    public static boolean equalTrees(Node root1, Node root2){
        ArrayList <Node> nodes1 = getNodes(root1);
        ArrayList <Node> nodes2 = getNodes(root2);
        if (nodes1.size() != nodes2.size()){
            return false;
        }
        for (int i=0; i<nodes1.size(); ++i){
            //If written using generics, != must be replaced with 'equals()'
            if (!equalNodes(nodes1.get(i), nodes2.get(i))){
                return false;
            }
        }
        return true;
    }

    private static ArrayList <Node> getNodes(Node root){
        ArrayList <Node> result = new ArrayList<>();
        Queue<Node> spis = new LinkedList<>();
        spis.add(root);

        while (!spis.isEmpty()){
            Node current = spis.peek();
            spis.remove();
            result.add(current);
            if (current.getLeft() != null){
                spis.add(current.getLeft());
            }
            if (current.getRight() != null){
                spis.add(current.getRight());
            }
        }

        return result;
    }

    private static boolean equalNodes(Node node1, Node node2){
        return node1.getValue() == node2.getValue() &&
               node1.getColor() == node2.getColor() &&
               (node1.getParent() == node2.getParent() ||
                    node1.getParent().getValue() == node2.getParent().getValue());
    }
}
