
public class SelectionSort {
	
    public DoubleLinkedList selectionSortPriority(DoubleLinkedList list)
    {
    	while(!checkOrder(list))
    	{
			for(Node node1=list.getHead(); node1!=null;node1=node1.getNext())
			{
			 Node min = node1;
			 for(Node node2=node1; node2 !=null; node2=node2.getNext())
			 {
				 Patient temp1= (Patient) node1.getData();
				 Patient temp2= (Patient) node2.getData();
				 if(temp1.getPriority() > temp2.getPriority())
				 {
					 min=node2;
				 }
				 
			 }
			 Node temp = new Node(node1.getData(), node1.getNext(), node1.getPrevious());
			 node1.setData(min.getData());
			 min.setData(temp.getData());
		    }	
    	}
    	return list;
    }
    public boolean checkOrder(DoubleLinkedList list)
    {
    	Node node1 = list.getHead();
    	boolean result = true;
    	for(node1=list.getHead(); node1!=null;node1=node1.getNext())
    	{
		for(Node node2=node1; node2 !=null; node2=node2.getNext()){
    		Patient temp1= (Patient) node1.getData();
    		Patient temp2= (Patient) node2.getData();
    			 if(temp1.getPriority() > temp2.getPriority())
    			 {
    				 result =  false;
    				 System.out.println("Here"); 
    			 }		 
		}
    	}
    	return result; 
    }
}



/*    	for(int i=0; i  < list.getSize(); i++)
    	{
    		Node second = head.getNext();
    		Patient patientOne = (Patient) head.getData();
	    	for(int j=i+1; j  < list.getSize(); j++) //change to get method for size
	    	{
	    		Patient patientTwo = (Patient) second.getData();
	    		if(patientOne.getPriority() > patientTwo.getPriority())
	    		{
	    			list.swap(head, second);
	    		}
	    		second = second.getNext();
	    	}
	    		
	    	head=head.getNext();
    	}*/