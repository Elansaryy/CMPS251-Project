package project;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * this is the code that contains most of the logic
 *
 * @author all
 * @version 1.0
 * 7-5-2023
 *
 */

public class Slot implements Serializable {

    private static int idCounter;
    private int id;
    private LocalTime time;
    private LocalDate date;
    private boolean isBooked;
    private Service allocatedService;
    private Patient allocatedPatient;
    public Slot(){
        id=++idCounter;
    }
    public Slot(LocalTime time, LocalDate date, boolean isBooked, Service allocatedService) {
        this();
        setTime(time);
        setDate(date);
        this.setBooked(isBooked);
        this.setAllocatedService(allocatedService);
    }
    public Slot( LocalTime time, LocalDate date, boolean isBooked, Service allocatedService, Patient allocatedPatient){
        this(time,date,isBooked,allocatedService);
        this.setAllocatedPatient(allocatedPatient);
    }
    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Slot.idCounter = idCounter;
    }
    public int getId() {
        return id;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {

        if (time.isBefore(LocalTime.of(7,0))||(time.plusMinutes(30)).isAfter(LocalTime.of(21, 0))) {
            System.out.println("Sorry this time is not allowed");
        }
        else this.time=time;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public boolean isBooked() {
        return isBooked;
    }
    public void setBooked(boolean booked) {
        isBooked = booked;
    }
    public Service getAllocatedService() {
        return allocatedService;
    }
    public void setAllocatedService(Service allocatedService) {
        this.allocatedService = allocatedService;
    }

    public Patient getAllocatedPatient() {
        return allocatedPatient;
    }
    public void setAllocatedPatient(Patient allocatedPatient) {
        this.allocatedPatient = allocatedPatient;
    }
    @Override
    public String toString() {
        return String.format("%-15d%-16s%-16s%-23s%-32s%s", getId(), getTime(), getDate(), (this.isBooked() ? "NO" : "YES")
                , (getAllocatedService() == null ? "No Allocated Service" : getAllocatedService().getTitle())
                , (getAllocatedPatient() == null ? "No Allocated Patient" : getAllocatedPatient().getName()));
    }


}








