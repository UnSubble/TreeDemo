
public class Main {

	public static void main(String[] args) {
		TreeDemo<Integer> tree = new TreeDemo<Integer>();
		tree.insert(2);
		tree.insert(4);
		tree.insert(3);
		tree.insert(1);
		System.out.println(tree);
	}

}
