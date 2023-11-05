import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TreeDemo <T extends Comparable<T>> implements Iterable<T>{
	private Node root;

	private class Node implements Comparable<T>{
		public T val;
		public Node left;
		public Node right;
		
		public Node(T val) {
			this.val = val;
			left = null;
			right = null;
		}

		@Override
		public int compareTo(T o) {
			return val.compareTo(o);
		}		
	}
	
	public void insert(T val) {
		if (root == null) {
			root =  new Node(val);
			return;
		}
		insert(root, val);
	}
	
	private void insert(Node node, T val) {
		if (node.compareTo(val) > 0) {
			if (node.left == null) node.left = new Node(val);
			else insert(node.left, val);
		} else if (node.compareTo(val) < 0) {
			if (node.right == null) node.right = new Node(val);
			else insert(node.right, val);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return toList().iterator();
	}
	
	private List<T> toList() {
		List<T> list = new ArrayList<>();
		toList(root, list);
		return list;
	}

	public Object[] toArray() {
		return toList().toArray();
	}
	
	private void toList(Node node, List<T> list) {
		if (node.left != null) toList(node.left, list);
		list.add(node.val);
		if (node.right != null) toList(node.right, list);	
	}
	
	@Override
	public String toString() {
		return toList().toString();
	}

}
