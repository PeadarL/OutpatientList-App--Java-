
public class Node 
{
	public Object data; // Instead of instantiating an object, use a generic so that it can be easily replaced and is not tightly coupled
	public Node next;
	public Node previous;
	
	public Node(Object data, Node next, Node previous)
	{
		this.data = data;
		this.next = next;
		this.previous = previous;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrevious() {
		return previous;
	}
	public void setPrevious(Node previous) {
		this.previous = previous;
	}	
	@Override
	public String toString() {
		return data.toString();
	}	
}
