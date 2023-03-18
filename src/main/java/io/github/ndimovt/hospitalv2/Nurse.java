package io.github.ndimovt.hospitalv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Nurse {
    private static Connection con = null;
    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "sheeuser123456@");
        }catch (SQLException E){
            System.out.println("Failed to connect to database. Please try again later");
            E.printStackTrace();
        }
    }
    private String forname;
    private String fathername;
    private String surname;
    private long EGN;
    private String address;
    private long phoneNumber;
    public Nurse() {
    }
    public Nurse(String forname, String fathername, String surname, long EGN, String address, long phoneNumber) {
        this.forname = forname;
        this.fathername = fathername;
        this.surname = surname;
        this.EGN = EGN;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public Nurse addNurseToDB(Nurse createNurse){
        PreparedStatement SQLStatementBasicData = null;
        PreparedStatement SQLStatementAdvancedData = null;
        try {
            String SQLAddNewBasicHuman = "INSERT INTO nurses (forename, fathername, surname, EGN) VALUES(?,?,?,?)";
            String SQLAddNewAdvancedHuman = "INSERT INTO nurses_personal_data (EGN,address,phone_number,date_in) VALUES(?,?,?,?)";
            SQLStatementBasicData = con.prepareStatement(SQLAddNewBasicHuman);
            SQLStatementAdvancedData = con.prepareStatement(SQLAddNewAdvancedHuman);
            SQLStatementBasicData.setString(1, createNurse.getForname());
            SQLStatementBasicData.setString(2, createNurse.getFathername());
            SQLStatementBasicData.setString(3, createNurse.getSurname());
            SQLStatementBasicData.setLong(4, createNurse.getEGN());
            SQLStatementAdvancedData.setLong(1, createNurse.getEGN());
            SQLStatementAdvancedData.setString(2,createNurse.getAddress());
            SQLStatementAdvancedData.setLong(3,createNurse.getPhoneNumber());
            SQLStatementAdvancedData.setString(4,createNurse.DateTime());
            SQLStatementBasicData.executeUpdate();
            SQLStatementAdvancedData.executeUpdate();
            System.out.println("Record successfully added to database!");
            SQLStatementAdvancedData.close();
            //SQLStatementBasicData.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return createNurse;
    }
    public String DateTime(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        String dateAndTime = currentDateAndTime.format(format);
        return dateAndTime;
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
}
