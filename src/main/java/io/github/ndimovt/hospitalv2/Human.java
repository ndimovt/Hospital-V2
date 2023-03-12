package io.github.ndimovt.hospitalv2;

public class Human {
    private String forname;
    private String fathername;
    private String surname;
    private long EGN;
    private String address;
    private int phoneNumber;
    private String illness;
    private String treatment;
    public Human(String forname, String fathername, String surname, long EGN) {
        this.forname = forname;
        this.fathername = fathername;
        this.surname = surname;
        this.EGN = EGN;
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
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public String getIllness() {
        return illness;
    }
    public String getTreatment() {
        return treatment;
    }
}
