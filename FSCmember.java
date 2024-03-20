// The FSCmember class represents an individual customer of the car wash service. It contains information about the customer and functionalities to manage that information.

public class FSCmember {
    
    // DATA MEMBERS
    
    private int arrivalTime;
    private int id;
    private String firstName;
    private String lastName;
    private String code;
    private int timeStarted;
    private FSCmember next;
    
    // CONSTRUCTORS

    public FSCmember(int arrivalTime, int id, String firstName, String lastName, String code) {
        this.arrivalTime = arrivalTime;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
        this.next = null;
    }
    
    // GETTERS AND SETTERS

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCode() {
        return code;
    }

    public void setTimeStarted(int timeStarted) {
        this.timeStarted = timeStarted;
    }

    public int getTimeStarted() {
        return timeStarted;
    }

    public FSCmember getNext() {
        return next;
    }

    public void setNext(FSCmember next) {
        this.next = next;
    }
}
