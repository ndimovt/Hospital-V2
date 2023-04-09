package io.github.ndimovt.hospitalv2;

import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        GetInformationFromDB getInformationFromDB = GetInformationFromDB.getInstance();
        RemoveStaffFromDB removeStaffFromDB = RemoveStaffFromDB.getInstance();
        AddInformationToDB addInfoToDB = AddInformationToDB.getInstance();
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
                                    try {
                                        getInformationFromDB.checkPatientsInfo();
                                    }catch (SQLException sqe){
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        sqe.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    String noTreatmentPatientEGN;
                                    System.out.println("Enter patient's EGN (must be 10 symbols long)");
                                    do {
                                        noTreatmentPatientEGN = inn.nextLine();
                                    } while (noTreatmentPatientEGN.length() != 10);
                                    long egn = Long.parseLong(noTreatmentPatientEGN);
                                    System.out.println("Enter patient's illness");
                                    //inn.nextLine();
                                    String illness = inn.nextLine();
                                    System.out.println("Enter patient's treatment");
                                    String treatment = inn.nextLine();
                                    try {
                                        addInfoToDB.updatePatient(egn, illness, treatment);
                                    } catch (SQLException e) {
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                    String releasePatientEGN;
                                    System.out.println("Enter patient's EGN1");
                                    do {
                                        releasePatientEGN = inn.nextLine();
                                    } while (releasePatientEGN.length() != 10);
                                    egn = Long.parseLong(releasePatientEGN);
                                    try {
                                        removeStaffFromDB.releasePatient(egn);
                                    }catch (SQLException sqe){
                                        System.out.println("Can't connect to database. Please try again later ot call your IT support");
                                        sqe.printStackTrace();
                                    }
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
                            addInfoToDB.addPatientToDB(forname, fathername, surname, egn, address);
                        } catch (SQLException e) {
                            System.out.println("Can't connect to Database. Please try again later or call your IT support");
                            e.printStackTrace();
                        }
                    } else if ((username.equals(dh.getUsername())) && (password.equals(dh.getPassword()))) {
                        boolean depHead = true;
                        System.out.println("You enter the system as Head of the Department");
                        while (depHead) {
                            System.out.println("1) Add nurse to the system 2)Add doctor to system 3)Release nurse 4)Release doctor " +
                                    "5) Get information for Doctors 6)Get information for Nurses 7)Get information for Patients 8)Back to main menu");
                            int departmentHead = inn.nextInt();
                            switch (departmentHead) {
                                case 1:
                                    String nurseEGN;
                                    String nursePersonalPhoneNumber;
                                    System.out.println("Enter forname");
                                    inn.nextLine();
                                    String nurseForname = inn.nextLine();
                                    System.out.println("Enter fathername");
                                    String nurseFathername = inn.nextLine();
                                    System.out.println("Enter surname");
                                    String nurseSurname = inn.nextLine();
                                    do {
                                        System.out.println("Enter EGN (must be 10 symbols long)");
                                        nurseEGN = inn.nextLine();
                                    } while (nurseEGN.length() != 10);
                                    do {
                                        System.out.println("Enter phone number (must be 10 symbols long)");
                                        nursePersonalPhoneNumber = inn.nextLine();
                                    } while (nursePersonalPhoneNumber.length() != 10);
                                    System.out.println("Enter address");
                                    String nurseAddress = inn.nextLine();
                                    long nursePhoneNumber = Long.parseLong(nursePersonalPhoneNumber);
                                    long nurseEgn = Long.parseLong(nurseEGN);
                                    try {
                                        addInfoToDB.addNursesToDB(nurseForname, nurseFathername, nurseSurname, nurseEgn, nurseAddress, nursePhoneNumber);
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
                                        addInfoToDB.addDoctorsToDB(doctorForname, doctorFathername, doctorSurname, doctorEgn, doctorAddress, doctorPhoneNumber);
                                    } catch (SQLException e) {
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                    String releaseNurseEGN;
                                    System.out.println("Enter nurse's EGN");
                                    do{
                                        releaseNurseEGN = inn.nextLine();
                                    }while (releaseNurseEGN.length() != 10);
                                    long releaseNurse = Long.parseLong(releaseNurseEGN);
                                    try {
                                        removeStaffFromDB.releaseNurse(releaseNurse);
                                    }catch (SQLException e) {
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        e.printStackTrace();
                                    }
                                    break;
                                case 4:
                                    String doctorReleaseEGN;
                                    System.out.println("Enter doctor's EGN");
                                    do{
                                        doctorReleaseEGN = inn.nextLine();
                                    }while (doctorReleaseEGN.length() != 10);
                                    long releaseDoctorEgn = Long.parseLong(doctorReleaseEGN);
                                    try {
                                        removeStaffFromDB.releaseDoctor(releaseDoctorEgn);
                                    }catch (SQLException e) {
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        e.printStackTrace();
                                    }
                                    break;
                                case 5:
                                    String checkDoctorEGN;
                                    do{
                                        System.out.println("Enter doctor's EGN");
                                        checkDoctorEGN = inn.nextLine();
                                    }while (checkDoctorEGN.length() != 10);
                                    try{
                                        getInformationFromDB.checkDoctorsInfo(checkDoctorEGN);
                                    }catch (SQLException se){
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        se.printStackTrace();
                                    }
                                    break;
                                case 6:
                                    String nurseInfoEGN;
                                    System.out.println("Enter nurse's EGN: ");
                                    do{
                                        nurseInfoEGN = inn.nextLine();
                                    }while (nurseInfoEGN.length() != 10);
                                    try{
                                        getInformationFromDB.checkNursesInfo(nurseInfoEGN);
                                    }catch (SQLException we){
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        we.printStackTrace();
                                    }
                                    break;
                                case 7:
                                    try{
                                        getInformationFromDB.checkPatientsInfo();
                                    }catch (SQLException e){
                                        System.out.println("Can't connect to Database. Please try again later or call your IT support");
                                        e.printStackTrace();
                                    }
                                    break;
                                case 8:
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
