
public class Transaction {
    public void transfer(Account acc1, Account acc2, int amount) {
        if (acc1.getBalance() >= amount) {
            acc1.withdraw(amount);
            acc2.deposit(amount);
        } else {
            System.out.println("Transfer failed: insufficient funds in source account.");
        }
    }
}
