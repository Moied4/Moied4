public class TestStack {
    public static void main(String[] args) {
        StackOfIntegers stack = new StackOfIntegers();

        System.out.println("\nPushing elements onto stack:");
        
        // Push integers 1 to 10 using for loop to repeat code for 10 elements 
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
            System.out.println("Pushed: " + i);
        }

        System.out.println("\nPopping elements from stack:");

        // Pop and print all elements using while loop that runs as long as stack is not empty
        while (!stack.empty()) {
            System.out.println("Popped: " + stack.pop());
        }
    }
}
//The stack follows the LIFO (Last In, First Out) principle.
//The last number pushed (10) is the first one to be popped, and so on down to 1
//Push 10 Pop all 