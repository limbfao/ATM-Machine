package Store;

import Controller.Profile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Shop {

    public HashMap<String, Item> storeItems = new HashMap<>();

    public Shop(Profile user) {
        // To add an item: Add the name and price to the end of the respective lists.
        String[] itemName = {"Shoes", "Airpods", "iPhone", "Hoodie", "Shirt"};
        int[] itemPrice = {80, 180, 599, 40, 25};
        for (int i = 0; i < itemName.length; i++) {
            Item myItem = new Item (itemName[i], itemPrice[i], user.name);
            storeItems.put(itemName[i], myItem);
        }
    }

    public void viewStore() {
        System.out.println("\t\t\t\tItems currently in store!");
        for (Map.Entry<String, Item> entry : storeItems.entrySet()) {
            System.out.println("\t\tItem Name: " + entry.getKey() + ", Price: " + entry.getValue().price);
        }
    }
}

