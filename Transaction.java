public class Transaction {
    Stock stock;
    int quantity;
    String type; // BUY or SELL

    public Transaction(Stock stock, int quantity, String type) {
        this.stock = stock;
        this.quantity = quantity;
        this.type = type;
    }
}
