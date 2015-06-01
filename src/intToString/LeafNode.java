package intToString;

import core.BPlusTreeIntToString60;
import core.DNSDB;

/**
 *
 * @author yuantian
 *
 */

public class LeafNode implements Node {
	public int keys[] = new int[BPlusTreeIntToString60.maxSize + 3];
	public String values[] = new String[BPlusTreeIntToString60.maxSize + 3];
	public int size;
	public LeafNode nextLeaf;

	public LeafNode() {
		this.size = 0;
	}

	public LeafNode(int key, String value) {
		this.size = 0;
		this.add(key, value);
	}

	public int getSize() {
		return size;
	}

	public int getKey(int i) {
		return keys[i];
	}

	public String getValue(int i) {
		return values[i];
	}

	public void add(int key, String value) {
		if (this.size == 0) {
			this.keys[size] = key;
			this.values[size] = value;
			this.size++;
			return;
		}
		else if(key > this.keys[size - 1]) {
			this.keys[size] = key;
			this.values[size] = value;
			this.size++;
			return;
		}
		else if(key < this.keys[0]) {
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
			if(key > this.keys[i] && key < this.keys[i+1]){
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
			s += DNSDB.IPToString(this.keys[i]);
			s += " -> ";
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
	public void add(int key, Node child) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(int key, int value) {
		// TODO Auto-generated method stub

	}

}
