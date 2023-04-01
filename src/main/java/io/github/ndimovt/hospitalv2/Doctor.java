package io.github.ndimovt.hospitalv2;

public class Doctor {
    private String forname;
    private String fathername;
    private String surname;
    private long EGN;
    private String address;
    private long phoneNumber;
    private String username = "doctor1";
    private String password = "password2";
    protected Doctor() {
    }
    protected Doctor(String forname, String fathername, String surname, long EGN, String address, long phoneNumber) {
        this.forname = forname;
        this.fathername = fathername;
        this.surname = surname;
        this.EGN = EGN;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
