package dhariwal;

public class LinkedList {
	Node head;	//defining the head node
	int sum = 0;

	
	public void insert(Double value) {
		Node neww = new Node();	//creating a new node
		neww.value = value;	//assigning the new node to the value to be added
		
		if (head == null) {	//if the list is empty
			head = neww;
		}
		else {
			Node temp = head;
			while (temp.next != null) {	//check to see where the list ends
				temp = temp.next;
			}
			temp.next = neww;	//add the new value to the end of the list
		}
	}
	
	public void insertAtHead (Double value) {
		Node neww = new Node();	//creating a new node
		neww.value = value;	//assigning the new node to the value to be added
		neww.next = head;
		head = neww;	//add the value at head node
	}
	
	public void insertAtThis(int index, Double value) {
		Node node = new Node();
		node.value = value;
		
		if(index == 0) {	//if we want to add the value at the beginning
			insertAtHead(value);
		}
		else {
			Node temp = head;

			for (int i = 0; i < index - 1; i++) {	//traverse through the list till we reach the desired index node
				temp = temp.next;
			}
			node.next = temp.next;
			temp.next = node;
		}
	}
	
	public void deleteAt(int index) {
		if (index == 0) {	//if we want to delete the head value
			head = head.next;
		}
		else {
			Node temp = head;
			Node n;
			for (int i = 0; i < index - 1; i++) {
				temp = temp.next;
			}
			n = temp.next;
			temp.next = n.next;
		}
	}
	
	public int size() {
		Node n = head;	//temp node starting at head
		int size = 1;
		
		while (n.next != null) {
			size += 1;	//increase size by 1 when we hit a number
			n = n.next;	//go to the next node
		}
		return size;
	}
	
	public void print() {
		Node n = head;	//starting from the head
		
		while (n.next != null) {	//as long as we're not at the end of the list
			System.out.println(n.value);
			n = n.next;	//go to the next node
		}
		System.out.println(n.value);	//the last value
	}
	
	public Double mean() {
		double mean =  0; //initialising mean
		if (head == null) {
			System.out.println("Add something to the list first!");	//check if list is empty
		}
		else {
			Node n = head;	//temp node starting head
			while (n.next != null) {	//as long we're not at the end of the list
				sum += n.value;	//sum of all values in the list
				n = n.next;	//hopping on to next node
			}
			sum += n.value;	//adding the last value
			int size = size();	//size of the list
			double len = Double.valueOf(size);	//converting the size from integer to double
			mean = sum/len;	//average formula
		}
		return mean;
	}

	public Double stdDev() {
		int size = size();
		double summ = 0;	//initialising the sum of the squares of the difference between the value and mean
		double temp = 0;	//temp number to go through each difference of value and mean
		double mx = mean();
		double stdDev = 0;	//initialising standard deviation number
		if (head == null) {	//check to see list is empty
			System.out.println("Add something to the list first!");
		}
		else {
			Node n = head;
			
			while (n.next != null) {
				temp += (n.value - mx);	//value - mean
				summ += Math.pow(temp, 2);	//temp^2
				temp = 0;	//re-initialising temp to 0
				n = n.next; //go to the next node
			}
			temp += (n.value - mx);	//accounting for the last value in the list
			summ += Math.pow(temp, 2);
			temp = 0;
		}
		temp = summ/size;
		stdDev = Math.sqrt(temp);
		return stdDev;
	}
	
}
