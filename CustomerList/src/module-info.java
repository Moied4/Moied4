package CustomerList;

import CustomerList.Customer;
import CustomerList.CustomerList;
import CustomerList.ListElement;

class Customer {
    private String name;
    private String city;
    private String phone;
    private int age;

    public Customer() {}

    public Customer(String name, String city, String phone, int age) {
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.age = age;
    }

    public String getName() { return name; }
    public String getCity() { return city; }
    public String getPhone() { return phone; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAge(int age) { this.age = age; }

    public String toString() {
        return "Customer [name=" + name + ", city=" + city + ", phone=" + phone + ", age=" + age + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer other = (Customer) obj;
        return age == other.age &&
               name.equals(other.name) &&
               city.equals(other.city) &&
               phone.equals(other.phone);
    }
}

class ListElement {
    private ListElement prev;
    private Customer ref;
    private ListElement next;

    public ListElement() {}

    public ListElement(ListElement prev, Customer ref, ListElement next) {
        this.prev = prev;
        this.ref = ref;
        this.next = next;
    }

    public ListElement getPrev() { return prev; }
    public void setPrev(ListElement prev) { this.prev = prev; }

    public Customer getRef() { return ref; }
    public void setRef(Customer ref) { this.ref = ref; }

    public ListElement getNext() { return next; }
    public void setNext(ListElement next) { this.next = next; }
}

class CustomerList {


	private ListElement head;
    private ListElement tail;
    private int size;

    public CustomerList() {
        head = null;
        tail = null;
        size = 0;
    }

    public CustomerList(ListElement head, ListElement tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    public ListElement getHead() { return head; }
    public ListElement getTail() { return tail; }
    public int getSize() { return size; }

    public void setHead(ListElement head) { this.head = head; }
    public void setTail(ListElement tail) { this.tail = tail; }
    public void setSize(int size) { this.size = size; }

    public void add(Customer customer) {
        ListElement newNode = new ListElement(tail, customer, null);
        if (tail != null) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    public void add(int index, Customer customer) {
        if (index < 0 || index > size) return;

        if (index == size) {
            add(customer);
            return;
        }

        ListElement current = getNode(index);
        ListElement newNode = new ListElement(current.getPrev(), customer, current);

        if (current.getPrev() != null) {
            current.getPrev().setNext(newNode);
        } else {
            head = newNode;
        }
        current.setPrev(newNode);
        size++;
    }

    public void clear() {
        ListElement current = head;
        while (current != null) {
            current.setRef(null);
            ListElement temp = current;
            current = current.getNext();
            temp.setNext(null);
            temp.setPrev(null);
        }
        head = tail = null;
        size = 0;
    }

    public boolean contains(Customer customer) {
        return indexOf(customer) != -1;
    }

    public Customer get(int index) {
        ListElement node = getNode(index);
        return node != null ? node.getRef() : null;
    }

    public int indexOf(Customer customer) {
        int idx = 0;
        for (ListElement current = head; current != null; current = current.getNext()) {
            if (current.getRef().equals(customer)) return idx;
            idx++;
        }
        return -1;
    }

    public int lastIndexOf(Customer customer) {
        int idx = size - 1;
        for (ListElement current = tail; current != null; current = current.getPrev()) {
            if (current.getRef().equals(customer)) return idx;
            idx--;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean remove(Customer customer) {
        for (ListElement current = head; current != null; current = current.getNext()) {
            if (current.getRef().equals(customer)) {
                unlink(current);
                return true;
            }
        }
        return false;
    }

    public boolean remove(int index) {
        ListElement node = getNode(index);
        if (node != null) {
            unlink(node);
            return true;
        }
        return false;
    }

    public Customer set(int index, Customer customer) {
        ListElement node = getNode(index);
        if (node == null) return null;
        Customer old = node.getRef();
        node.setRef(customer);
        return old;
    }

    private void unlink(ListElement node) {
        ListElement prev = node.getPrev();
        ListElement next = node.getNext();

        if (prev != null) prev.setNext(next);
        else head = next;

        if (next != null) next.setPrev(prev);
        else tail = prev;

        node.setRef(null);
        node.setNext(null);
        node.setPrev(null);

        size--;
    }

    private ListElement getNode(int index) {
        if (index < 0 || index >= size) return null;

        ListElement current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }
        return current;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        ListElement current = head;
        while (current != null) {
            sb.append(current.getRef().getName());
            current = current.getNext();
            if (current != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

class Driver {
    public static void main(String[] args) {
        CustomerList list1 = new CustomerList();
        CustomerList list2 = new CustomerList();
        CustomerList list3 = new CustomerList();

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

        int ret = 0;
        if (ret == 1) System.exit(0);

        System.out.println(list3.contains(c3));
        System.out.println(list3.contains(x3));
        list3.clear();
        System.out.println(list3.contains(x3));

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
