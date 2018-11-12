package BST;

import java.util.stream.IntStream;

/**
 * Created by Khmely on 10.05.2018.
 */
public class Main {

    public static void main(String[] args) {

        BST bst = new BST();

        IntStream.of(5,3,7,1,4,0,2,6,12,9).forEach((i)-> bst.insert(i));

        System.out.println(bst.toStringInOrder());
        System.out.println(bst.toStringPreOrder());
        System.out.println(bst.toStringPostOrder());

        System.out.println("Min: " + bst.min());
        System.out.println("Max: " + bst.max());

        System.out.println(bst.find(2));
        System.out.println(bst.find(11));
        System.out.println("Height: " + bst.findHeight());
        System.out.println("Number Of Nodes: " + bst.numberOfNodes());
        System.out.println("Internal Nodes: " + bst.internalNodes());
        System.out.println("External Nodes: " + bst.externalNodes());
        System.out.println("Number Of Leafs: " + bst.numberOfLeafs());


        bst.toStringLevelOrder();
        System.out.println("Successor for 4: " + bst.successor(4));
        System.out.println("Predecessor for 6: " + bst.predecessor(6));
        bst.delete(5);
        bst.toStringLevelOrder();

    }
}
