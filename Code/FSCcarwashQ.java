// The FSCcarwashQ class is designed to manage a queue of customers at a car wash using a linked list structure


    public class FSCcarwashQ {

        // DATA MEMBERS

        // Tracks the first and last customer in the car wash line.
        private FSCmember front;
        private FSCmember back;
        private int numCustomers; // Amount of customers.

        // CONSTRUCTORS

        // Constructor to create an empty queue.
        public FSCcarwashQ() {
            front = back = null; // Initially, there are no customers in line.
            numCustomers = 0; // The count of customers starts at zero.
        }

        // HELPER METHODS

        // Check if the queue is empty.
        public boolean isEmpty() {
            return front == null; // If the front is null, the queue is empty.
        }

        // Add a customer to the end of the queue.
        public void enqueue(FSCmember member) {
            if (isEmpty()) {
                front = back = member; // If the queue is empty, the new member is both front and back.
            } else {
                back.setNext(member); // Otherwise, add the member to the end of the queue.
                back = member; // The new member becomes the new back of the queue.
            }
            numCustomers++; // Increase the total count of customers.
        }

        // Remove and return the customer from the front of the queue.
        public FSCmember dequeue() {
            if (!isEmpty()) {
                FSCmember removedMember = front; // Take the first customer in line.
                front = front.getNext();        // The next customer becomes the front.
                if (front == null) {
                    back = null;                // If the queue is now empty, set the back to null.
                }
                removedMember.setNext(null);    // Disconnect the removed member from the queue.
                numCustomers--;                 // Decrease the total count of customers.
                return removedMember;           // Return the removed customer.
            }
            return null; // If the queue was empty, there's no customer to remove.
        }

        // See who is at the front of the queue without removing them.
        public FSCmember peek() {
            return front;
        }

        // Get the number of customers currently in the queue.
        public int size() {
            return numCustomers;
        }
    }
