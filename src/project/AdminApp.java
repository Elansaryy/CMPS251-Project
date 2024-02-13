package project;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * this is the code that contains most of the logic
 * @author all
 * @version 5.1
 *
 */
public class AdminApp implements Serializable {
    private ArrayList<Patient> patients;
    private ArrayList<Service> services;
    private ArrayList<Slot> slots;

    Scanner scan = new Scanner(System.in);
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
    int maxSlots;
    String title,name;
    boolean valid;
    int price;
    public AdminApp(){
        patients= new ArrayList<Patient>();
        services= new ArrayList<Service>();
        slots= new ArrayList<Slot>();
    }

    public AdminApp(ArrayList<Patient> patients, ArrayList<Slot> slots, ArrayList<Service> services) {
        this();
        this.patients = patients;
        this.slots = slots;
        this.services = services;
    }
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
    public ArrayList<Slot> getSlots() {
        return slots;
    }
    public void setSlots(ArrayList<Slot> slots) {
        this.slots = slots;
    }
    public ArrayList<Service> getServices() {
        return services;
    }
    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }
    /**
     * This method add the patient to the ArrayList
     *
     * @param P this method takes P of type Patient
     * @return String
     * @author Abdulrahman Q. Alshawabkeh 7-5-2023
     */
    public String addPatient(Patient P) {
        for (Patient p11 : patients) {
            if (P.getPid() == p11.getPid()) {
                return "Patient with same Id already exists";
            }
        }
        patients.add(P);
        return ("Patient added successfully");
    }
    /**
     * This method delete the patient from the patients ArrayList
     *
     * @param qid this method takes qid of type int
     * @return String
     * @author Abdulrahman Q. Alshawabkeh created in 7-5-2023 / edited in 14-5-2023
     *
     */
    public String deletePatient(int qid) {
        Patient p1 = null;
        for (Patient p : patients) {
            if (p.getPid() == qid) {
                p1 = p;
                break;
            }
        }
        if (p1 == null) {
            return ("Patient Not Found");
        } else if (p1 != null) {
            patients.remove(p1);
            ;
        }
        return ("Patient removed successfully");
    }
    /**
     * This method find a patient from the patients ArrayList
     *
     * @param qid this method takes qid of type int
     * @return returns an object of type Patient
     * @author Abdulrahman Q. Alshawabkeh 6-5-2023
     */
    public Patient findPatient(int qid) {
        for (Patient p : patients) {
            if (p.getPid() == qid) {
                return p;
            }
        }
        return null;
    }
    /**
     * This method modify an existing patient
     *
     * @param QID this method takes QID of type int
     * @return String
     * @author Abdulrahman Q. Alshawabkeh created in 7-5-2023 edited in 17-5-2023
     */
    public String modifyPatient(int QID) {
        for (Patient p : patients) {
            if (p.getPid() == QID) {
                System.out.println("What information would you like to modify?");
                System.out.println("1. Modify name");
                System.out.println("2. Modify residency type");
                System.out.print("Enter here:");
                while (true) {
                    try {
                        choice = scan.nextInt();
                        scan.nextLine();
                        while (choice > 2 || choice < 1) {
                            System.out.print("Please enter a valid value 1 or 2: ");
                            choice = scan.nextInt();
                            scan.nextLine();
                        }
                    } catch (InputMismatchException errr) {
                        System.err.println("please enter an integer 1 or 2: ");
                        scan.nextLine();
                        continue;
                    }
                    switch (choice) {
                        case 1:
                            name = getNameFromUser();
                            p.setName(name);
                            return "Patient updated successfully";
                        case 2:
                            ResidencyType residencyType = this.getResidencyTypeFromUser();
                            p.setResidencyType(residencyType);
                            return "Patient updated successfully";
                    }
                }
            }
        }
        scan.nextLine();
        return "Patient Not Found.";
    }




    /**
     *This method adds a service to the arrarylist
     * @param s is object service that will be added to the arraylist
     * @return it returns a string that confirms whether the action happened or not
     * @author Mohamed Elansari
     * created 7-5-2023
     */
    public String addService(Service s) {
        for (Service service: services) {
            if (service.getID()==s.getID()) {
                return "Service with same Id already exists";
            }
        }
        services.add(s);
        return ("Service added successfully");
    }

    /**
     * This method modifies 3 attributes of the service
     * @param ID it recieves the id for a service
     * @return it returns a string
     * @author Mohamed Elansari
     * created 7-5-2023
     */

    public String modifyService(int ID) {
        for (Service s : services) {
            if (s.getID() == ID) {
                System.out.println("What information would you like to modify?");
                System.out.println("1. Modify Service Title.");
                System.out.println("2. Modify Max Slots");
                System.out.println("3. Modify Price per slot");
                System.out.print("Enter here:");
                while (true) {
                    try {
                        choice = scan.nextInt();
                        while (choice > 3 || choice < 1) {
                            System.out.print("Please enter a valid value 1 or 3: ");
                            choice = scan.nextInt();
                        }
                    } catch (InputMismatchException errr) {
                        System.err.println("please enter an integer 1 or 3: ");
                        scan.nextLine();
                        continue;
                    }
                    switch (choice) {
                        case 1:
                            scan.nextLine();
                            title = getServiceTitleFromUser();
                            s.setTitle(title);
                            return "Service updated successfully";
                        case 2:
                            int maxSlots = getMaxSlotsFromUser();
                            s.setMaxSlots(maxSlots);
                            scan.nextLine();
                            return "Service updated successfully";
                        case 3:
                            int price = getPriceFromUser();
                            s.setPricePerSlot(price);
                            scan.nextLine();
                            return "Service updated successfully";
                    }
                }
            }
        }
        scan.nextLine();
        return "Service Not Found.";
    }


    /**
     * this method deletes a service from the arraylist
     * @param ID it receives the id for a services that will be deleted
     * @return it returns whether the service has been deleted or not found
     * @author Mohamed Elansari
     * created 7-5-2023
     */

    public String deleteService(int ID) {
        Service tempService = new Service();
        ArrayList<Slot>temp=new ArrayList<>();
        for (Service s : services) {
            if (s.getID() == ID) {
                tempService = s;
                for (Slot slot : slots) {
                    if (slot.getAllocatedService() != null){
                        if (slot.getAllocatedService().getID()==s.getID()) {
                            temp.add(slot);
                        }
                    }
                }
            }
        }
        if (tempService.getID()==0&&tempService.getTitle()==null&&tempService.getMaxSlots()==0&&tempService.getPricePerSlot()==0) {
            return "Service not found!";
        }

        services.remove(tempService);
        for(Slot sl1:temp){
            slots.remove(sl1);
        }
        return "Service and all related slots are deleted successfully";
    }
    /**
     *This method finds a service that exists in the arraylist
     * @param servTitle it recieves the service title
     * @author Mohamed Elansari
     * created 8-5-2023
     */
    public void findService(String servTitle) {
        int count=0;
        for (Service s : services) {
            if (s.getTitle().equalsIgnoreCase(servTitle)) {
                count+=1;
                System.out.println("Max Slots for this service is: " + s.getMaxSlots());
                System.out.println("ID for this service is: " + s.getID());
                System.out.println("Price per slot for this service is: " + s.getPricePerSlot());
                int isBooked = 0;
                for (Slot slot : slots) {
                    if(slot.getAllocatedService()!=null) {
                        if (slot.getAllocatedService().getID()==s.getID()) {
                            if (slot.isBooked()) {
                                isBooked++;
                            }
                        }
                    }
                }
                System.out.println("The booked Slots are: " + isBooked);
                break;
            }
        }

        if(count==0){
            System.out.println("This service title doesnt exist");
        }
    }

    /**
     * This method get a slot per some Service Title and put it on the new ArrayList
     *
     * @param ServTitle of type String
     * @return returns an array list that contains objects of type slot that have
     * the same Service Title
     * @author Abdulrahman Q. Alshawabkeh 7-5-2023
     */
    public ArrayList<Slot> getSlotsPerService(String ServTitle) {
        ArrayList<Slot> result = new ArrayList<Slot>();
        for (Slot s : slots) {
            if (s.getAllocatedService() != null) {
                if (s.getAllocatedService().getTitle().equalsIgnoreCase(ServTitle)) {
                    result.add(s);
                }
            }
        }
        return result;
    }
    /**
     * This method Check if a certain slot is available or not
     *
     * @param ServTitle of type String time
     * @param date an object of type LocalDate
     * @param time an object of type LocalDate
     * @return returns Boolean
     * @author Abdulrahman Q. Alshawabkeh 7-5-2023
     */
    public Boolean IsSlotAvailable(String ServTitle, LocalDate date, LocalTime time) {
        for (Slot s : slots) {
            if (s != null) {
                for (Service serv : services) {
                    if (serv != null) {
                        if (serv.getTitle().equalsIgnoreCase(ServTitle) && s.getDate() == date && s.getTime() == time) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *  adds an empty slot
     * @return string telling the user that an empty slot was created
     * @author abdullah
     * 5-12(2:06)
     */

    public String addEmptySlot() {
        Slot s=new Slot();
        slots.add(s);
        return "Slot added successfully";
    }
    /**
     * deletes a slot with a given service id time and date
     * @param serviceId the service id of the slot you wan to delete
     * @param date an object of localdate
     * @param time an object of loacltime
     * @return returns a string telling the user if the slot was deleted
     * @author abdullah
     */

    public String deleteSlot(int serviceId,LocalDate date,LocalTime time) {
        Slot s1 = null;
        for (Slot s:slots) {
            if(s.getAllocatedService()!=null) {
                if (s.getAllocatedService().getID()==serviceId&&s.getDate().equals(date)&&s.getTime().equals(time)) {
                    s1=s;
                    break;
                }
            }
        }
        if (s1==null) {
            return "Slot not found";
        }
        else {
            slots.remove(s1);
            return "Slot deleted successfully";
        }
    }
    /**
     *  simple method that returns whether a patinet is free or not
     * @param date an object of type LocalDate
     * @param time an object of type LocalTime
     * @param pId id of the patient
     * @return returns true if the patient is free otherwise false
     * @author abdullah
     */

    public boolean isFree(LocalDate date, LocalTime time,int pId) {
        for (Slot s: slots) {
            if(s.getAllocatedPatient()!=null&&s.getAllocatedService()!=null) {
                if (s.getAllocatedPatient().getPid()==pId&&s.getDate().equals(date)) {
                    if(time.equals(s.getTime())) {
                        return false;
                    }
                    else if(time.isAfter(s.getTime())&&time.isBefore((s.getTime().plusMinutes(30)))){
                        return false;
                    }

                }

            }
        }
        return true;
    }
    /**
     *  simple method that returns whether a service is free or not
     * @param date an object of type LocalDate
     * @param time an object of type LocalTime
     * @param serviceId id of the service
     * @return returns true if the service is free otherwise false
     * @author abdullah
     */

    public boolean isServiceFree(LocalDate date, LocalTime time,int serviceId) {
        for (Slot s: slots) {
            if(s.getAllocatedPatient()!=null&&s.getAllocatedService()!=null) {
                if (s.getAllocatedService().getID()==serviceId&&s.getDate().equals(date)) {
                    if(time.equals(s.getTime())) {
                        return false;
                    }
                    else if(time.isAfter(s.getTime())&&time.isBefore((s.getTime().plusMinutes(30)))){
                        return false;
                    }

                }

            }
        }
        return true;
    }



    /**
     * a simple method that returns the service this method is used by reserveSlot only
     * @param serviceId the service if that the object of type service has
     * @return returns an object of type service
     * @author abdullah
     */
    public Service getService(int serviceId) {
        for (Service s:services) {
            if (s.getID()==serviceId){
                return s;
            }
        }
        return null;
    }
    /**
     * this method is the most complicated complicated method it is the only method that has access to availability and other attributes it reserves a specific slot after getting info about the slot from the user
     * @param slotId the slot id that you want to reserve
     * @param serviceId the service id that you want to reserve
     * @param pId the patient id that you want to reserve
     * @param date the date of the reservation
     * @param time the time of the reservation
     * @return a string telling the user whether it has been reserved or maybe other possibilities like patient not found
     * @author abdullah
     */

    public String reserveSlot(int slotId,int serviceId,int pId,LocalDate date,LocalTime time) {
        for (Slot s:slots) {
            if (s.getId()==slotId) {
                if(s.getAllocatedPatient()==null&&s.getAllocatedService()==null&&s.getDate()==null&&s.getTime()==null) {

                    Service s1=getService(serviceId);
                    if(s1==null) {
                        return "service not found!";
                    }
                    int counter=0;
                    for(Slot sl:slots) {
                        if (sl.getAllocatedService() != null) {
                            if (sl.getAllocatedService().getID() == s1.getID()&&sl.getDate().equals(date)) {
                                ++counter;
                            }

                        }
                    }
                    if(counter<s1.getMaxSlots()) {
                        Patient p1=findPatient(pId);
                        if(p1==null) {
                            return "Patient not found!";
                        }
                        s.setAllocatedPatient(p1);
                        s.setAllocatedService(s1);
                        s.setBooked(true);
                        s.setDate(date);
                        s.setTime(time);
                        return "Slot for patient " +p1.getName()+" reservered successfully";
                    }
                    else return "Max number of slots reached for this service";
                }
                else return "This slot is not empty";
            }
        }
        return "Slot with given Id not found";
    }


    /**
     * this method puts the slots that have the same date in an array list
     * @param date takes an object of type date
     * @return returns an array list that contains objects of type slot that have the same date
     * @author abdallah
     * 13-5-2023
     */
    public ArrayList<Slot> findSlotsByDate(LocalDate date) {
        ArrayList<Slot> result = new ArrayList<Slot>();
        for (Slot s : slots) {
            if (s.getDate()==null)
                continue;
            if (s.getDate().equals(date))
                result.add(s);
        }

        return result;
    }
    /**
     * This method shows all the current services
     *
     * @void
     * @author Abdulrahman Shabban
     * 13-5-2023
     */
    public void showAllServices() {
        System.out.println("Title\t       ID\t          Maximum slots\t" +
                "   Price per slot\n-------------------------------" +
                "------------------------------");
        for (Service s : services) {
            System.out.println(s);
        }
    }

    /**
     * This method shows all the current patients
     *
     * @void
     * @author Abdulrahman Shabban
     * 13-5-2023
     */
    public void showAllPatients() {
        System.out.println("PID\t           Name\t\t          Residency Type\n-----------------------------------" +
                "-------------");
        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    /**
     * This method shows all the current slots
     *
     * @void
     * @author Abdulrahman Shabban
     * 13-5-2023
     */
    public void showAllSlots() {
        System.out.println("ID\t           Time\t\t\t   Date\t           Availability\t " +
                "         Service\t                      Patient\n---------------" +
                "------------------------------------------------------"
                + "-------------------------------------------------------");
        for (Slot s : slots) {
            System.out.println(s);
        }
    }


    /**
     * This method shows all the available slots for certain service on a specified date
     * @param serviceID ID of the specified service of type int
     * @param date Object of type LocalDate
     * @return ArrayList of type Slot
     * @author Abdulrahman Shabban
     * 13-5-2023
     */
    public ArrayList<Slot> getAvailableSlotsPerServiceByDate(int serviceID, LocalDate date) {
        ArrayList<Slot> availableSlots = new ArrayList<>();
        for (Slot s : slots) {
            if(s.getAllocatedService()!=null&&s.getDate()!=null) {
                if (s.getAllocatedService().getID() == serviceID && s.getDate() == date && !s.isBooked()) {
                    availableSlots.add(s);
                }
            }
        }
        return availableSlots;
    }


    /**
     * This method shows all the available slots
     *
     * @return ArrayList of type Slot
     * @author Abdulrahman Shabban
     * 13-5-2023
     */
    public ArrayList<Slot> getAvailableSlots() {
        ArrayList<Slot> availableSlots = new ArrayList<>();
        for (Slot s : slots) {
            if (!s.isBooked()) {
                availableSlots.add(s);
            }
        }
        return availableSlots;
    }

    /**
     * this method adds the service to the arraylist
     * @param date,serviceTitle it takes both date and service title
     * @return it returns an arraylist of slots
     * @author Mohamed Elansari
     */
    public ArrayList<Slot> getServiceSlots(LocalDate date, String serviceTitle) {
        ArrayList<Slot> result = new ArrayList<Slot>();
        for (Slot sl : slots) {
            if (sl.getDate()!=null) {
                if (sl.getDate().equals(date)) {
                    if(sl.getAllocatedService().getTitle().equals(serviceTitle)){
                        result.add(sl);
                    }
                }
            }
        }
        return result;
    }

    /**
     * This gets the slot ID with the maximum value
     *
     * @param slots the ArrayList containing all the slots
     * @return an integer representing the greatest slot ID value
     * @author Abdulrahman Shaban
     * 13-5-2023
     */

    public int getMaxID(ArrayList<Slot> slots){
        int max = 0;
        for(Slot s : slots){
            if (s.getId()>max)
                max=s.getId();
        }
        return max;
    }
    /**
     * get date from user and validates
     * @return an object of type date
     * @author abdullah
     */

    public LocalDate getDateFromUser() {
        while (true) {
            try {
                System.out.print("Enter day: ");
                day = scan.nextInt();
                System.out.print("Enter month: ");
                month = scan.nextInt();
                System.out.print("Enter year: ");
                year = scan.nextInt();
                return LocalDate.of(year, month, day);
            } catch (Exception eeer) {
                System.out.println("Invalid! please enter a valid value");
                scan.nextLine();
            }
        }
    }
    /**
     * gets the time from user and validates
     * @return an object of type time
     * @author abdullah
     */

    public LocalTime getTimeFromUser() {
        while (true) {
            try {
                System.out.print("Enter hour (0-24): ");
                hour = scan.nextInt();
                System.out.print("Enter minutes: ");
                minutes = scan.nextInt();
                while (LocalTime.of(hour, minutes).isBefore(LocalTime.of(7,0))||(LocalTime.of(hour, minutes).isAfter(LocalTime.of(20, 30)))) {
                    System.out.println("Sorry this time is not allowed");
                    System.out.print("Enter hour (0-24): ");
                    hour = scan.nextInt();
                    System.out.print("Enter minutes: ");
                    minutes = scan.nextInt();
                }

                return LocalTime.of(hour, minutes);
            }
            catch (Exception eeer) {
                System.out.println("Invalid! please enter a valid value");
                scan.nextLine();
            }
        }
    }
    /**
     * gets the patient id from from user and validates it
     * @author abdullah
     * @return an integer representing the patients id
     *
     */

    public int getPatientIdFromUser() {
        while (true) {
            try {
                System.out.print("Enter Qid: ");
                pId = scan.nextInt();
                if (pId < 0) {
                    System.out.println("Invalid! please enter a valid value");
                    continue;
                }
                return pId;
            } catch (InputMismatchException eeer) {
                System.out.println("Invalid! please enter a valid value");
                scan.nextLine();
            }
        }
    }
    /**
     * gets the slot id from from user and validates it
     * @author abdullah
     * @return an integer representing the slots id
     *
     */

    public int getSlotIdFromUser() {
        while (true) {
            try {
                System.out.print("Enter Slot Id: ");
                slotId = scan.nextInt();
                if (slotId < 0) {
                    System.out.println("Invalid! please enter a valid value");
                    continue;
                }
                return slotId;
            } catch (InputMismatchException eeer) {
                System.out.println("Invalid! please enter a valid value");
                scan.nextLine();
            }
        }
    }
    /**
     * gets the maximum amount of slots allowed for a service from from user and validates it
     * @author abdullah
     * @return an integer representing the maximum amount of slots
     *
     */

    public int getMaxSlotsFromUser() {
        while (true) {
            try {
                System.out.print("Enter amount of Slots: ");
                maxSlots = scan.nextInt();
                if (maxSlots < 0) {
                    System.out.println("Invalid! please enter a valid value");
                    continue;
                }
                return maxSlots;
            } catch (InputMismatchException eeer) {
                System.out.println("Invalid! please enter a valid value");
                scan.nextLine();
            }
        }
    }
    /**
     * gets the price of a service  from user and validates it
     * @author abdullah
     * @return an integer representing the price of a service
     *
     */

    public int getPriceFromUser() {
        while (true) {
            try {
                System.out.print("Enter price: ");
                price = scan.nextInt();
                if (price < 0) {
                    System.out.println("Invalid! please enter a valid value");
                    continue;
                }
                return price;
            } catch (InputMismatchException eeer) {
                System.out.println("Invalid! please enter a valid value");
                scan.nextLine();
            }
        }
    }
    /**
     * gets the service id from from user and validates it
     * @author abdullah
     * @return an integer representing the service id
     *
     */

    public int getServiceIdFromUser() {
        while (true) {
            try {
                System.out.print("Enter Service Id: ");
                serviceId = scan.nextInt();
                if (serviceId < 0) {
                    System.out.println("Invalid! please enter a valid value");
                    continue;
                }
                return serviceId;
            } catch (InputMismatchException eeer) {
                System.out.println("Invalid! please enter a valid value");
                scan.nextLine();
            }
        }
    }
    /**
     * gets the patient name from from user and validates it
     * @author abdullah
     * @return a string representing the patients name
     *
     */

    public String getNameFromUser() {
        while (true) {
            try {
                System.out.print("Enter name: ");
                name = scan.nextLine();
                for (char c : name.toCharArray()) {
                    if (Character.isDigit(c)) {
                        Object x = null;
                        x.toString();
                    }
                }
                return name;
            } catch (Exception eeer) {
                System.out.println("Invalid! please enter a valid value");
            }
        }
    }
    /**
     * gets the residency type from from user and validates it
     * @author abdullah
     * @return an object of type ResidencyType
     *
     */

    public ResidencyType getResidencyTypeFromUser() {
        System.out.print("Resident or Visitor (r for resident v for visitor): ");
        ch= scan.nextLine().charAt(0);
        ResidencyType type;
        while (!(Character.toUpperCase(ch) == 'R' || Character.toUpperCase(ch) == 'V')) {
            System.out.print("Invalid input please choose r for resident v for visitor: ");
            ch = scan.nextLine().charAt(0);
        }
        if (Character.toUpperCase(ch) == 'R') {
            type = ResidencyType.RESIDENT;
        } else {
            type = ResidencyType.VISITOR;
        }
        return type;
    }
    /**
     * gets the service title from from user and validates it
     * @author abdullah
     * @return a string representing the service title
     *
     */


    public String getServiceTitleFromUser() {
        while (true) {
            try {
                System.out.print("Enter service title: ");
                title = scan.nextLine();
                switch (title.toUpperCase()) {
                    case "PROCEDURE":
                        title = "PROCEDURE";
                        return title;
                    case "GENERIC":
                        title = "GENERIC";
                        return title;
                    case "SPECIALIZED":
                        title = "SPECIALIZED";
                        return title;
                    case "OPERATION":
                        title = "OPERATION";
                        return title;
                    default:
                        System.out.print("Wrong title entered!\n");
                        continue;

                }
            }
            catch (Exception eeer) {
                System.out.println("Invalid! please enter a valid value");
            }
        }
    }


    /**
     * This method returns the total price for each patient when the program exits
     * @author Mohamed Elansari
     * created 19-5-2023
     */

    public void getPatientInvoice() {
        for (Patient p : patients) {
            int price=0;
            for (Slot sl : slots) {
                if (sl.getAllocatedPatient() != null) {
                    if (sl.getAllocatedPatient().getPid()==p.getPid()) {
                        price+=sl.getAllocatedService().getPricePerSlot();
                    }
                }
            }
            System.out.printf("%s%s%s%d\n","The total price for ",p.getName()," is " ,price);
        }
    }

}




