import java.util.ArrayList;
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
	
	/*public void remove(T val) {
		if (search(val)) {
			for (int c : )
		}			
	}
	*/
	
	public int getDepth() {
		return root == null ? 0 : (getDepth(root) + 1);
	}
	
	private int getDepth(Node node) {
		int l = 0;
		int r = 0;
		if (node.left != null) 
			l = getDepth(node.left) + 1;
		if (node.right != null)
			r = getDepth(node.right) + 1;
		return Math.max(l, r);
	}
	
	public int getDepthOfValue(T val) {
		return search(val) ? getDepthOfValue(root, val, 1) : -1;
	}
	
	private int getDepthOfValue(Node node, T val, int d) {
		if (node.compareTo(val) > 0)
			return getDepthOfValue(node.left, val, d + 1);
		else if (node.compareTo(val) < 0)
			return getDepthOfValue(node.right, val, d + 1);
		return d;
	}
	
	public boolean search(T val) {
		return root == null ? false : searchLeft(root, val);
	}
	
	private boolean searchRight(Node node, T val) {
		if (node == null) 
			return false;
		if (node.val.compareTo(val) > 0)
			return searchLeft(node.left, val);
		if (node.val.compareTo(val) < 0)
			return searchRight(node.right, val);
		return true;
	}
	
	private boolean searchLeft(Node node, T val) {
		if (node == null) 
			return false;
		if (node.val.compareTo(val) > 0)
			return searchLeft(node.left, val);
		if (node.val.compareTo(val) < 0)
			return searchRight(node.right, val);
		return true;
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
