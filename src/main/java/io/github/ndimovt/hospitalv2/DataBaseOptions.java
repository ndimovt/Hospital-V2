package io.github.ndimovt.hospitalv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DataBaseOptions {
    private static Connection con = null;
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "sheeuser123456@");
        }catch (SQLException E){
            E.printStackTrace();
        }
    }
    protected Human addHumanToDB(String choice,Human h) throws SQLException {
        PreparedStatement pst = null;
        try {
            String SQLAddNewHuman = "INSERT INTO "+choice+"(forename, fathername, surname, EGN) VALUES(?,?,?,?)";
            pst = con.prepareStatement(SQLAddNewHuman);
            pst.setString(1, h.getForname());
            pst.setString(2, h.getFathername());
            pst.setString(3, h.getSurname());
            pst.setLong(4, h.getEGN());
            pst.executeUpdate();
            System.out.println(choice+"Successfully added to database!");
        }finally {
            if(pst != null){
                pst.close();
            }
            if(con != null){
                con.close();
            }
        }
            return h;
    }
}

