import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * The Node class acts as an auxiliary to the FileSystem class. Its objects
 * represent the nodes of a tree.
 * 
 * @author João Matos nº 56292
 *
 * @param <E> - The type of information to be stored in the node
 */

public class Node<E> {

	private E data;
	private Node<E> parent;
	private ArrayList<Node<E>> children = new ArrayList<>();

	/**
	 * The constructor builds a new node.
	 * 
	 * @param data   - The information to be stored in the new node
	 * @param parent - The parent of the new node (Should be null for the root)
	 * @param path   - The path to the new node (Can be null for the root)
	 */

	public Node(E data, Node<E> parent) {
		this.data = data;
		this.parent = parent;
	}

	/**
	 * Gets the information stored on the node
	 * 
	 * @return the information stored on the node
	 */
	public E getData() {
		return data;
	}

	/**
	 * Gets the parent of the selected node
	 * 
	 * @return the parent of the node
	 */
	public Node<E> getParent() {
		return parent;
	}

	/**
	 * Gets the list of children of the selected node
	 * 
	 * @return a list with the children of the selected node
	 */
	public List<Node<E>> getChildren() {
		return children;
	}

	/**
	 * Reconstructs the path to the node, returning it as a String with the data
	 * contained in the subsequent parent nodes separated by "/" until the current
	 * node. Does not include the name of the root node.
	 * 
	 * @return the path to the node
	 */
	public String getPath() {
		StringBuilder sb = new StringBuilder();
		Stack<Node<E>> family = new Stack<>();
		Node<E> parent = getParent();
		while (parent != null && parent.getParent() != null) {
			family.add(parent);
			parent = parent.getParent();
		}
		while (!family.isEmpty()) {
			sb.append("/" + family.pop().getData());
		}
		sb.append("/" + data);
		return sb.toString();
	}

	/**
	 * Checks if the node has any children
	 * 
	 * @return true if the chosen node has children, false if not
	 */
	public boolean hasChildren() {
		return children.size() != 0;
	}

	/**
	 * Adds a new child to the chosen node
	 * 
	 * @param newChild - the node to be added as child
	 */
	public void addChild(Node<E> newChild) {
		children.add(newChild);
	}

	/**
	 * Removes a child from the chosen node
	 * 
	 * @param child - the child to be removed
	 */
	public void removeChild(Node<E> child) {
		children.remove(child);
	}
}
