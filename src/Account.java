/**
 * Created by trein on 10.05.17.
 */
public class Account {
    private int id;
    private String userName;
    private int balance;

    public Account(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Account(int id, String userName, int balance) {
        this(id, userName);
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
