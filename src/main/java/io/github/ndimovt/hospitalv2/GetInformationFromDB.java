package io.github.ndimovt.hospitalv2;

import java.sql.*;

public class GetInformationFromDB {
    private static GetInformationFromDB getInformationFromDB;
    private GetInformationFromDB() {
    }
    public static GetInformationFromDB getInstance(){
        if(getInformationFromDB == null){
            getInformationFromDB = new GetInformationFromDB();
        }
        return getInformationFromDB;
    }
    private static Connection getConnection() throws SQLException {
        Connection DBConnection = null;
        DBConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "sheeuser123456@");
        return DBConnection;
    }
    protected void checkPatientsInfo()throws SQLException{
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
                Patient p = new Patient(
                        rs.getString("forename"),
                        rs.getString("fathername"),
                        rs.getString("surname"),
                        rs.getLong("EGN"),
                        rs.getString("adress"),
                        rs.getString("illness"),
                        rs.getString("treatment"),
                        rs.getString("date_in"));
                System.out.println(p.toString());
            }
        }finally {
            if(c != null){
                c.close();
            }if(rs != null){
                rs.close();
            }if(ps != null){
                ps.close();
            }
        }
    }
    protected void checkDoctorsInfo(String doctorInfoEGN) throws SQLException{
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        try{
            c = this.getConnection();
            pst = c.prepareStatement("""
                    SELECT d.forename, d.fathername, d.surname, dpd.address, dpd.phone_number, dpd.date_in
                    FROM doctors d
                    JOIN doctors_personal_data dpd ON d.EGN = dpd.EGN
                    WHERE d.EGN = """+doctorInfoEGN+"""
                    """);
            rs = pst.executeQuery();
            while (rs.next()){
                Doctor d = new Doctor(rs.getString("forename"),
                        rs.getString("fathername"),
                        rs.getString("surname"),
                        rs.getString("address"),
                        rs.getLong("phone_number"),
                        rs.getString("date_in"));
                //System.out.println(d);
                d.toString();
            }
        }finally {
            if(c != null){
                c.close();
            }if(rs != null){
                rs.close();
            }if(pst != null){
                pst.close();
            }
        }
    }
    protected void checkNursesInfo(){

    }
}
