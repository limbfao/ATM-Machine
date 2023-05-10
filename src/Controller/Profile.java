package Controller;

import CreditCards.AmericanExpress;
import CreditCards.CreditCard;
import CreditCards.MasterCard;
import CreditCards.Visa;
import Store.Item;

import java.util.HashMap;
import java.util.HashSet;

public class Profile {
    public HashMap<String, HashSet<Item>> bag;
    public String name;
    public CreditCard card;
    public String typeOfCard;
    public int pin;

    public Profile(String name, double balance, String typeOfCard, int pin) throws Exception {
        setName(name);
        typeOfCard = typeOfCard.toLowerCase();
        if (typeOfCard.compareTo("visa") != 0 && typeOfCard.compareTo("americanexpress") != 0 && typeOfCard.compareTo("mastercard") != 0) {
            throw new Error("Not a valid card name!");
        }

        // Gives the user the type of creditCard
        switch(typeOfCard) {
            case("americanExpress"):
                card = new AmericanExpress(balance);
                this.typeOfCard = "American Express";
                break;
            case("mastercard"):
                card = new MasterCard(balance);
                this.typeOfCard = "Master Card";
                break;
            case("visa"):
                card = new Visa(balance);
                this.typeOfCard = "Visa";
                break;
        }
        this.pin = pin;
        bag = new HashMap<>();
    }

    void setName(String name) {
        this.name = name;
    }
    String getName() {
        System.out.println(name);
        return name;
    }

    public double getBalance() {
        System.out.format("Balance: $%.2f\n",card.balance);
        return card.balance;
    }
}
