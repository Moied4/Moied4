public class StackOfIntegers {
    private int[] elements;	
    private int size;
    public static final int DEFAULT_CAPACITY = 16;
    // Final int value is default capacity of set 
    // Static means belongs to class public means can be accessed from other classes 
    // Constructor with default capacity
    public StackOfIntegers() {
        this(DEFAULT_CAPACITY);
    }

    // Constructor with custom capacity 
    public StackOfIntegers(int capacity) {
        elements = new int[capacity];
    }

    // Push element onto stack
    public void push(int value) {
        if (size >= elements.length) {
            // Grow array if needed because size >
            int[] newElements = new int[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        elements[size++] = value;
    }

    // Pop element from top of stack and handle RuntimeException possibly decrement elements 
    public int pop() {
        if (empty()) {
            throw new RuntimeException("Stack is empty");
        }
        return elements[--size];
    }

    // Peek at top element without removing to handle exception
    public int peek() {
        if (empty()) {
            throw new RuntimeException("Stack is empty");
        }
        return elements[size - 1];
    }

    // Check if stack is empty
    public boolean empty() {
        return size == 0;
    }

    // Get current size of stack
    public int getSize() {
        return size;
    }
}
