// The FSCvoucher class represents a voucher, for the car wash, with various attributes to track each customer's details and their service status.

public class FSCvoucher {

    // DATA MEMBERS
    
    private int arrivalTime;
    private int ID;
    private String firstName;
    private String lastName;
    private String code;
    private int timeStarted;
    private int timeFinished;
    private FSCvoucher next;

    // CONSTRUCTORS

    public FSCvoucher() {
    }
    
    public FSCvoucher(int arrivalTime, String code, int ID, String firstName, String lastName) {
        this.arrivalTime = arrivalTime;
        this.ID = ID;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public FSCvoucher(int arrivalTime, int ID, String firstName, String lastName, String code) {
        this.arrivalTime = arrivalTime;
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
        this.timeStarted = 0;
        this.timeFinished = 0;
        this.next = null;
    }

    public FSCvoucher(int arrivalTime, int ID, String firstName, String lastName, String code, int timeStarted, int timeFinished, FSCvoucher next) {
        this.arrivalTime = arrivalTime;
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
        this.timeStarted = timeStarted;
        this.timeFinished = timeFinished;
        this.next = next;
    }

    // GETTERS AND SETTERS
    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(int timeStarted) {
        this.timeStarted = timeStarted;
    }

    public int getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(int timeFinished) {
        this.timeFinished = timeFinished;
    }

    public FSCvoucher getNext() {
        return next;
    }

    public void setNext(FSCvoucher next) {
        this.next = next;
    }

}
