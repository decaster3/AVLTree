

/**
 * Created by rinathatipov on 25.10.16.
 */
public class Calculate {
    //here we doother manipulates with tree
    String symbols = ".*-:;<=>?@^_`";
    String[] arrOfSymbols = symbols.split("");

    public AVL getAVL(String[] i){
        AVL<String> avl = new AVL();
        for (int n = 0; n < i.length; n++ ){
            avl.add(i[n]);
        }
        for(int m = 0; m < arrOfSymbols.length; m++){
            avl.remove(arrOfSymbols[m]);
        }
        return avl;
    }
}
