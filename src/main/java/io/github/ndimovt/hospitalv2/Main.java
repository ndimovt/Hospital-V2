package io.github.ndimovt.hospitalv2;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        DataBaseOperations dbo = DataBaseOperations.getInstance();
        Scanner inn = new Scanner(System.in);
        Doctor doctor = new Doctor();
        Nurse nurse = new Nurse();
        DepartmentHead dh = new DepartmentHead();
        System.out.println("Welcome to City hospital");
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("Choose option from the menu: 1) Enter in the system 2)Exit the system");
            int choice = inn.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter username");
                    inn.nextLine();
                    String username = inn.nextLine();
                    System.out.println("Enter password");
                    String password = inn.nextLine();
                    if ((username.equals(doctor.getUsername())) && (password.equals(doctor.getPassword()))) {
                        boolean thisMenu = true;
                        System.out.println("You enter the system as doctor");
                        while (thisMenu) {
                            System.out.println("1)Check patients with no treatment and illness 2) Set illness and treatment 3)Release patient 4)Back to main menu");
                            int doctorChoice = inn.nextInt();
                            switch (doctorChoice) {
                                case 1:
                                    dbo.checkPatientsInfo();
                                    break;
                                case 2:
                                    String EGN;
                                    do {
                                        System.out.println("Enter patient's EGN (must be 10 symbols long)");
                                        EGN = inn.nextLine();
                                    } while (EGN.length() != 10);
                                    long egn = Long.parseLong(EGN);
                                    System.out.println("Enter patient's illness");
                                    inn.nextLine();
                                    String illness = inn.nextLine();
                                    System.out.println("Enter patient's treatment");
                                    String treatment = inn.nextLine();
                                    try {
                                        dbo.updatePatient(egn, illness, treatment);
                                    } catch (SQLException e) {
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                    do {
                                        System.out.println("Enter patient's EGN");
                                        EGN = inn.nextLine();
                                    } while (EGN.length() != 10);
                                    egn = Long.parseLong(EGN);
                                    dbo.releasePatient(egn);
                                    break;
                                case 4:
                                    thisMenu = false;
                            }
                        }
                    } else if ((username.equals(nurse.getUsername())) && (password.equals(nurse.getPassword()))) {
                        System.out.println("Enter forname");
                        String forname = inn.nextLine();
                        System.out.println("Enter fathername");
                        String fathername = inn.nextLine();
                        System.out.println("Enter surname");
                        String surname = inn.nextLine();
                        String EGN;
                        do {
                            System.out.println("Enter EGN (must be 10 symbols long)");
                            EGN = inn.nextLine();
                        } while (EGN.length() != 10);
                        System.out.println("Enter address");
                        String address = inn.nextLine();
                        long egn = Long.parseLong(EGN);
                        try {
                            dbo.addPatientToDB(forname, fathername, surname, egn, address);
                        } catch (SQLException e) {
                            System.out.println("Can't connect to Database. Please try again later or call your IT support");
                            e.printStackTrace();
                        }
                    } else if ((username.equals(dh.getUsername())) && (password.equals(dh.getPassword()))) {
                        boolean depHead = true;
                        System.out.println("You enter the system as Head of the Department");
                        while (depHead) {
                            System.out.println("1) Add nurse to the system 2)Add doctor to system 2)Release staff 3)Get staff and patients information 4)Back to main menu");
                            int departmentHead = inn.nextInt();
                            switch (departmentHead) {
                                case 1:
                                    String EGN;
                                    String personalPhoneNumber;
                                    System.out.println("Enter forname");
                                    inn.nextLine();
                                    String forname = inn.nextLine();
                                    System.out.println("Enter fathername");
                                    String fathername = inn.nextLine();
                                    System.out.println("Enter surname");
                                    String surname = inn.nextLine();
                                    do {
                                        System.out.println("Enter EGN (must be 10 symbols long)");
                                        EGN = inn.nextLine();
                                    } while (EGN.length() != 10);
                                    do {
                                        System.out.println("Enter phone number (must be 10 symbols long)");
                                        personalPhoneNumber = inn.nextLine();
                                    } while (personalPhoneNumber.length() != 10);
                                    System.out.println("Enter address");
                                    String address = inn.nextLine();
                                    long phoneNumber = Long.parseLong(personalPhoneNumber);
                                    long egn = Long.parseLong(EGN);
                                    try {
                                        dbo.addNursesToDB(forname, fathername, surname, egn, address, phoneNumber);
                                    } catch (SQLException e) {
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    String doctorEGN;
                                    String doctorPersonalPhoneNumber;
                                    System.out.println("Enter forname");
                                    inn.nextLine();
                                    String doctorForname = inn.nextLine();
                                    System.out.println("Enter fathername");
                                    String doctorFathername = inn.nextLine();
                                    System.out.println("Enter surname");
                                    String doctorSurname = inn.nextLine();
                                    do {
                                        System.out.println("Enter EGN (must be 10 symbols long)");
                                        doctorEGN = inn.nextLine();
                                    } while (doctorEGN.length() != 10);
                                    do {
                                        System.out.println("Enter phone number (must be 10 symbols long)");
                                        doctorPersonalPhoneNumber = inn.nextLine();
                                    } while (doctorPersonalPhoneNumber.length() != 10);
                                    System.out.println("Enter address");
                                    String doctorAddress = inn.nextLine();
                                    long doctorPhoneNumber = Long.parseLong(doctorPersonalPhoneNumber);
                                    long doctorEgn = Long.parseLong(doctorEGN);
                                    try {
                                        dbo.addDoctorsToDB(doctorForname, doctorFathername, doctorSurname, doctorEgn, doctorAddress, doctorPhoneNumber);
                                    } catch (SQLException e) {
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    depHead = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    break;
                case 2:
                    isTrue = false;
                    System.exit(0);
                    inn.close();
                default:
                    break;
            }
        }
    }
}
