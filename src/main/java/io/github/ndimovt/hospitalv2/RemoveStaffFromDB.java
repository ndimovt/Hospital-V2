package io.github.ndimovt.hospitalv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class RemoveStaffFromDB {
    private static RemoveStaffFromDB removeStaffFromDB;
    private RemoveStaffFromDB() {
    }
    public static RemoveStaffFromDB getInstance(){
        if(removeStaffFromDB == null){
            removeStaffFromDB = new RemoveStaffFromDB();
        }
        return removeStaffFromDB;
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
    protected void releasePatient(long egn) throws SQLException{
        Connection c = null;
        PreparedStatement pst = null;
        try{
            c  = this.getConnection();
            pst = c.prepareStatement("UPDATE patients_personal_data SET date_out = ? WHERE EGN ='"+egn+"'");
            pst.setString(1, removeStaffFromDB.DateTime());
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
            doctorPst.setString(1, removeStaffFromDB.DateTime());
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
            nursePst.setString(1, removeStaffFromDB.DateTime());
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


}
