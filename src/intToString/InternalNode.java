package intToString;

import core.BPlusTreeIntToString60;

/**
 *
 * @author yuantian
 *
 */

public class InternalNode implements Node {
	public int keys[] = new int[BPlusTreeIntToString60.maxSize + 3];
	public int size;
	public Node children[] = new Node[BPlusTreeIntToString60.maxSize + 3];

	public InternalNode(int key, Node child) {
		this.size = 0;
		this.add(key, child);
	}

	public InternalNode() {
		this.size = 0;
	}

	public int getSize() {
		return size;
	}

	public int getKey(int i) {
		return keys[i];
	}

	public Node getChild(int i) {
		return children[i];
	}

	public void add(int key, Node child) {
		if (size == 0) {
			this.keys[size + 1] = key;
			this.children[size+1] = child;
			this.size++;
			return;
		}
		else if(key > this.keys[size]) {
			this.keys[size+1] = key;
			this.children[size+1] = child;
			this.size++;
			return;
		}
		else {
			for(int i = 1; i <= this.size; i++) {
				if(key<this.keys[i]){
					for(int j = size+1; j > i; j--) {
						this.keys[j] = this.keys[j-1];
						this.children[j] = this.children[j-1];
					}
					this.keys[i] = key;
					this.children[i] = child;
					this.size++;
					break;
				}
			}
		}
	}

	public void remove(int i) {
		for(int j = i; j <= this.size; j++) {
			this.keys[j] = this.keys[j+1];
		}
		this.size--;
	}

	public String toString() {
		String s = "Internal: ";
		for(int i = 0; i <= this.size; i++) {
			s += this.keys[i];
			s += "->";
			s += this.children[i];
			s += ",";
		}
		return s;
	}

	@Override
	public String getValue(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int key, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(int key, int value) {
		// TODO Auto-generated method stub

	}

}
