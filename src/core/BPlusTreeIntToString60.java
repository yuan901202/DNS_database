package core;
import intToString.Pair;
import intToString.InternalNode;
import intToString.LeafNode;
import intToString.Node;

/**
 * @author yuantian
 *
 */

/**
Implements a B+ tree in which the keys are integers and the
values are Strings (with maximum length 60 characters)
*/

public class BPlusTreeIntToString60 {

	private Node root;
	public static final int maxSize = 60;

	/**
     * Returns the String associated with the given key,
     * or null if the key is not in the B+ tree.
     */
	public String find(int key) {
		//YOUR CODE HERE
		if (root == null) {
			return null;
		}
		return find(key,root);
	}

	public String find(int key, Node node) {
		if(node instanceof LeafNode) {
			for(int i = 0; i <= node.getSize() - 1; i++) {
				if(key == node.getKey(i)) {
					return node.getValue(i);
				}
			}
			return null;
		}
		if(node instanceof InternalNode) {
			for(int i = 1; i <= node.getSize(); i++) {
				if(key < node.getKey(i)) {
					return find(key,node.getChild(i-1));
				}
			}
			return find(key,node.getChild(node.getSize()));
		}
		return null;
	}

	/**
     * Stores the value associated with the key in the B+ tree.
     * If the key is already present, replaces the associated value.
     * If the key is not present, adds the key with the associated value
     * @param key
     * @param value
     * @return whether pair was successfully added.
     */
	public boolean put(int key, String value) {
		//YOUR CODE HERE
		if(root == null) {
			LeafNode leaf = new LeafNode(key, value);
			root = leaf;
			return true;
		}
		else {
			Pair pair = add(key, value, this.root);
			if(pair != null) {
				InternalNode node = new InternalNode();
				node.size = 1;
				node.children[0] = root;
				node.keys[1] = pair.newKey;
				node.children[1] = pair.rightChild;
				this.root = node;
				return true;
			}
		}

		return false;
	}
	public Pair add(int key, String value, Node node) {
		if(node instanceof LeafNode) {
			if(((LeafNode) node).size < BPlusTreeIntToString60.maxSize + 1) {
				node.add(key, value);
				return null;
			}
			else {
				return splitLeaf(key,value,node);
			}
		}

		if(node instanceof InternalNode) {
			for(int i = 1; i <= node.getSize(); i++) {
				if(key < ((InternalNode)node).keys[i]) {
					Pair temp = add(key, value, ((InternalNode)node).children[i-1]);
					if(temp == null) {
						return null;
					}
					else {
						return dealWithPromote(temp, node);
					}
				}
			}
			Pair temp = add(key, value, ((InternalNode)node).children[node.getSize()]);
			if(temp == null) {
				return null;
			}
			else {
				return dealWithPromote(temp, node);
			}
		}
		return null;

	}

	public Pair dealWithPromote(Pair pair, Node node) {
		if(pair == null) {
			return null;
		}

		node.add(pair.newKey, pair.rightChild);
		if(node.getSize() <= maxSize) {
			return null;
		}

		InternalNode sibling = new InternalNode();
		int mid = (node.getSize()/2)+1;
		int j = 1;
		int nodeSize = ((InternalNode)node).size;
		int promoteKey = ((InternalNode)node).keys[mid];

		for(int i = mid+1; i <= nodeSize; i++) {
			sibling.keys[j] = node.getKey(i);
			sibling.size++;
			j++;
		}

		j = 0;
		for(int i = mid; i <= nodeSize; i++) {
			sibling.children[j] = node.getChild(i);
			j++;
			node.remove(i);
		}
		return new Pair(promoteKey, sibling);
	}

	public Pair splitLeaf(int key, String value, Node node) {
		node.add(key, value);
		LeafNode sibling = new LeafNode();

		int mid = (node.getSize()+1)/2;
		int nodeSize = node.getSize();

		for(int i = mid; i < nodeSize; i++) {
			sibling.add(node.getKey(i), node.getValue(i));
		}

		for(int i = mid; i < nodeSize; i++) {
			node.remove(i);
		}

		sibling.nextLeaf = ((LeafNode) node).nextLeaf;
		((LeafNode) node).nextLeaf = sibling;
		return new Pair(sibling.keys[0], sibling);
	}

	public String toString() {
		Node toReturnString = root;
		while(!(toReturnString instanceof LeafNode)) {
			toReturnString = toReturnString.getChild(0);
		}

		if(toReturnString instanceof LeafNode){
			return toReturnString.toString();
		}
		return "FAIL";
	}

}
