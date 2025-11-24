// Account.java
// Representa una cuenta bancaria.

public class Account {
    private int id;
    private String holderName;
    private double balance;

    public Account(int id, String holderName, double initialBalance) {
        this.id = id;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    public int getId() { return id; }

    public String getHolderName() { return holderName; }

    public void setHolderName(String holderName) { this.holderName = holderName; }

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Monto inválido.");
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Monto inválido.");
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

   
    public String toString() {
        return "ID: " + id + " | Titular: " + holderName + " | Saldo: " + balance;
    }
}

