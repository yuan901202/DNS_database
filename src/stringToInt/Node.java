package stringToInt;

/**
 *
 * @author yuantian
 *
 */

public interface Node {
	public int getSize();
	public String getKey(int i);
	public Integer getValue(int i);
	public Node getChild(int i);
	public void add(String key, Integer value);
	public void add(String key, Node child);
	public void remove(int i);
}
