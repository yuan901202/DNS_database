package stringToInt;

/**
 *
 * @author yuantian
 *
 */

public class Pair {
	public String newKey;
	public Node rightChild;


	public Pair() {}

	public Pair(String newKey, Node rightChild) {
		this.newKey = newKey;
		this.rightChild = rightChild;
	}

	public String getNewKey() {
		return newKey;
	}

	public void setNewKey(String newKey) {
		this.newKey = newKey;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
}
