package Controller;

import java.util.HashMap;
import java.util.Scanner;
import States.State;


public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<String, Profile> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("How many users would you like to enter?");
        int limit = sc.nextInt();
        for (int i = 0; i < limit; i++) {
            System.out.println("List of available card types: AmericanExpress, MasterCard, Visa");
            System.out.println("Enter the user information you want to register - ");
            System.out.println("\tInput Format = 'name' 'balance' 'card type' '4 digit pin'");
            String username = sc.next();
            int balance = sc.nextInt();
            String typeOfCreditCard = sc.next();
            int pin = sc.nextInt();
            if (pin > 9999 || pin < 1000) {
                System.out.println("Pin must 4 digits! Try again.");
                i--;
            } else {
                Profile profile = new Profile(username, balance, typeOfCreditCard, pin);
                map.put(username, profile);
            }

        }
        State state = new State(map, sc);

    }
}