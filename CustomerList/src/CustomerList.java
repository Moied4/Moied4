class Customer {
    private String name;		//instance variables that belong to each Customer object
    private String city;		// private means only methods within the class can access them 
    private String phone;
    private int age;

    public Customer() {}		//default constructor

    public Customer(String name, String city, String phone, int age) {	 //specific or parameterized constructor specify values to initialize object with 
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.age = age;
    }
    //getters accessor methods
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getPhone() { return phone; }
    public int getAge() { return age; }
    //setters mutator methods
    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAge(int age) { this.age = age; }
    // Override toString method from Object class and return name city phone age as string representations of customer Object
    @Override
    public String toString() {
        return "Customer [name=" + name + ", city=" + city + ", phone=" + phone + ", age=" + age + "]"; 
    }
 
    @Override
    public boolean equals(Object obj) {	// Step 1: Check if both references point to the same object in memory
        if (this == obj) return true;	// Step 2: Check if the other object is null or if it is not of the same class
        if (obj == null || getClass() != obj.getClass()) return false;	
        // If either is true, they cannot be equal
        // Step 3: Cast the object to a Customer type so we can access its fields
        Customer other = (Customer) obj;
        return age == other.age &&					
               name.equals(other.name) &&
               city.equals(other.city) &&
               phone.equals(other.phone);
    }	 // Step 4: Check if all fields are equal (name, city, phone, age)
    	// If all match, the two Customer objects are considered equal
}

class ListElement {
    private ListElement prev; // reference previous ListElement Object
    private Customer ref;	// reference to customer Object
    private ListElement next;	// reference to next ListElement Object

    public ListElement() {}

    public ListElement(ListElement prev, Customer ref, ListElement next) {
        this.prev = prev; //set previous
        this.ref = ref;   //set customer data
        this.next = next; // set next
    }

    public ListElement getPrev() { return prev; }
    public void setPrev(ListElement prev) { this.prev = prev; }

    public Customer getRef() { return ref; }
    public void setRef(Customer ref) { this.ref = ref; }

    public ListElement getNext() { return next; }
    public void setNext(ListElement next) { this.next = next; }
}

class CustomerListImpl {		//doubly linked list where head points to 1st, tail points to last, size int to track size 
    private ListElement head;
    private ListElement tail;
    private int size;

    public CustomerListImpl() {		//default is null for string and 0 for int as the constructor initializes an empty list with no elements at all
        head = null;
        tail = null;
        size = 0;
    }
    // method that adds a customer to last and an element is added to list as newNode with a tail attachment and head null
    public void add(Customer customer) {	
        ListElement newNode = new ListElement(tail, customer, null);	// last node becomes previous of new, new becomes last
        if (tail != null) {			//last's next pointer updated if list is not empty since it has to be updated for the new
            tail.setNext(newNode);
        } else {					//else if the list was empty head and tail both = newNode 
            head = newNode;
        }
        tail = newNode;				// newNode is last so it is equal to tail and size is incremented
        size++;
    }

    public void add(int index, Customer customer) {	// if index is less than or index is greater than size of list return invalid index
        if (index < 0 || index > size) return; 

        if (index == size) {				//if index is equal to size then add customer using the method
            add(customer);
            return;
        }

        ListElement current = getNode(index);	//getNode at current index 
        ListElement newNode = new ListElement(current.getPrev(), customer, current);	//new ListElement before, customer, current

        if (current.getPrev() != null) {
            current.getPrev().setNext(newNode);		// if there is node before current update, link its next to the new node otherwise 
        } else {									// else newNode assigned to head 
            head = newNode;
        }
        current.setPrev(newNode);					//link newNode to previous
        size++;										// increment list size
    }

    public void clear() {							//method to empty list
        ListElement current = head;					// start at head
        while (current != null) {					// while loop as long as current is not null so we are not at end of list
            current.setRef(null);					// disconnects reference to customer object stored in current node for garbage collector
            ListElement temp = current;				// store current in temporary variable
            current = current.getNext();			// move to next 
            temp.setNext(null);						// Next and Prev set to null to break links to temp for garbage collector
            temp.setPrev(null);
        }
        head = tail = null;							//reset head tail and size after while loop ends for all nodes because the list is now empty
        size = 0;
    }

    public boolean contains(Customer customer) {	//boolean checks if customer exists in list and calls indexOf(customer) 
        return indexOf(customer) != -1;				// -1 means customer does not exist
    }

