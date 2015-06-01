package stringToInt;

import core.BPlusTreeString60toInt;

/**
 *
 * @author yuantian
 *
 */

public class InternalNode implements Node {
	public String keys[] = new String[BPlusTreeString60toInt.maxSize + 3];
	public int size;
	public Node children[] = new Node[BPlusTreeString60toInt.maxSize + 3];

	public InternalNode(String key, Node child) {
		this.size = 0;
		this.add(key, child);
	}

	public InternalNode() {
		this.size = 0;
	}

	public int getSize() {
		return size;
	}

	public String getKey(int i) {
		return keys[i];
	}

	public Node getChild(int i) {
		return children[i];
	}

	public void add(String key, Node child) {
		if (size == 0) {
			this.keys[size + 1] = key;
			this.children[size+1] = child;
			this.size++;
			return;
		}
		else if(key.compareTo(this.keys[size]) > 0) {
			this.keys[size+1] = key;
			this.children[size+1] = child;
			this.size++;
			return;
		}
		else {
			for(int i = 1; i <= this.size; i++) {
				if(key.compareTo(this.keys[size]) < 0) {
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
	public Integer getValue(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String key, Integer value) {
		// TODO Auto-generated method stub

	}

}
