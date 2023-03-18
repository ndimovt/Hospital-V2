package io.github.ndimovt.hospitalv2;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner inn = new Scanner(System.in);
        System.out.println("Choose between doctors, nurses or patients");
        String ch= inn.nextLine();
        System.out.println("nam1");
        String firstname = inn.nextLine();
        System.out.println("nam2");
        String secondname = inn.nextLine();
        System.out.println("nam3");
        String thirdname = inn.nextLine();
        System.out.println("addre");
        String address = inn.nextLine();
        System.out.println("egn");
        long EGN = inn.nextLong();
        System.out.println("phone");
        long phoneNumber = inn.nextLong();

            if (ch.equals("nurses")) {
                Nurse ns = new Nurse();
                ns.addNurseToDB(new Nurse(firstname,secondname,thirdname,EGN,address,phoneNumber));
            }
    }
}