package io.github.ndimovt.hospitalv2;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class DataBaseOperations {
    private static DataBaseOperations dbo;

    private DataBaseOperations() {
    }

    public static DataBaseOperations getInstance() {
        if (dbo == null) {
            dbo = new DataBaseOperations();
        }
        return dbo;
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
            pst1.setString(4, dbo.DateTime());
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
            pst1.setString(4, dbo.DateTime());
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
            pst1.setString(3, dbo.DateTime());
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
    protected void releasePatient(long egn) throws SQLException{
        Connection c = null;
        PreparedStatement pst = null;
        try{
            c  = this.getConnection();
            pst = c.prepareStatement("UPDATE patients_personal_data SET date_out = ? WHERE EGN ='"+egn+"'");
            pst.setString(1, dbo.DateTime());
            pst.executeUpdate();
            System.out.println("Patient successfully released");
        }finally {
            if(c != null){
                c.close();
            }if(pst != null){
                pst.close();
            }
        }
    }
    protected void releaseDoctor(long egn) throws SQLException{
        Connection doctorConnection = null;
        PreparedStatement doctorPst = null;
        try{
            doctorConnection  = this.getConnection();
            doctorPst = doctorConnection.prepareStatement("UPDATE doctors_personal_data SET date_out = ? WHERE EGN ='"+egn+"'");
            doctorPst.setString(1, dbo.DateTime());
            doctorPst.executeUpdate();
            System.out.println("Operation is successful");
        }finally {
            if(doctorConnection != null){
                doctorConnection.close();
            }if(doctorPst != null){
                doctorPst.close();
            }
        }
    }
    protected void releaseNurse(long egn) throws SQLException {
        Connection nurseConnection = null;
        PreparedStatement nursePst = null;
        try{
            nurseConnection  = this.getConnection();
            nursePst = nurseConnection.prepareStatement("UPDATE nurses_personal_data SET date_out = ? WHERE EGN ='"+egn+"'");
            nursePst.setString(1, dbo.DateTime());
            nursePst.executeUpdate();
            System.out.println("Operation is successful");
        }finally {
            if(nurseConnection != null){
                nurseConnection.close();
            }if(nursePst != null){
                nursePst.close();
            }
        }
    }

    protected void checkPatientsInfo(){
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            c = this.getConnection();
            ps = c.prepareStatement("""
                    SELECT p.forename, p.fathername, p.surname, p.EGN, ppd.adress, ppd.illness, ppd.treatment, ppd.date_in
                    FROM patients p
                    JOIN patients_personal_data ppd ON p.EGN = ppd.EGN
                    """);
            rs = ps.executeQuery();
            while(rs.next()){
                Patient p = new Patient(rs.getString("forename"),
                        rs.getString("fathername"),
                        rs.getString("surname"),
                        rs.getLong("EGN"),
                        rs.getString("adress"),
                        rs.getString("illness"),
                        rs.getString("treatment"),
                        rs.getString("date_in") );
                System.out.println(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
