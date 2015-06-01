package stringToInt;

import core.BPlusTreeString60toInt;

/**
 *
 * @author yuantian
 *
 */

public class LeafNode implements Node {
	public String[] keys = new String[BPlusTreeString60toInt.maxSize + 3];
	public Integer values[] = new Integer[BPlusTreeString60toInt.maxSize + 3];
	public int size;
	public LeafNode nextLeaf;

	public LeafNode() {
		this.size = 0;
	}

	public LeafNode(String key, int value) {
		this.size = 0;
		this.add(key, value);
	}

	public int getSize() {
		return size;
	}

	public String getKey(int i) {
		return keys[i];
	}

	public Integer getValue(int i) {
		return values[i];
	}

	public void add(String key, Integer value) {
		if (this.size == 0) {
			this.keys[size] = key;
			this.values[size] = value;
			this.size++;
			return;
		}
		else if(key.compareTo(this.keys[size-1]) > 0) {
			this.keys[size] = key;
			this.values[size] = value;
			this.size++;
			return;
		}
		else if(key.compareTo(this.keys[0]) < 0) {
			for(int i = this.size-1; i >= 0; i--) {
				this.keys[i+1] = this.keys[i];
				this.values[i+1] = this.values[i];
			}
			this.keys[0] = key;
			this.values[0] = value;
			this.size++;
			return;
		}
		for(int i = 0; i <= this.size-1; i++) {
			if(key.compareTo(this.keys[i]) > 0 && key.compareTo(this.keys[i+1]) < 0) {
				for(int j = size; j > i+1; j--) {
					this.keys[j] = this.keys[j-1];
					this.values[j] = this.values[j-1];
				}
				this.keys[i+1] = key;
				this.values[i+1] = value;
				this.size++;
				break;
			}
		}
	}

	public void remove(int i) {
		for(int j = i; j < this.size-1; j++) {
			this.keys[j] = this.keys[j+1];
			this.values[j] = this.values[j+1];
		}
		this.size--;
	}

	public String toString() {
		String s = "";
		for(int i = 0; i < this.size; i++) {
			s += this.keys[i];
			s += "->";
			s += this.values[i];
			s += "\n";
		}
		if(this.nextLeaf != null) {
			s += this.nextLeaf.toString();
		}
		return s;
	}

	@Override
	public Node getChild(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String key, Node child) {
		// TODO Auto-generated method stub

	}

}
