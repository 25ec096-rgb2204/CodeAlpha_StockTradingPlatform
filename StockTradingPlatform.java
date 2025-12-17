import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {

    static ArrayList<Stock> marketStocks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        marketStocks.add(new Stock("AAPL", 150.0));
        marketStocks.add(new Stock("GOOGL", 2800.0));
        marketStocks.add(new Stock("TSLA", 700.0));

        System.out.print("Enter your name: ");
        String name = sc.next();
        User user = new User(name, 10000.0);

        while (true) {
            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewMarket();
                    break;
                case 2:
                    buyStock(sc, user);
                    break;
                case 3:
                    sellStock(sc, user);
                    break;
                case 4:
                    viewPortfolio(user);
                    break;
                case 5:
                    System.out.println("Thank you for trading!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void viewMarket() {
        for (Stock s : marketStocks) {
            System.out.println(s.symbol + " : $" + s.price);
        }
    }

    static void buyStock(Scanner sc, User user) {
        System.out.print("Enter stock symbol: ");
        String symbol = sc.next();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        for (Stock s : marketStocks) {
            if (s.symbol.equalsIgnoreCase(symbol)) {
                double cost = s.price * qty;
                if (user.balance >= cost) {
                    user.balance -= cost;
                    user.transactions.add(new Transaction(s, qty, "BUY"));
                    System.out.println("Stock purchased successfully!");
                } else {
                    System.out.println("Insufficient balance");
                }
                return;
            }
        }
        System.out.println("Stock not found");
    }

    static void sellStock(Scanner sc, User user) {
        System.out.print("Enter stock symbol: ");
        String symbol = sc.next();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        for (Transaction t : user.transactions) {
            if (t.stock.symbol.equalsIgnoreCase(symbol) && t.type.equals("BUY")) {
                user.balance += t.stock.price * qty;
                user.transactions.add(new Transaction(t.stock, qty, "SELL"));
                System.out.println("Stock sold successfully!");
                return;
            }
        }
        System.out.println("No stock to sell");
    }

    static void viewPortfolio(User user) {
        System.out.println("\nBalance: $" + user.balance);
        for (Transaction t : user.transactions) {
            System.out.println(t.type + " - " + t.stock.symbol + " x " + t.quantity);
        }
    }
}
