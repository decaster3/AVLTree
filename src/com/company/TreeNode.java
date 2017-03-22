

/**
 * Created by rinathatipov on 17.10.16.
 */
public class TreeNode<K extends Comparable<K>> {
    //I have done implementation of a tree without a parent
    public  K data;
    int counter;
    public TreeNode<K> left, right;
    public  int height;
    public int bf;

    public TreeNode(K data) {
        this.data = data;
        counter = 1;
    }
}
