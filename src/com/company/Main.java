
public class Main {

    public static void main(String[] args) {
	    AVL<String> avl = new AVL();
        Calculate calculate = new Calculate();
        StringBuilder answer = new StringBuilder();
        InputOutput inputOutput = new InputOutput();
        String[] lines = inputOutput.read("input.txt");
        avl = calculate.getAVL(lines);
        avl.traverse((c, o2) -> {
            answer.append(c + ":" + o2 + " ");
        });
        inputOutput.write("output.txt", answer.toString() );
    }
}
