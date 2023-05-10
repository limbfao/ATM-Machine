package States;
import java.util.*;

import Controller.Profile;
import Store.Shop;
import Store.Item;

public class State {

    HashMap<String, Profile> users;

    public State(HashMap<String, Profile> users, Scanner sc) throws InterruptedException {
        this.users = users;
        switchUsers(sc);
    }

    public void switchUsers(Scanner sc) throws InterruptedException {
        System.out.println("---------------------  List of Users in our database  ---------------------");
        for (Map.Entry<String, Profile> entry : users.entrySet()) {
            System.out.println("\t\t\t\t\t\t\tUser: " + entry.getKey());
        }
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\tWhat user would you like to access?");
        System.out.println("To access a user type the user's name followed by their 4 digit pin. ex: 'joseph' '1234'");
        String username = sc.next();
        int number = sc.nextInt();
        if (users.containsKey(username)) {
            Profile player = users.get(username);
            if(player.pin == number) {
                System.out.println("Access Granted!");
                menuState(sc, player);
            } else {
                System.out.println("Error try again!");
                switchUsers(sc);
            }
        } else {
            System.out.println("User does not exist, try again!");
            switchUsers(sc);
        }
    }

    public void menuState(Scanner sc, Profile user) throws InterruptedException {

        System.out.println("\t---------------------  MENU  ---------------------");
        System.out.println("\t\t\t\tWhat would you like to do ?!");
        System.out.println("\tCheck your balance - balance");
        System.out.println("\tCheck the type of card you have - card type");
        System.out.println("\tPay someone else - pay 'user' 'amount'");
        System.out.println("\tGo to the store - store");
        System.out.println("\tCheck your bag - bag");
        System.out.println("\tWager your money in a game! - game");
        System.out.println("\tExit the user account - quit");

        System.out.print("\nInput: ");
        String input = sc.next() + sc.nextLine();
        input = input.toLowerCase();
        if (input.compareTo("balance") == 0) {
            user.getBalance();
            menuState(sc, user);
        } else if (input.compareTo("card type") == 0) {
            System.out.println("The user " + user.name + " has a card of type: " + user.typeOfCard);
            System.out.println("This card has a store cashback of: " + user.card.cashBack + "%");
            menuState(sc, user);
        } else if (input.contains("pay")) {
            String[] tokens = input.split(" ");
            if (users.containsKey(tokens[1])) {
                Profile userToPayTo = users.get(tokens[1]);
                double amount = Double.valueOf(tokens[2]);

                if (!balanceChecker(user, amount)) {
                    System.out.println("--- Insufficient Funds! ---");
                    menuState(sc, user);
                }

                user.card.sendMoney(userToPayTo, amount);
                System.out.println("Transaction Complete!");
            } else {
                System.out.println("Invalid user or User does not exist!");
            }
            menuState(sc, user);
        } else if (input.contains("quit")) {
            switchUsers(sc);
        } else if (input.contains("store")) {
            store(user, sc);
        } else if (input.contains("bag")) {
            System.out.println("You currently have ...");
            HashMap<String, HashSet<Item>> userBag = user.bag;
            for (Map.Entry<String, HashSet<Item>> entry : userBag.entrySet()) {
                System.out.println("\tItem: " + entry.getKey() + " | Count: " + entry.getValue().size());
            }
            System.out.println("Would you like to see more details about your item? (Y/N)");
            String newInput = sc.next();
            if (newInput.contains("Y")) {
                itemInfo(sc, userBag);
            }
            menuState(sc, user);
        } else if (input.contains("game")) {
            gameMenu(user, sc);
        } else {
            System.out.println("Bad Input. Try Again.");
            menuState(sc, user);
        }
    }

    private void itemInfo(Scanner sc, HashMap<String, HashSet<Item>> userBag) {
        System.out.println("What item would you like to know more about?");
        String itemToGet = sc.next();
        itemToGet = wordChecker(itemToGet);
        HashSet<Item> set;
        if (userBag.containsKey(itemToGet)) {
             set = userBag.get(itemToGet);
        } else {
            System.out.println("Looks like you don't even have that item!");
            return;
        }
        for (Item entry : set) {
            System.out.println("Item name: " + entry.name + " | Color: " + entry.color + " | Original Price: $" + entry.price + " | Original Owner: " + entry.originalOwner + " | Time Bought: " + entry.timeBought);
        }
        System.out.println("Would you like to check another item out? (Y/N)");
        String recur = sc.next().toUpperCase();
        if (recur.contains("Y")) {
            itemInfo(sc, userBag);
        } else if (recur.contains("N")) {
            return;
        } else {
            System.out.println("Bad Input! We're going to assume no :(");
            return;
        }
    }

