package core;

import stringToInt.Pair;
import stringToInt.InternalNode;
import stringToInt.LeafNode;
import stringToInt.Node;

/**
 * @author yuantian
 *
 */

/**
  Implements a B+ tree in which the keys  are Strings (with
  maximum length 60 characters) and the values are integers
*/

public class BPlusTreeString60toInt {
	private Node root;
	public static final int maxSize = 60;

	/**
     * Returns the integer associated with the given key,
     * or null if the key is not in the B+ tree.
     */
    public Integer find(String key) {
    	//YOUR CODE HERE
    	if (root == null) {
			return null;
		}
		return find(key, root);
    }


    private Integer find(String key, Node node) {
    	if(node instanceof LeafNode) {
			for(int i = 0; i <= node.getSize() - 1; i++) {
				if(key.compareTo(node.getKey(i)) == 0) {
					return node.getValue(i);
				}
			}
			return null;
		}
		if(node instanceof InternalNode) {
			for(int i = 1; i <= node.getSize(); i++) {
				if(key.compareTo(node.getKey(i)) < 0){
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
     * @param value
     * @param key
     * @return whether pair was successfully added.
     */
    public boolean put(String key, Integer value) {
    	//YOUR CODE HERE
    	if(root == null) {
			LeafNode leaf = new LeafNode(key, value);
			root = leaf;
			return true;
		}
		else {
			Pair Pair = Add(key, value, this.root);
			if(Pair != null) {
				InternalNode node = new InternalNode();
				node.size = 1;
				node.children[0] = root;
				node.keys[1] = Pair.newKey;
				node.children[1] = Pair.rightChild;
				this.root = node;
				return true;
			}
		}

		return false;
    }


    public Pair Add(String key, Integer value, Node node) {
		if(node instanceof LeafNode) {
			if(((LeafNode) node).size < BPlusTreeIntToString60.maxSize + 1) {
				node.add(key, value);
				return null;
			}
			else {
				return splitLeaf(key, value, node);
			}
		}

		if(node instanceof InternalNode) {
			for(int i = 1; i <= node.getSize(); i++) {
				if(key.compareTo(((InternalNode)node).keys[i]) < 0) {
					Pair pair = Add(key, value, ((InternalNode)node).children[i-1]);

					if(pair == null) {
						return null;
					}
					else {
						return dealWithPromote(pair,node);
					}
				}
			}

			Pair pair = Add(key, value, ((InternalNode)node).children[node.getSize()]);
			if(pair == null) {
				return null;
			}
			else {
				return dealWithPromote(pair, node);
			}
		}
		return null;

	}

	private Pair dealWithPromote(Pair Pair, Node node) {
		if(Pair == null) {
			return null;
		}

		node.add(Pair.newKey, Pair.rightChild);
		if(node.getSize() <= maxSize) {
			return null;
		}

		InternalNode sibling = new InternalNode();
		int mid = (node.getSize()/2)+1;
		int j = 1;
		int nodeSize = ((InternalNode)node).size;
		String promoteKey = ((InternalNode)node).keys[mid];

		for(int i = mid+1;i <= nodeSize; i++) {
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


	private Pair splitLeaf(String key, Integer value, Node node) {
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

}
