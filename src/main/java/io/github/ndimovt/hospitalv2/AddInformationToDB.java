package io.github.ndimovt.hospitalv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            pst1.setString(4,DateAndTime.dateTime);
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
            pst1.setString(4, DateAndTime.dateTime);
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
            pst1.setString(3, DateAndTime.dateTime);
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
    protected void updatePatient(String illness, String treatment, long patienEGN) throws SQLException {
        Connection c = null;
        PreparedStatement pst = null;
        try{
            c = this.getConnection();
            pst = c.prepareStatement("UPDATE patients_personal_data SET illness = ? ,treatment = ? WHERE EGN = ?");
            pst.setString(1,illness);
            pst.setString(2,treatment);
            pst.setLong(3,patienEGN);
            pst.executeUpdate();
            System.out.println("Patient's info successfully updated!");
        } finally{
            if(c != null){
                c.close();
            }if(pst != null){
                pst.close();
            }
        }
    }
}