    public Customer get(int index) {			//returns customer at specified index unless index invalid returns null
        ListElement node = getNode(index);
        return node != null ? node.getRef() : null;
    }
    //returns first index at which specified customer appears and if there is no match return =1
    public int indexOf(Customer customer) {
        int idx = 0;
        for (ListElement current = head; current != null; current = current.getNext()) {
            if (current.getRef().equals(customer)) return idx;
            idx++;
        }
        return -1;
    }
    //returns last index at which specified customer appears and if there is no match return =1
    public int lastIndexOf(Customer customer) {
        int idx = size - 1;
        for (ListElement current = tail; current != null; current = current.getPrev()) {
            if (current.getRef().equals(customer)) return idx;
            idx--;
        }
        return -1;
    }
    // check wether list is empty and return true if its size is 0 otherwise false
    public boolean isEmpty() {
        return size == 0;
    }
    //remove first instance of specified customer from the list
    public boolean remove(Customer customer) {
        for (ListElement current = head; current != null; current = current.getNext()) {
            if (current.getRef().equals(customer)) {
                unlink(current);
                return true;	//if unlinked from current return true 
            }
        }						//otherwise return false
        return false;
    }
    	//method to remove customer at specified index and returns true if removed and false otherwise
    public boolean remove(int index) {
        ListElement node = getNode(index);
        if (node != null) {
            unlink(node);
            return true;
        }
        return false;
    }
    //Method that replaces the Customer at the specified index in the list with a new Customer, and returns the old Customer that was previously at that position
    public Customer set(int index, Customer customer) {
        ListElement node = getNode(index);	//check if index is invalid 
        if (node == null) return null;		// null if no node is found
        Customer old = node.getRef();		//Returns the original Customer that was previously in the node old;
        node.setRef(customer);
        return old;
    }
    // method to remove node from doubly linked list and adapting head and tail references and prev and next nodes
    private void unlink(ListElement node) {		
        ListElement prev = node.getPrev();	// get references for previous and next
        ListElement next = node.getNext();

        if (prev != null) prev.setNext(next);	//if node has prev then set prev's next pointer to node after this node
        else head = next;						// if this node has no previous it is the head so update list's head pointer to next

        if (next != null) next.setPrev(prev);	//if the node has a next node, set that next node’s prev pointer to skip this node and point to the node before it
        else tail = prev;						// Otherwise update list's tail pointer to previous

        node.setRef(null);						//clean up removed node by setting Ref, Next, Prev to null for garbage collector
        node.setNext(null);
        node.setPrev(null);
        size--;									//decrement size
    }

    private ListElement getNode(int index) {			//method to retrieve node at specific index in doubly linked list
        if (index < 0 || index >= size) return null;	// returns null if index is invalid 
        ListElement current;		
        //if index is in the first half of the list (index < size/2), start at the head and move forward (getNext()) until reaching the desired index.
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }//else index is in the second half of the list (index >= size/2), start at the tail and move backward (getPrev()) until reaching the desired index.
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }
        return current;	//return node of specified index, current
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("["); //builds string representation of the list
        ListElement current = head;
        while (current != null) {					//loop through each node while current != null
            sb.append(current.getRef().getName());	// append name of custoemr
            current = current.getNext();			// moves current to next node
            if (current != null) sb.append(", ");	// adds comma and space between nodes if current != null 
        }
        sb.append("]");								//append at end of string
        return sb.toString();						// returns string we built 
    }
}
					// Driver class
public class CustomerList {							
    public static void main(String[] args) {
        CustomerListImpl list1 = new CustomerListImpl();	//implementation class of an interface meaning it is a concrete implementation of the interface that defines methods
        CustomerListImpl list2 = new CustomerListImpl();
        CustomerListImpl list3 = new CustomerListImpl();

        Customer c1 = new Customer("Steeve", "Montreal", "514-666-1234", 15);
        Customer c2 = new Customer("David", "Toronto", "514-777-1234", 16);
        Customer c3 = new Customer("Belal", "Montreal", "514-999-1234", 16);
        Customer x1 = new Customer("Ana", "Montreal", "514-666-1234", 15);
        Customer x2 = new Customer("Lele", "Toronto", "514-777-1234", 16);
        Customer x3 = new Customer("Chunmei", "Montreal", "514-999-1234", 16);

        list1.add(c1);
        list1.add(c2);
        list1.add(c3);
        list1.add(x1);

        list2.add(x1);
        list1.add(c2);
        list2.add(c2);
        list2.add(x2);
        list2.add(x3);
        list3.add(x3);

        System.out.println("list1 " + list1);
        System.out.println("list2 " + list2);
        System.out.println("list3 " + list3);

        System.out.println(list1);

        System.out.println(list1.set(0, x3));
        System.out.println(list1);

        System.out.println(list3.contains(c3)); // false
        System.out.println(list3.contains(x3)); // true
        list3.clear();
        System.out.println(list3.contains(x3)); // false

        System.out.println("printing " + list1);

        list1.add(4, x1);					
        System.out.println(list1);
        list1.clear();

        System.out.println(list1);

        list1.add(x1);
        list1.add(0, x1);
        System.out.println(list1);

        list1.add(0, x1);
        System.out.println(list1);

        list1.clear();
    }
}