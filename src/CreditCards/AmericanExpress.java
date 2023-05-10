package CreditCards;

import java.util.List;
import Controller.Profile;

public class AmericanExpress extends CreditCard{

    List<String> rewards;
    public AmericanExpress(double money) {
        balance = money;
        cashBack = 2.3;
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
    public void listRewards() {}
}