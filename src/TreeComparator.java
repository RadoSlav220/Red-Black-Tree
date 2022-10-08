import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeComparator {
    public static boolean equalTrees(Node root1, Node root2){
        ArrayList<Node> nodes1 = getNodes(root1);
        ArrayList <Node> nodes2 = getNodes(root2);
        if (nodes1.size() != nodes2.size()){
            return false;
        }
        for (int i=0; i<nodes1.size(); ++i){
            //If written using gererics, != must be replaced with 'equals()'
            if (nodes1.get(i).getValue() != nodes2.get(i).getValue() ||
                nodes1.get(i).getColor() != nodes2.get(i).getColor() ){
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
}
