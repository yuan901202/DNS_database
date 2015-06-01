package intToString;

/**
 *
 * @author yuantian
 *
 */

public class Pair {
	public int newKey;
	public Node rightChild;

	public Pair() {}

	public Pair(int newKey, Node rightChild) {
		this.newKey = newKey;
		this.rightChild = rightChild;
	}

	public int getNewKey() {
		return newKey;
	}

	public void setNewKey(int newKey) {
		this.newKey = newKey;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
}
