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
	
	public void remove(T val) {
		Node target = searchNode(val);
		if (target != null) {
			remove(target.left == null ? target.right : target.left, target);
		}
			
	}
	
	private void remove(Node node, Node target) {
		if (node == null) {
			if (target == root) 
				root = null;
			else
				deleteNode(root, target);
		} else {
			T temp = node.val;
			node.val = target.val;
			target.val = temp;
			remove(node.left == null ? node.right : node.left, node);
		}		
	}
	
	private void deleteNode(Node node, Node target) {
		if (node.left == target)
			node.left = null;
		else if (node.right == target)
			node.right = null;
		else {
			if (node.left != null)
				deleteNode(node.left, target);
			if (node.right != null)
				deleteNode(node.right, target);
		}		
	}
	
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
		return root == null ? false : (searchNode(val) != null);
	}
	
	private Node searchNode(T val) {
		return searchLeft(root, val);
	}
	
	private Node searchRight(Node node, T val) {
		if (node == null) 
			return null;
		if (node.val.compareTo(val) > 0)
			return searchLeft(node.left, val);
		if (node.val.compareTo(val) < 0)
			return searchRight(node.right, val);
		return node;
	}
	
	private Node searchLeft(Node node, T val) {
		if (node == null) 
			return null;
		if (node.val.compareTo(val) > 0)
			return searchLeft(node.left, val);
		if (node.val.compareTo(val) < 0)
			return searchRight(node.right, val);
		return node;
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
