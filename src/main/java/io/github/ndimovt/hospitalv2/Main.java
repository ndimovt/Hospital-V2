package io.github.ndimovt.hospitalv2;

import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner inn = new Scanner(System.in);
        System.out.println("Enter firstname");
        String firstname = inn.nextLine();
        System.out.println("Enter secondname");
        String secondname = inn.nextLine();
        System.out.println("Enter thirdname");
        String thirdname = inn.nextLine();
        System.out.println("Choose between doctors, nurses or patients");
        String ch= inn.nextLine();
        System.out.println("Enter egn");
        long EGN = inn.nextLong();
        try {
            if (ch.equals("doctors")) {
                Human human = new Doctor(firstname, secondname, thirdname, EGN);
                DataBaseOptions dbo = new DataBaseOptions();
                dbo.addHumanToDB(ch,human);
            } else if (ch.equals("nurses")) {
                Human human = new Nurse(firstname, secondname, thirdname, EGN);
                DataBaseOptions dbo = new DataBaseOptions();
                dbo.addHumanToDB(ch,human);
            } else if (ch.equals("patients")) {
                Human human = new Patient(firstname, secondname, thirdname, EGN);
                DataBaseOptions dbo = new DataBaseOptions();
                dbo.addHumanToDB(ch,human);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}