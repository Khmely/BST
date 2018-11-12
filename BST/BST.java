package BST;

/**
 * Created by Khmely on 10.05.2018.
 */
public class BST {

    Node root;

    BST(){
        root = null;
    }


    void insert(int n){
        root = insert(n, root);
    }

    private Node insert(int n, Node r) {
        if(r == null)
            r = new Node(n);
        else {
            Node temp = null;
            if(n < r.value){
                temp = insert(n, r.left);
                r.left = temp;
            }else if(n > r.value){
                temp = insert(n, r.right);
                r.right = temp;
            }else{
                System.out.println("There is Node like this");
            }
        }
        return r;
    }

    String toStringInOrder(){
        String b = "In Order: ";
        if(root!=null){
            b+=root.inOrder();
        }else{
            b+="Empty tree";
        }
        return b;
    }

    String toStringPreOrder(){
        String b = "Pre Order: ";
        if(root!=null){
            b+=root.preOrder();
        }else{
            b+="Empty tree";
        }
        return b;
    }
    String toStringPostOrder(){
        String b = "Post Order: ";
        if(root!=null){
            b+=root.postOrder();
        }else{
            b+="Empty tree";
        }
        return b;
    }

    String find(int x){
        Node t = search(x);
        return t!=null ? "Find " + x : "Not Find " + x;
    }

    private Node search(int n) {
        Node r = root;
        while(r!=null && r.value != n){
            r = n < r.value ? r.left : r.right;
        }
        return r;
    }

    int min(){
        return min(root).value;
    }
    private Node min(Node n){
        while (n.left != null)
            n = n.left;
        return n;
    }
    int max(){
        return max(root).value;
    }
    private Node max(Node n){
        while (n.right != null)
            n = n.right;
        return n;
    }

    public int findHeight(){
        if(root == null){
            return -1;
        }
        else{
            return findHeight(root);
        }
    }

    private int findHeight(Node n) {
        if(n == null || (n.left == null && n.right == null))
            return 0;
        return Math.max(findHeight(n.left), findHeight(n.right)) + 1;
    }

    public int numberOfNodes(){
        if(root == null){
            return -1;
        }
        else{
            return numberOfNodes(root);
        }
    }
    private int numberOfNodes(Node n){
        int c =  1;
        if (n == null)
            return 0;
        else
        {
            c += numberOfNodes(n.left);
            c += numberOfNodes(n.right);
            return c;
        }
    }

    public int numberOfLeafs(){
        if(root == null){
            return -1;
        }
        else{
            return numberOfLeafs(root);
        }
    }

    private int numberOfLeafs(Node n){
        if (n == null)
            return 0;
        if (n.left == null && n.right == null)
            return 1;
        else
            return numberOfLeafs(n.left) + numberOfLeafs(n.right);
    }

    public int internalNodes()
    {
        return numberOfNodes()-numberOfLeafs();
    }

    public int externalNodes(){
        if(root == null){
            return -1;
        }
        else{
            return externalNodes(root);
        }
    }
    private int externalNodes(Node n)
    {
        if(n == null)
            return 0;
        if(n.left == null && n.right == null)
            return 2;
        else if (n.left == null || n.right == null)
            return 1;
        else
            return externalNodes(n.left) + externalNodes(n.right);
    }

    void toStringLevelOrder(){
        System.out.println("Level Order:");
        int h = findHeight();
        for (int i=0; i<=h; i++) {
            printGivenLevel(root, i);
            System.out.println();
        }
    }
    private void printGivenLevel(Node n, int level){
        if (n == null)
            return;
        if (level == 0)
            System.out.print(n.value + " ");
        else if (level > 0)
        {
            printGivenLevel(n.left, level-1);
            printGivenLevel(n.right, level-1);
        }
    }
    public int successor(int n){
        Node x = search(n);
        Node result = null;
        if(x!=null)
            result = successor(x);
        if(result == null){
            return -1;
        }
        return result.value;
    }

    private Node successor(Node n){
        if( n.right != null )
            return min(n.right);

        Node succ = null;
        Node r = root;
        while (r != null)
        {
            if (n.value < r.value)
            {
                succ = r;
                r = r.left;
            }
            else if (n.value > r.value)
                r = r.right;
            else
                break;
        }
        return succ;
    }
    public int predecessor(int n){
        Node x = search(n);
        Node result = null;
        if(x!=null)
            result = predecessor(x);
        if(result == null){
            return -1;
        }
        return result.value;
    }

    private Node predecessor(Node n){
        if( n.left != null )
            return max(n.left);

        Node succ = null;

        Node r = root;
        while (r != null)
        {
            if (n.value > r.value)
            {
                succ = r;
                r = r.right;
            }
            else if (n.value < r.value)
                r = r.left;
            else
                break;
        }
        return succ;
    }

    void delete(int key){
        Node l = search(key);
        root = deleteRec(root, key);
    }

    Node deleteRec(Node n, int key){
        if (n == null)  return n;

        if (key < n.value)
            n.left = deleteRec(n.left, key);
        else if (key > n.value)
            n.right = deleteRec(n.right, key);
        else {
            if (n.left == null)
                return n.right;
            else if (n.right == null)
                return n.left;

            n.value = minValue(n.right);
            n.right = deleteRec(n.right, n.value);
        }
        return n;
    }

    int minValue(Node r){
        int minv = r.value;
        while (r.left != null)
        {
            minv = r.left.value;
            r = r.left;
        }
        return minv;
    }

    class Node{
        int value;
        Node left;
        Node right;
        Node(int n){
            value = n;
            left = right = null;
        }
        Node(int n, Node l, Node r){
            value = n;
            left = l;
            right = r;
        }
        String inOrder(){
            String b = "";
            if(left!=null){
                b+=left.inOrder();
            }
            b+= value + ", ";
            if(right!=null){
                b+= right.inOrder();
            }
            return b;
        }
        String preOrder(){
            String b = "";
            b+= value + ", ";
            if(left!=null){
                b+=left.preOrder();
            }
            if(right!=null){
                b+= right.preOrder();
            }
            return b;
        }
        String postOrder(){
            String b = "";
            if(left!=null){
                b+= left.postOrder();
            }
            if(right!=null){
                b+= right.postOrder();
            }
            b+= value + ", ";
            return b;
        }
    }

}
