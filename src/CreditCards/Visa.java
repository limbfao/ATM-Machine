package CreditCards;

import Controller.Profile;

import java.util.List;

public class Visa extends CreditCard {

    List<String> rewards;

    public Visa(double money) {
        balance = money;
        cashBack = 1.3;
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

    }
}
