package project;

import java.io.Serializable;
/**
 * this is the code that contains most of the logic
 *
 * @author all
 * @version 1.0
 * 7-5-2023
 *
 */

public class Patient implements Serializable {
    private int pid;
    private String name;
    private ResidencyType residencyType;
    public Patient(){
    }
    public Patient(int pid, String name, ResidencyType residencyType) {
        this();
        this.pid = pid;
        this.name = name;
        this.residencyType = residencyType;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResidencyType getResidencyType() {
        return residencyType;
    }

    public void setResidencyType(ResidencyType residencyType) {
        this.residencyType = residencyType;
    }

    @Override
    public String toString() {
        return String.format("%-15d%-19s%s",getPid(),getName(),getResidencyType());
    }

}



