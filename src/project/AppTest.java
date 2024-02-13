package project;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * the app tester that has the menu
 *
 * @author all
 * @version 6.1
 */
public class AppTest {
    public static void main(String[] args) throws IOException {
        new AppTest();
    }

    int pId;
    char ch;
    int slotId;
    int patientId;
    int day;
    int month;
    int year;
    int hour;
    int minutes;
    LocalDate date;
    LocalTime time;
    int choice;
    int serviceId;
    String title;
    ObjectInputStream in;
    ObjectOutputStream out;
    AdminApp test;
    boolean valid;
    Scanner kb = new Scanner(System.in);

    public AppTest() throws IOException {
        /**
         * @author Mohamed Elansari
         * This method reads from a file
         */
        try {
            in = new ObjectInputStream(new FileInputStream("patients.dat"));
            ArrayList<Patient> pList = (ArrayList<Patient>) in.readObject();
            in = new ObjectInputStream(new FileInputStream("services.dat"));
            ArrayList<Service> sList = (ArrayList<Service>) in.readObject();
            in = new ObjectInputStream(new FileInputStream("slots.dat"));
            ArrayList<Slot> slList = (ArrayList<Slot>) in.readObject();
            test = new AdminApp(pList, slList, sList);
            Slot.setIdCounter(test.getMaxID(slList));
        } catch (Exception e) {
            test = new AdminApp();
        }


        while (true) {
            try {
                print();
                choice = kb.nextInt();
                while (choice < 0 || choice > 20) {
                    System.out.print("Please enter a value from 1-20 or zero to exit: ");
                    choice = kb.nextInt();
                }
            } catch (InputMismatchException errr) {
                System.err.println("please enter an integer from 0-20: ");
                kb.nextLine();
                continue;
            }
            /**
             * @author abdallah
             * switch handling
             * 13-5-2023
             */
            switch (choice) {
/**
 * @author abdullah
 */

                case 1:

                    pId = test.getPatientIdFromUser();
                    test.scan.nextLine();
                    Patient p = new Patient(pId, test.getNameFromUser(), test.getResidencyTypeFromUser());
                    System.out.println(test.addPatient(p));
                    break;

                /**
                 * @author Abdulrahman Q. Alshawabkeh 13-5-2023
                 */
                case 2:
                    System.out.println(test.deletePatient(test.getPatientIdFromUser()));
                    test.scan.nextLine();
                    break;
                /**
                 * @author Abdulrahman Q. Alshawabkeh 14-5-2023
                 */
                case 3:
                    pId = test.getPatientIdFromUser();
                    if (test.findPatient(pId) == null) {
                        System.out.println("Patient not found");
                    } else {
                        System.out.println(
                                "PID\t Name\t\t Residency Type\n-----------------------------------"
                                        + "-------------");
                        System.out.println(test.findPatient(pId));
                    }
                    test.scan.nextLine();
                    break;
                /**
                 * @author Abdulrahman Q. Alshawabkeh 14-5-2023
                 */
                case 4:
                    System.out.println(test.modifyPatient(test.getPatientIdFromUser()));
                    break;

                /**
                 * @author Mohamed Elansari
                 * switch handling
                 * 15-5-2023
                 */

                case 5:
                    serviceId = test.getServiceIdFromUser();
                    test.scan.nextLine();
                    int maxSlots = 0;
                    int pricePerSlot = 0;
                    while (maxSlots == 0) {

                        switch (test.getServiceTitleFromUser().toUpperCase()) {
                            case "PROCEDURE":
                                maxSlots = 15;
                                pricePerSlot = 50;
                                title = "PROCEDURE";
                                break;
                            case "GENERIC":
                                maxSlots = 20;
                                pricePerSlot = 100;
                                title = "GENERIC";
                                break;
                            case "SPECIALIZED":
                                maxSlots = 10;
                                pricePerSlot = 150;
                                title = "SPECIALIZED";
                                break;
                            case "OPERATION":
                                maxSlots = 5;
                                pricePerSlot = 1000;
                                title = "OPERATION";
                                break;
                            default:
                                System.out.print("Wrong value entered!\n");
                                continue;

                        }
                    }
                    Service ss = new Service(title, serviceId, maxSlots, pricePerSlot);
                    System.out.println(test.addService(ss));
                    break;
/**
 * @author Mohamed Elansari
 * switch handling
 * 15-5-2023
 */

                case 6:
                    System.out.println(test.modifyService(test.getServiceIdFromUser()));
                    break;
/**
 * @author Mohamed Elansari
 * switch handling
 * 15-5-2023
 */

                case 7:
                    System.out.println(test.deleteService(test.getServiceIdFromUser()));
                    test.scan.nextLine();
                    break;
/**
 * @author Mohamed Elansari
 * switch handling
 * 15-5-2023
 */

                case 8:
                    test.findService(test.getServiceTitleFromUser());
                    break;
                /**
                 * @author abdallah
                 * switch handling
                 * 13-5-2023
                 */
                case 9:
                    System.out.println(test.addEmptySlot());
                    break;
                /**
                 * @author Abdulrahman Shabban
                 * 17-5-2023
                 */
                case 10:
                    ArrayList<Slot> avslotsb = new ArrayList<>(test.getAvailableSlotsPerServiceByDate
                            (test.getServiceIdFromUser(), test.getDateFromUser()));
                    if (avslotsb.size()==0)
                        System.out.println("No available slots for the given service on the specified date.");
                    else {
                        System.out.println("ID\t           Time\t\t\t   Date\t           Availability\t " +
                                "         Service\t                      Patient\n---------------" +
                                "------------------------------------------------------"
                                + "-------------------------------------------------------");
                        for (Slot s : avslotsb)
                            System.out.println(s);
                    }
                    test.scan.nextLine();
                    break;

                /**
                 * @author Abdulrahman Shabban
                 * 17-5-2023
                 */
                case 11:
                    test.showAllServices();
                    break;
                /**
                 * @author Abdulrahman Shabban
                 * 17-5-2023
                 */
                case 12:
                    test.showAllPatients();
                    break;
                /**
                 * @author Abdulrahman Shabban
                 * 17-5-2023
                 */
                case 13:
                    test.showAllSlots();
                    break;
                /**
                 * @author Abdulrahman Shabban
                 * 17-5-2023
                 */
                case 14:
                    ArrayList<Slot> avslots = new ArrayList<>(test.getAvailableSlots());
                    if (avslots.size()==0)
                        System.out.println("No available slots found.");
                    else {
                        System.out.println("ID\t           Time\t\t\t   Date\t           Availability\t " +
                                "         Service\t                      Patient\n---------------" +
                                "------------------------------------------------------"
                                + "-------------------------------------------------------");
                        for (Slot s : avslots)
                            System.out.println(s);
                    }
                    break;
                /**
                 * @author abdullah
                 * 13-5-2023
                 */

                case 15:
                    System.out.println(test.deleteSlot(test.getServiceIdFromUser(), test.getDateFromUser(), test.getTimeFromUser()));
                    test.scan.nextLine();
                    break;

                /**
                 * @author abdallah
                 * switch handling
                 * 13-5-2023
                 */
                case 16:
                    pId = test.getPatientIdFromUser();
                    date = test.getDateFromUser();
                    time = test.getTimeFromUser();
                    serviceId= test.getServiceIdFromUser();
                    while (!(test.isFree(date, time, pId))||(!(test.isServiceFree(date, time, serviceId)))) {
                        if(!(test.isFree(date, time, pId))){
                            System.out.println("You have an appointment at the same date and time please change them");
                            date = test.getDateFromUser();
                            time = test.getTimeFromUser();
                        }
                        else {
                            System.out.println("This service is already reserved for this time");
                            date = test.getDateFromUser();
                            time = test.getTimeFromUser();
                        }
                    }

                    System.out.println(test.reserveSlot(test.getSlotIdFromUser(), serviceId, pId, date, time));
                    test.scan.nextLine();
                    break;
                /**
                 * @author abdullah
                 * 13-5-2023
                 */


                case 17:
                    ArrayList<Slot> sbydate = test.findSlotsByDate(test.getDateFromUser());
                    if (sbydate.size()==0){
                        System.out.println("No slots reserved on this date.");
                    }
                    else {
                        System.out.println("ID\t           Time\t\t\t   Date\t           Availability\t " +
                                "         Service\t                      Patient\n---------------" +
                                "------------------------------------------------------"
                                + "-------------------------------------------------------");
                        for (Slot sbydate1 : sbydate) {
                            System.out.println(sbydate1);
                        }
                    }
                    test.scan.nextLine();
                    break;

                /**
                 * @author Abdulrahman Q. Alshawabkeh 14-5-2023
                 */
                case 18:
                    boolean b;
                    b = test.IsSlotAvailable(test.getServiceTitleFromUser(), test.getDateFromUser(),
                            test.getTimeFromUser());
                    if (b)
                        System.out.println("Slot is available.");
                    else
                        System.out.println("Slot is unavailable.");
                    test.scan.nextLine();
                    break;
/**
 * @author Mohamed Elansari
 * this case handles get service slots
 */

                case 19:
                    date = test.getDateFromUser();
                    test.scan.nextLine();
                    title = test.getServiceTitleFromUser();
                    ArrayList<Slot> sSlots = new ArrayList<>(test.getServiceSlots(date, title));
                    if (sSlots.size()==0){
                        System.out.println("No slots reserved for this service on this date.");
                    }
                    else {
                        System.out.println("ID\t           Time\t\t\t   Date\t           Availability\t " +
                                "         Service\t                      Patient\n---------------" +
                                "------------------------------------------------------"
                                + "-------------------------------------------------------");
                        for (Slot s : sSlots)
                            System.out.println(s);
                    }
                    break;

                /**
                 * @author Abdulrahman Q. Alshawabkeh 14-5-2023
                 */
                case 20:
                    title = test.getServiceTitleFromUser();
                    ArrayList<Slot> sslots = new ArrayList<>(test.getSlotsPerService(title));
                    if (sslots.size() == 0) {
                        System.out.println("No slots reserved for this service");
                    } else {
                        System.out.println("ID\t Time\t\t\t Date\t Availability\t "
                                + " Service\t Patient\n---------------"
                                + "------------------------------------------------------"
                                + "-------------------------------------------------------");
                        for (Slot s : sslots) {
                            System.out.println(s);
                        }
                    }
                    break;




                /**
                 * @author Mohamed Elansari
                 * This case exits the file and writes the three arraylists to a file
                 */

                case 0:
                    System.out.println("Exiting...");
                    kb.close();
                    out = new ObjectOutputStream(new FileOutputStream("patients.dat"));
                    out.writeObject(test.getPatients());
                    out = new ObjectOutputStream(new FileOutputStream("services.dat"));
                    out.writeObject(test.getServices());
                    out = new ObjectOutputStream(new FileOutputStream("slots.dat"));
                    out.writeObject(test.getSlots());
                    out.close();
                    test.getPatientInvoice();
                    System.exit(0);
            }
        }
    }

    public static void print() {
        System.out.print("\n");
        System.out.println("Welcome to the Sehaa System!");
        System.out.println("Please select an option:");
        System.out.println("1. Add a Patient");
        System.out.println("2. Delete a Patient");
        System.out.println("3. Find a Patient");
        System.out.println("4. Modify a Patient");
        System.out.println("5. Add a Service");
        System.out.println("6. Modify a Service");
        System.out.println("7. Delete a Service");
        System.out.println("8. Find a Service");
        System.out.println("9. Add an Empty Slot");
        System.out.println("10. Get Available Slots for a Service by Date");
        System.out.println("11. Show All Services");
        System.out.println("12. Show All Patients");
        System.out.println("13. Show All Slots");
        System.out.println("14. Get Available Slots");
        System.out.println("15. Delete a Slot");
        System.out.println("16. Reserve a Slot");
        System.out.println("17. Find Slots by Date");
        System.out.println("18. Check slot availability");
        System.out.println("19. Get Slots for a Service and its status");
        System.out.println("20. Get Slots per Service");
        System.out.println("0. Exit\n");
        System.out.print("Enter a number: ");
        System.out.print("");
    }
}




