public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        int[] arr = {5, 12, 9, 3, 19, 22, -4};
        for (int i : arr){
            tree.add(i);
        }
        tree.printSorted();
    }
}