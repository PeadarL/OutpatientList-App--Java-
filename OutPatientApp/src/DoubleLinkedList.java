import java.util.NoSuchElementException;

//Change to private
public class DoubleLinkedList {
	public Node head;
	public Node tail;
	public int size;

	public DoubleLinkedList(){
		this.head = null;
		this.size = 0;
	}
	
	
	public Node getHead() {
		return head;
	}
	public Node getTail() {
		return tail;
	}
	public int getSize() {
		return size;
	}

	
	public void addFront(Object data)
	{
		Node temp = new Node(data,head,null);
		if(head!=null)
		{
			head.previous=temp;
		}
		head=temp;
		if(tail==null)
		{
			tail=temp;
		}
		size++;
	}	
	public void addRear(Object data)
	{
        Node tmp = new Node(data, null, tail);
        if(tail != null) 
        {
        	tail.next = tmp;
        }
        tail = tmp;
        if(head == null) 
        { 
        	head = tmp;
        }
        size++;
	}
	
    public void iterateForward()
    {
        
        System.out.println("iterating forward..");
        Node tmp = head;
        while(tmp != null)
        {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }
    public void iterateBackward()
    {    
        System.out.println("iterating backword..");
        Node tmp = tail;
        while(tmp != null)
        {
            System.out.println(tmp.data);
            tmp = tmp.previous;
        }
    }
    
    public Object removeFirst() 
    {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = head;
        head = head.next;
        head.previous = null;
        size--;
        System.out.println("deleted: "+tmp.data);
        return tmp.data;
    }   
    public Object removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = tail;
        tail = tail.previous;
        tail.next = null;
        size--;
        System.out.println("deleted: "+tmp.data);
        return tmp.data;
    }   

    public void swap(Node one, Node two)
    {
    	Node temp = new Node(null,null,null);
    	temp.data = one.getData();
    	one.data = two.getData();
    	two.data = temp.getData();
    }
}
