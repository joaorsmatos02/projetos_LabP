import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * The FileSystem class implements the IFileSystem interface
 * 
 * @author João Matos nº 56292
 *
 */

public class FileSystem implements IFileSystem {

	private Node<String> root = new Node<String>("/", null);

	/**
	 * Creates a new file in the given path with the chosen name. The file can only
	 * be created if there are no other files with the same name on the chosen
	 * location
	 * 
	 * @param path - The path in which to create the file
	 * @return true if the file was created successfully, false if not
	 */
	public boolean createFile(String path) {
		boolean result = true;
		String[] location = path.split("/");
		Node<String> currentDirectory = root; // diretorio inicial
		for (int i = 1; i < location.length; i++) {
			boolean found = false;
			if (currentDirectory.getChildren() != null) {
				for (int j = 0; j < currentDirectory.getChildren().size() && !found; j++) {
					if (currentDirectory.getChildren().get(j).getData().equals(location[i])) {
						found = true;
						currentDirectory = currentDirectory.getChildren().get(j);
					}
				}
			}
			if (!found) {
				Node<String> newNode = new Node<String>(location[i], currentDirectory); // criar caso não exista
				currentDirectory.addChild(newNode);
				currentDirectory = currentDirectory.getChildren().get(currentDirectory.getChildren().size() - 1);
			} else if (i == location.length - 1) { // caso tenha sido encontrado um ficheiro com o mesmo nome e path
				result = false;
			}
		}
		return result;
	}

	/**
	 * Finds all files with the chosen name
	 * 
	 * @param filename - The name of the file to find
	 * @return a list containing the paths of all instances of the file
	 */
	public List<String> find(String filename) {
		List<String> result = new ArrayList<String>();
		ArrayList<Node<String>> files = terminalNodes(root); // lista de todos os nodes terminais na árvore
		for (Node<String> n : files) {
			if (n.getData().equals(filename)) { // caso tenham o nome desejado
				StringBuilder sb = new StringBuilder();
				sb.append(n.getPath());
				result.add(sb.toString());
			}
		}
		return result;
	}

	/**
	 * Checks if the given path corresponds to an existing file
	 * 
	 * @param path - The path to be checked
	 * @return true if such file exists, false if not
	 */
	public boolean existsFile(String path) {
		boolean result = true;
		Node<String> lastOnPath = followPath(path); // percorrer o path até ao ultimo existente
		if (!lastOnPath.getPath().equals(path)) // se path do ultimo existente não corresponder
			result = false;
		return result;
	}

	/**
	 * Removes the file with the corresponding path. The file will be removed if it
	 * exists. If it does not exist, the method will return false
	 * 
	 * @param path - The path of the file to be removed
	 * @return true if the file was removed, false if not
	 */
	public boolean removeFile(String path) {
		boolean result = true;
		Node<String> lastOnPath = followPath(path);
		if (path.equals(lastOnPath.getPath())) // se path do ultimo existente corresponder
			lastOnPath.getParent().removeChild(lastOnPath);
		else
			result = false; // caso contrario nada foi removido
		return result;
	}

	/**
	 * Delivers a textual representation of the current file system. The returned
	 * String is composed of the paths of all terminal nodes in the system.
	 * 
	 * @return a String with a textual representation of the file system
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ArrayList<Node<String>> files = terminalNodes(root);
		for (Node<String> n : files) { // iterar por todos os nodes terminais
			sb.append(n.getPath() + "\r\n"); // e adicionar os paths de todos
		}
		return sb.toString();
	}

	/**
	 * Retrives a list of all terminal nodes in the subtree starting on the given
	 * node
	 * 
	 * @param start - The node in which to start searching
	 * @return a list of all terminal nodes connected to the given node
	 */
	public ArrayList<Node<String>> terminalNodes(Node<String> start) {
		ArrayList<Node<String>> result = new ArrayList<>();
		Queue<Node<String>> queue = new LinkedList<>();
		queue.add(start);
		while (!queue.isEmpty()) { // algoritmo do tipo breadth
			Node<String> current = queue.remove();
			List<Node<String>> children = current.getChildren();
			for (Node<String> n : children) {
				queue.add(n);
				if (!n.hasChildren()) { // caso seja terminal
					result.add(n);
				}
			}
		}
		return result;
	}

	/**
	 * Starts on the root of the tree and follows the given path until its last
	 * existing node.
	 * 
	 * @param path - the path to follow
	 * @return the last real (existing) node in the path
	 */
	public Node<String> followPath(String path) {
		String[] location = path.split("/");
		Node<String> currentNode = root;
		boolean end = false;
		for (int i = 1; i < location.length && !end; i++) {
			boolean found = false;
			if (currentNode.getChildren() != null) {
				for (int j = 0; j < currentNode.getChildren().size() && !found; j++) { // percorrer lista de filhos
					if (currentNode.getChildren().get(j).getData().equals(location[i])) { // caso tenha sido encontrado
						found = true;
						currentNode = currentNode.getChildren().get(j); // atualizar node mais recente
					}
				}
			}
			if (!found)
				end = true; // caso nao exista terminar execução
		}
		return currentNode;
	}

}
