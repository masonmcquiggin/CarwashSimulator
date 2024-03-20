// The FSCvouchers class implements a stack data structure specifically for managing FSCvoucher.

public class FSCvouchers {

    // DATA MEMBERS
    
    private FSCvoucher top;

    // CONSTRUCTORS
    
    public FSCvouchers() {
        top = null; // Initially, the stack is empty, so top is set to null.
    }

    // HELPER METHODS
    
    // Checks if the stack is empty.
    public boolean isEmpty() {
        return top == null; // Returns true if top is null, meaning the stack is empty.
    }

    // Adds a new voucher to the top of the stack.
    public void push(FSCvoucher voucher) {
        top = push(top, voucher); // Calls the private push method to add the voucher to the stack.
    }

    // Helper method to push a voucher onto the stack.
    private FSCvoucher push(FSCvoucher top, FSCvoucher voucher) {
        voucher.setNext(top); // Sets the current top as the next element of the new voucher.
        return voucher; // The new voucher becomes the new top of the stack.
    }

    // Removes and returns the top voucher from the stack.
    public FSCvoucher pop() {
        if (!isEmpty()) {
            FSCvoucher poppedVoucher = top; // Store the current top voucher.
            top = top.getNext(); // Set the next voucher as the new top.
            return poppedVoucher; // Return the popped voucher.
        }
        return null; // If stack is empty, return null.
    }

    // Returns the top voucher from the stack without removing it.
    public FSCvoucher peek() {
        if (!isEmpty()) {
            return top; // Return the top voucher if stack is not empty.
        }
        return null; // If stack is empty, return null.
    }

    // Prints all vouchers in the stack.
    public void PrintStack() {
        PrintStack(top);
    }

    // Helper method to print the stack.
    private void PrintStack(FSCvoucher top) {
        FSCvoucher helpPtr = top;
        while (helpPtr != null) {
            System.out.print("ID: " + helpPtr.getID() + ", "); // Print the ID of each voucher in the stack.
            helpPtr = helpPtr.getNext(); // Move to the next voucher.
        }
        System.out.println();
    }

    // Searches for a voucher with an ID in the stack.
    public boolean search(int ID) {
        return search(top, ID);
    }

    // Helper method to search for a voucher in the stack.
    private boolean search(FSCvoucher p, int ID) {
        FSCvoucher helpPtr = p;
        while (helpPtr != null) {
            if (helpPtr.getID() == ID) {
                return true; // Return true if a voucher with the specified ID is found.
            }
            helpPtr = helpPtr.getNext(); // Move to the next voucher.
        }
        return false; // Return false if the voucher is not found.
    }
}
