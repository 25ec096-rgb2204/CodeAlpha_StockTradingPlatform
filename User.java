import java.util.ArrayList;

public class User {
    String name;
    double balance;
    ArrayList<Transaction> transactions = new ArrayList<>();

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
}
