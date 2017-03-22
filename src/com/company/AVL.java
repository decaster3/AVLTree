

/**
 * Created by rinathatipov on 17.10.16.
 */
public class AVL<T extends Comparable<T>> {
    //I have done implementation of a tree without a parent
    private TreeNode<T> root;
    private int size;
    private TreeNode<T> current = null;

    public void add(T data) {
        if (contains(data)){
            current.counter++;
            return;
        }
        TreeNode<T> newNode = new TreeNode<T>(data);
        root = add(root,newNode);
        size++;
    }

    public TreeNode successor(TreeNode<T> node) {
        node = succ(root, node.data, null);
        if (null != node) {
            return node;
        }
        return null;
    }

    private TreeNode succ(TreeNode<T> n, T x, TreeNode p) {
        if (null == n) {
            return null;
        }
        if (compare(x,n.data) < 0) {
            return succ(n.left, x, n);
        } else if (compare(x,n.data) > 0) {
            return succ(n.right, x, p);
        }
        if (null != n.right) {
            return min(n.right);
        }
        return p;
    }

    public void traverse(java.util.function.BiConsumer visitor) {
        TreeNode node = min(root);
        while (node != null) {
            visitor.accept(node.data, node.counter);
            node = successor(node);
        }
    }

    public boolean contains(T data) {
        if (isEmpty())return false;
        return contains(root,data);
    }

    private boolean contains(TreeNode<T> current, T n){
        if(current==null)return false;
        if(compare(current.data,n) == 0){
            this.current = current;
            return true;
        }
        else{
            if(contains(current.right,n)){return true;}
            else if(contains(current.left,n)){return true;}
            return false;
        }
    }

    private TreeNode<T> add(TreeNode<T> current, TreeNode<T> n){
        if (current == null){
            n.bf = 0;
            n.height = 0;
            return n;
        }
        if (compare(n.data,current.data)>0){
            current.right = rotate(add(current.right,n));
        }
        else{
            current.left = rotate(add(current.left,n));
        }
        current = rotate(current);

        return current;
    }

    public T remove(T data) {
        if(!contains(data)){
            return null;
        }
        root = rotate(remove(root,data));
        size--;
        return data;
    }
    public TreeNode min(TreeNode node){
        TreeNode min;
        min = node;
        while(min.left != null)
            min = min.left;
        return min;
    }

    private TreeNode<T> remove(TreeNode<T> current, T n){

        if (compare(current.data,n)==0){
            if(current.right == null && current.left== null){
                return null;
            }
            else if(current.right == null){
                return rotate(current.left);
            }
            else if(current.left == null){
                return rotate(current.right);
            }
            else{
                TreeNode<T> pre = current.left;
                TreeNode<T> predecessor;
                if (pre.right==null){
                    predecessor = pre;
                    predecessor.right = current.right;
                }
                else{
                    while(pre.right.right!=null){
                        pre = pre.right;
                    }
                    predecessor = pre.right;
                    pre.right = predecessor.left;
                    predecessor.left = current.left;
                    predecessor.right = current.right;
                }
                return predecessor;
            }
        }
        else{
            if (compare(n,current.data)>0){
                current.right = rotate(remove(current.right,n));
            }
            else{
                current.left = rotate(remove(current.left,n));
            }
            return rotate(current);
        }
    }

    private TreeNode<T> update(TreeNode<T> n) {

        int left,right;
        left = n.left!=null ? n.left.height : -1;
        right = n.right!=null ? n.right.height : -1;
        n.bf = left-right;
        n.height = (right>left ? right : left) +1;
        return n;
    }

    private TreeNode<T> rotate(TreeNode<T> node) {
        if(node == null)return node;
        node = update(node);
        if(node.bf<-1){
            if(node.right.bf>0){
                node.right = right(node.right);
                node = left(node);
            }
            else{
                node = left(node);
            }
        }
        else if(node.bf>1){
            if(node.left.bf<0){
                node.left = left(node.left);
                node = right(node);
            }
            else{
                node = right(node);
            }
        }
        return node;
    }

    private TreeNode<T> left(TreeNode<T> n) {
        TreeNode<T> newRoot = n.right;
        TreeNode<T> temp = n.right.left;
        n.right.left = n;
        n.right = temp;
        n = update(n);
        return newRoot;
    }

    private TreeNode<T> right(TreeNode<T> n) {
        TreeNode<T> newRoot = n.left;
        TreeNode<T> temp = n.left.right;
        n.left.right = n;
        n.left = temp;
        n = update(n);
        return newRoot;
    }

    public boolean isEmpty() {
        if (size==0) return true;
        return false;
    }

    private int compare(T first,T second){
        if (first==null && second == null){
            return 0;
        }
        else if(first==null){
            return 1;
        }
        else if(second==null){
            return -1;
        }
        else{
            return first.compareTo(second);
        }
    }
}
