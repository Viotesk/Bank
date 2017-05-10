
/**
 * Created by trein on 10.05.17.
 */
public class Bank {

    public void transferMoney(Account acc1, Account acc2, int amount) throws InterruptedException {


        StringBuilder sb = new StringBuilder();

        sb.append("Transaction between accounts: ")
                .append(String.valueOf(acc1.getId())).append(" and ")
                .append(String.valueOf(acc2.getId())).append('\n');

        if(acc1.getId() < acc2.getId()) {
            synchronized (acc1) {
                if (amount > acc1.getBalance()) {
                    sb.append("Not implemented. Reason: Not enough money");
                    MailerThread.addToQueue(sb.toString());
                    return;
                }

                synchronized (acc2) {
                    acc1.setBalance(acc1.getBalance() - amount);
                    acc2.setBalance(acc2.getBalance() + amount);
                }
            }
        } else {
            synchronized (acc2) {
                if (amount > acc1.getBalance()) {
                    sb.append("Not implemented. Reason: Not enough money");
                    MailerThread.addToQueue(sb.toString());
                    return;
                }
                synchronized (acc1) {
                    acc1.setBalance(acc1.getBalance() - amount);
                    acc2.setBalance(acc2.getBalance() + amount);
                }
            }
        }


        sb.append("Successful");
        MailerThread.addToQueue(sb.toString());

    }

}
