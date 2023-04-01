package io.github.ndimovt.hospitalv2;
public class Patient{
    private String forname;
    private String fathername;
    private String surname;
    private long EGN;
    private String address;
    private String illness;
    private String treatment;
    private String dateIn;
    protected Patient(String forname, String fathername, String surname, long EGN, String address, String illness, String treatment,String dateIn) {
        this.illness = illness;
        this.treatment = treatment;
        this.forname = forname;
        this.fathername = fathername;
        this.surname = surname;
        this.EGN = EGN;
        this.address = address;
        this.dateIn = dateIn;
    }
    protected Patient() {
    }
    @Override
    public String toString() {
        return ""+forname+fathername+surname+EGN+address+illness+treatment+dateIn+"";
    }
    public String getIllness() {
        return illness;
    }
    public String getTreatment() {
        return treatment;
    }
    public String getForname() {
        return forname;
    }
    public String getFathername() {
        return fathername;
    }
    public String getSurname() {
        return surname;
    }
    public long getEGN() {
        return EGN;
    }
    public String getAddress() {
        return address;
    }
}
