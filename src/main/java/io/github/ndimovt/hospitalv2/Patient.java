package io.github.ndimovt.hospitalv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Patient {
    private static Connection con = null;
    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "sheeuser123456@");
        }catch (SQLException E){
            System.out.println("Failed to connect to database. Please try again later");
            E.printStackTrace();
        }
    }
    private String illness;
    private String treatment;
    private String forname;
    private String fathername;
    private String surname;
    private long EGN;
    private String address;
    private long phoneNumber;
}
