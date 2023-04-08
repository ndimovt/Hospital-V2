package io.github.ndimovt.hospitalv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class AddInformationToDB {
   private static AddInformationToDB addInformationToDB;
    private AddInformationToDB() {
    }
    public static AddInformationToDB getInstance(){
        if(addInformationToDB == null){
            addInformationToDB = new AddInformationToDB();
        }
        return addInformationToDB;
    }
    private static Connection getConnection() throws SQLException {
        Connection DBConnection = null;
        DBConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "sheeuser123456@");
        return DBConnection;
    }
    private String DateTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        String dateAndTime = currentDateAndTime.format(format);
        return dateAndTime;
    }
    protected void addDoctorsToDB(String forname, String fatherName, String surname, long egn, String address, long phoneNumber) throws SQLException {
        Connection c = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        try {
            c = this.getConnection();
            pst = c.prepareStatement("INSERT INTO doctors (forename,fathername,surname,EGN) VALUES(?,?,?,?)");
            pst1 = c.prepareStatement("INSERT INTO doctors_personal_data (EGN,address,phone_number,date_in) VALUES(?,?,?,?)");
            pst.setString(1, forname);
            pst.setString(2, fatherName);
            pst.setString(3, surname);
            pst.setLong(4, egn);
            pst1.setLong(1, egn);
            pst1.setString(2, address);
            pst1.setLong(3, phoneNumber);
            pst1.setString(4, addInformationToDB.DateTime());
            pst.executeUpdate();
            pst1.executeUpdate();
            System.out.println("Doctor added to database");
        } finally {
            if (c != null) {
                c.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (pst1 != null) {
                pst1.close();
            }
        }
    }
    protected void addNursesToDB(String forname, String fatherName, String surname, long egn, String address, long phoneNumber) throws SQLException{
        Connection c = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        try{
            c = this.getConnection();
            pst = c.prepareStatement("INSERT INTO nurses (forename,fathername,surname,EGN) VALUES(?,?,?,?)");
            pst1 = c.prepareStatement("INSERT INTO nurses_personal_data (EGN,address,phone_number,date_in) VALUES(?,?,?,?)");
            pst.setString(1, forname);
            pst.setString(2, fatherName);
            pst.setString(3, surname);
            pst.setLong(4, egn);
            pst1.setLong(1, egn);
            pst1.setString(2, address);
            pst1.setLong(3, phoneNumber);
            pst1.setString(4, addInformationToDB.DateTime());
            pst.executeUpdate();
            pst1.executeUpdate();
            System.out.println("Nurse added to database");
        }finally {
            if(c != null){
                c.close();
            }if(pst != null){
                pst.close();
            }if(pst1 != null){
                pst1.close();
            }
        }
    }
    protected void addPatientToDB(String forname, String fatherName, String surname, long egn, String address) throws SQLException{
        Connection c = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        try{
            c = this.getConnection();
            pst = c.prepareStatement("INSERT INTO patients(forename, fathername, surname, EGN) VALUES(?,?,?,?)");
            pst1 = c.prepareStatement("INSERT INTO patients_personal_data (EGN, adress, date_in) VALUES(?,?,?)");
            pst.setString(1,forname);
            pst.setString(2,fatherName);
            pst.setString(3,surname);
            pst.setLong(4, egn);
            pst1.setLong(1,egn);
            pst1.setString(2,address);
            pst1.setString(3, addInformationToDB.DateTime());
            pst.executeUpdate();
            pst1.executeUpdate();
            System.out.println("Patient successfully added to DataBase");
        }finally {
            if(c != null){
                c.close();
            }if(pst != null){
                pst.close();
            }if(pst1 != null){
                pst1.close();
            }
        }
    }
    protected void updatePatient(long egn,String illness, String treatment) throws SQLException {
        Connection c = null;
        PreparedStatement pst = null;
        try{
            c = this.getConnection();
            pst = c.prepareStatement("UPDATE patients_personal_data SET illness = ? ,treatment = ? WHERE EGN ='"+egn+"'");
            pst.setString(1,illness);
            pst.setString(2,treatment);
            pst.executeUpdate();
            System.out.println("Patient's info successfully updated");
        } finally{
            if(c != null){
                c.close();
            }if(pst != null){
                pst.close();
            }
        }
    }
}
