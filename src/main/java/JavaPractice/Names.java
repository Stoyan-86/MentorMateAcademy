package JavaPractice;

import java.util.Scanner;


public class Names {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your first name:");
        String firstName = sc.nextLine();

        System.out.println("Please enter your middle name:");
        String middleName = sc.nextLine();

        System.out.println("Please enter your last name:");
        String lastName = sc.nextLine();

        System.out.format("Your name is: %s %s %s ", firstName, middleName, lastName);
    }
}
