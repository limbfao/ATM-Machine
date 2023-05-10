package CreditCards;
import Controller.Profile;

public class MasterCard extends CreditCard{

    public MasterCard(double money) {
        balance = money;
        cashBack = 1.8;
    }

    public void purchase(double money) {
        double actualDeduction = money - (money * (cashBack / 100));
        balance -= actualDeduction;
    }

    public void addMoney(double money) {
        this.balance += money;
    }

    public void sendMoney(Profile user, double amount) {
        balance -= amount;
        user.card.addMoney(amount);
    }

    public void listRewards() {
        System.out.println("Rewards in your MasterCard!");
        System.out.println("CashBack of 1.3% on all purchases");
        System.out.println("15% off on all store transactions!");
        System.out.println("5% back when paying other users!");
    }
}