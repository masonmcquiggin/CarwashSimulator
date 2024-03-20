// MAIN
import java.util.*;

public class Main {

        public static int getServiceTime(String voucherCode, int washTime, int waxTime, int vacuumTime) {
        int time = 0; // S the total service time as zero.

        // Check the voucher code and calculate the service time respectively.
        if ("W".equals(voucherCode)) {
            // If the voucher code is "W", only add the wash time to the total.
            time += washTime;
        } else if ("WW".equals(voucherCode)) {
            // If the voucher code is "WW", add both wash and wax times to the total.
            time += washTime + waxTime;
        } else if ("WWV".equals(voucherCode)) {
            // If the voucher code is "WWV", add wash, wax, and vacuum times to the total.
            time += washTime + waxTime + vacuumTime;
        }

        // Return the total service time.
        return time;
    }

        public static String formatTime(int minutesAfter10) {
        // Calculate the minutes part of the time by taking the remainder after dividing by 60.
        int minutes = minutesAfter10 % 60;

        // Calculate the hours part of the time. First, convert the total minutes to hours by dividing by 60,
        // then use mod 12 to convert it into 12 hour format.
        int hours = (minutesAfter10 / 60) % 12;

        String amPm; // A variable to store the AM/PM part of the time.

        // Determine if the time is AM or PM.
        // If the total hours since midnight is less than 12, it's AM. Otherwise, it's PM.
        if ((minutesAfter10 / 60) < 12) {
            amPm = "AM";
        } else {
            amPm = "PM";
        }

        // 12 hour clock format, 0 hours is represented as 12.
        if (hours == 0) {
            hours = 12;
        }

        // Return thetime string.
        return String.format("%d:%02d %s", hours, minutes, amPm);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Create a scanner for input.
        System.out.println("Welcome to the FSC Car Clean Simulator"); // Welcome message.

        FSCcarwashQ outsideLine = new FSCcarwashQ();
        // Queue for customers outside the line.
        FSCcarwashQ washQueue = new FSCcarwashQ();
        // Queue for the car wash line.
        Stack<String> collectedVouchers = new Stack<>();
        // Stack for collected vouchers.

        // Read the maximum queue size.
        int maxQueueSize = Integer.parseInt(in.nextLine());
        // Read the number of simulation days.
        int numberOfDays = Integer.parseInt(in.nextLine());

        // LOOP for each day
        for (int day = 1; day <= numberOfDays; day++) {
            // Day header
            System.out.println("\n**********");
            System.out.println("Day " + day + ":");
            System.out.println("**********\n");

            // Reading time required for wash, wax, and vacuum services
            int washTime = in.nextInt();
            int waxTime = in.nextInt();
            int vacuumTime = in.nextInt();
            in.nextLine(); // Since nextInt was use have to use nextLine to consume the space.

            // Reading number of customers for the day and processes customer data and creates FSCmember objects
            int numberOfCustomers = Integer.parseInt(in.nextLine());
            // LOOP through each customer based on the total number of customers for the day.
            for (int i = 0; i < numberOfCustomers; i++) {
                String[] customerData = in.nextLine().split(" ");
                // Creating FSCmember from customer data
                int arrivalTime = Integer.parseInt(customerData[0]);
                int id = Integer.parseInt(customerData[1]);
                String firstName = customerData[2];
                String lastName = customerData[3];
                String code = customerData[4];

                // 600 = 10:00 AM
                arrivalTime = 600 + arrivalTime; // Converting arrival time to minutes since 10 AM

                // Create a new FSCmember object using the user's customer data
                FSCmember member = new FSCmember(arrivalTime, id, firstName, lastName, code);
                // Add the newly created FSCmember to the outsideLine queue.
                // This simulates the customer arriving at the car clean service and joining the waiting line.
                // The outsideLine queue represents customers who have arrived but are not yet being serviced.
                outsideLine.enqueue(member);
            }

            int nextServiceAvailabilityTime = 600;  // Car wash availability start time 10:00 AM
            // Setting it to null initially indicates that no customer is being serviced at the start of the simulation day.
            FSCmember currentMember = null;
            // Start of the day in minutes (10 AM).
            int currentTime = 600;

            // LOOP that continues as long as currentTime is less than 960 representing 4 PM in minutes
            while (currentTime < 960) {
                // Check if a car service has been completed
                // This code block is executed if there is a current member being serviced currentMember is not null
                // and the current time has reached or higher then the nextServiceAvailabilityTime.
                if (currentMember != null && currentTime >= nextServiceAvailabilityTime) {
                    // Print message with current time and customer's name
                    // This indicates that the car service for the current member is finished.
                    System.out.println(formatTime(currentTime) + ":  The car for " + currentMember.getFirstName() + " " + currentMember.getLastName() + " is now finished.");
                    // Calculate and display the waiting time in line for the current member
                    // This is the time from their arrival to when their service started.
                    System.out.println("        Waiting time in line: " + (currentMember.getTimeStarted() - currentMember.getArrivalTime()) + " minutes");
                    // Calculate and display the actual service time for the current member
                    // This depends on the service type indicated by their voucher code given by the user's input.
                    System.out.println("        Service time: " + getServiceTime(currentMember.getCode(), washTime, waxTime, vacuumTime) + " minutes");
                    // Calculate and display the total time spent by the current member in the simulation
                    // This includes both waiting + service time.
                    System.out.println("        Total time: " + ((currentMember.getTimeStarted() - currentMember.getArrivalTime()) + getServiceTime(currentMember.getCode(), washTime, waxTime, vacuumTime)) + " minutes");
                    // Reset currentMember to null indicating the carWash is now free for the next customer
                    currentMember = null;
                }

                // Check for LOWLY Minion arrival and process vouchers.
                if (!outsideLine.isEmpty() && "Z".equals(outsideLine.peek().getCode()) && outsideLine.peek().getArrivalTime() == currentTime) {
                    // This if-statement checks three conditions:
                    // The 'outsideLine' queue is not empty, meaning there are customers or a LOWLY Minion present.
                    // The customer at the front of the queue is the LOWLY Minion, identified by the code "Z".
                    // The arrival time of LOWLY Minion matches the current simulation time... Was getting errors of different time stamps
                    outsideLine.dequeue(); // Remove LOWLY Minion from the queue.
                    // Print a message indicating the LOWLY Minion's arrival and start of voucher collection
                    System.out.println(formatTime(currentTime) + ":  LOWLY Minion came and collected the following vouchers:");
                    // LOOP through the stack of collected vouchers
                    while (!collectedVouchers.isEmpty()) {
                        // For each voucher in the stack, pop it and print it
                        // This simulates the LOWLY Minion collecting all vouchers accumulated so far
                        System.out.println(" " + collectedVouchers.pop());
                        // After this block executes, all collected vouchers are printed and removed from the stack
                    }
                }

                // Check if the Carwash is available and if the current time is equal to or greater than the next service availability time
                if (currentMember == null && currentTime >= nextServiceAvailabilityTime) {
                    // This condition ensures that a new service can start only if there's no ongoing service currentMember is null
                    // and the Carwash is ready for the next car currentTime >= nextServiceAvailabilityTime.
                    // Check if there are members in the wash queue
                    if (!washQueue.isEmpty()) { // If the wash queue is not empty, start servicing the first member in the queue 
                        currentMember = washQueue.dequeue(); // Remove the first member from the wash queue to start their service
                        int serviceTime = getServiceTime(currentMember.getCode(), washTime, waxTime, vacuumTime);
                        // Calculate the service time for the current member based on their voucher code
                        nextServiceAvailabilityTime = currentTime + serviceTime; // Update the next service availability time by adding the calculated service time to the current time
                        currentMember.setTimeStarted(currentTime); // Set the service start time for the current member
                        // Print a message indicating the start of service for the current member
                        System.out.println(formatTime(currentTime) + ":  " + currentMember.getFirstName() + " " + currentMember.getLastName() + " has now started Class " + currentMember.getCode() + " service.");
                        // Push the current member's details onto the collected vouchers stack
                        collectedVouchers.push(currentMember.getFirstName() + " " + currentMember.getLastName() + " (" + currentMember.getID() + ")");
                        // This order ensures that the simulation first addresses customers who are already in the process of being serviced or waiting in line, 
                        // and only then  to new arrivals. 
                    } else if (!outsideLine.isEmpty() && outsideLine.peek().getArrivalTime() == currentTime) { // Else, if there is a new arrival at the current time, start servicing them.
                        currentMember = outsideLine.dequeue(); // Remove the arriving member from the outside line queue
                        // The arriving member becomes the current member being serviced
                        int serviceTime = getServiceTime(currentMember.getCode(), washTime, waxTime, vacuumTime);  // Calculate the service time for the current member
                        nextServiceAvailabilityTime = currentTime + serviceTime;   // Update the next service availability time
                        currentMember.setTimeStarted(currentTime);  // The service start time for the current customer
                        // Print a message indicating the start of service for the arriving member and immediately start
                        System.out.println(formatTime(currentTime) + ":  " + currentMember.getFirstName() + " " + currentMember.getLastName() + " arrived at the FSC Car Clean and immediately started Class " + currentMember.getCode() + " service.");
                        // Push the arriving member's details onto the collected vouchers stack
                        collectedVouchers.push(currentMember.getFirstName() + " " + currentMember.getLastName() + " (" + currentMember.getID() + ")"); // Collect voucher details.
                    }
                }

                // Check for new arrivals as long as there are customers in the outside line and it is their arrival time.
                while (!outsideLine.isEmpty() && outsideLine.peek().getArrivalTime() == currentTime) {
                    // This while LOOP processes all customers who have arrived at the current simulation time.
                    // Remove the first customer from the outside line queue to process them
                    FSCmember arrivingMember = outsideLine.dequeue(); // Dequeue the next member.
                    if (washQueue.size() < maxQueueSize) {  // Check if there is space in the wash queue
                        washQueue.enqueue(arrivingMember);  // Add the arriving customer to the wash queue
                        // Print a message indicating the customer's arrival and entry into the wash queue
                        System.out.println(formatTime(currentTime) + ":  " + arrivingMember.getFirstName() + " " + arrivingMember.getLastName() + " arrived at the FSC Car Clean and entered Wash Queue.");
                    } else {
                        // If the wash queue is full, no more customers can be added to it.  
                        // Print a message indicating the customer's is not able to service them due to a full queue and turn them away
                        System.out.println(formatTime(currentTime) + ":  " + arrivingMember.getFirstName() + " " + arrivingMember.getLastName() + " arrived at the FSC Car Clean. Unfortunately, the Wash Queue is full, and the customer left disappointed.");
                    }
                }
                // This LOOP will process all customers arriving at the same time, handling each based on the capacity of the wash queue.

                currentTime++; // Increment the simulations current time by one minute
                // This INCREMENT is important because we are going to stop time for a minute and read the inputs for that current time.

            }// End of the while loop for the current day

            // After the daily simulation loop ends...when the current time reaches 4 PM
            // the LOWLY Minion character performs its end of day pickup
            // Print message indicating the lowly minion's arrival at the end of the day
            // LOOP through the stack of collected vouchers as long as it is not empty
            System.out.println(formatTime(960) + ":  LOWLY Minion came and collected the following vouchers:");
            while (!collectedVouchers.isEmpty()) {
                System.out.println(" " + collectedVouchers.pop()); // Pop each voucher from the stack and print it
                // The pop operation is going to remove the voucher from the stack, meaning that it been collected by the minion.
            }
            // This is everything for ONE day, the entire sequence will repeat for the next day based on what the user's input says.
        }
    }
}