    private void gameMenu(Profile user, Scanner sc) throws InterruptedException {
        System.out.println("\t\t\t\t---------------------  Game - Instructions  ---------------------");
        System.out.println("\tYou will be prompted with 5 simple math questions.");
        System.out.println("\tYou will have to stake your money to make money!");
        System.out.println("\tYou need to answer all 5 questions within 10 seconds or you will lose your entire stake!");
        System.out.println("\tIf you do win, you will 2x your money!");
        System.out.println("\tTo play - play");
        System.out.println("\tTo leave this game - quit");

        System.out.print("\nInput: ");
        String input = sc.next().toLowerCase();

        if (input.contains("play")) {
            System.out.println("How much money would you like to bet?");
            double stakeAmount = sc.nextDouble();

            if (!balanceChecker(user, stakeAmount)) {
                System.out.println("--- Insufficient Funds! ---");
                gameMenu(user, sc);
            }

            game(user, sc, stakeAmount);
        } else if (input.contains("quit")) {
            System.out.println("Sorry to see you leave!");
            menuState(sc, user);
        } else {
            System.out.println("Bad Input!");
            menuState(sc, user);
        }
    }

    private void game(Profile user, Scanner sc, double stakeAmount) throws InterruptedException {
        System.out.println("The game will begin in ...");
        Thread.sleep(1000);
        System.out.println("3...");
        Thread.sleep(1000);
        System.out.println("2...");
        Thread.sleep(1000);
        System.out.println("1...");
        Thread.sleep(1000);
        System.out.println("GO!");


        Random rand = new Random();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            int a = rand.nextInt(20);
            int b = rand.nextInt(20);
            System.out.println(a + " + " + b + " ? ");
            int answer = sc.nextInt();
            if (answer != (a+b)) {
                System.out.println("Wrong Answer!");
                System.out.println("You Lost: " + stakeAmount);
                user.card.balance -= stakeAmount;
                gameMenu(user, sc);
                return;
            }
        }
        long endTime = System.currentTimeMillis();

        if (endTime - startTime > 10000) {
            System.out.println("Looks like you took more than 10 seconds :(");
            System.out.println("You lost: " + stakeAmount);
            user.card.balance -= stakeAmount;
            gameMenu(user, sc);
            return;
        }

        double winningAmount = stakeAmount*2;
        System.out.println("Congratulations!");
        System.out.println("$" + winningAmount + " will be awarded to your account.");
        user.card.addMoney(winningAmount);
        gameMenu(user, sc);
    }

    private void store(Profile user, Scanner sc) throws InterruptedException {
        Shop shop = new Shop(user);
        System.out.println("\t\t\t\t---------------------  Store - Instructions  ---------------------");
        System.out.println("\tTo view all items - view");
        System.out.println("\tTo buy an an item - buy 'item' 'color'");
        System.out.println("\tTo go back to the user menu - quit");

        String input = sc.next() + sc.nextLine();

        if (input.contains("view")) {
            shop.viewStore();
            store(user, sc);
        } else if (input.contains("buy")) {
            String[] tokens = input.split(" ");
            if (shop.storeItems.containsKey(tokens[1])) {
                Item itemToPurchase = shop.storeItems.get(tokens[1]);
                String color = tokens[2];
                itemToPurchase.customizeColor(color);

                // Checks if the user has enough money in their account to buy the item
                if (!balanceChecker(user, itemToPurchase.price)) {
                    System.out.println("--- Insufficient Funds! ---");
                    store(user, sc);
                }
                // Buys the item
                user.card.purchase(itemToPurchase.price);
                // Adds the item to the users bag
                user.bag.computeIfAbsent(itemToPurchase.name, v -> new HashSet<>()).add(itemToPurchase);

                System.out.println("Item successfully purchased!");
            } else {
                System.out.println("That item is not in store!");
            }
            store(user, sc);
        } else if (input.contains("quit")) {
            menuState(sc, user);
        }
    }

    private String wordChecker(String word) {
        word = word.toLowerCase();

        switch (word) {
            case "iphone":
                return "iPhone";
            case "basketball":
                return "BasketBall";
            case "airpods":
                return "Airpods";
            case "shoes":
                return "Shoes";
            case "hoodie":
                return "Hoodie";
            case "shirt":
                return "Shirt";
        }
        return "ERROR";

    }

    private boolean balanceChecker(Profile user, double amountToSpend) {

        if (user.getBalance() >= amountToSpend) {
            return true;
        }
        return false;

    }

}
