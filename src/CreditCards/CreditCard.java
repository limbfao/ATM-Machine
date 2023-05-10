package CreditCards;
import Controller.Profile;

public abstract class CreditCard {
    public double balance;
    public double cashBack;
    /**
     * This method deducts the amount of money given but gives a percent cashback depending on the type of card.
     * @param purchaseAmount amount of the purchase item, will be deducted from the user's balance.
     */
    public void purchase(double purchaseAmount) {}

    /**
     * This method should be used to insert money into the user balance.
     * @param money
     */
    public void addMoney(double money) {}

    /**
     * This method should only be used to send money to other users, as there is no cashback involved in the transaction.
     * @param money amount of money to be sent
     */
    public void sendMoney(Profile user, double money) {}

}