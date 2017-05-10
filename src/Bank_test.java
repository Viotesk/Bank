import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by trein on 10.05.17.
 */
public class Bank_test {
    private static Bank bank = new Bank();
    private static List<Account> accounts = new ArrayList<>();

    static {
        for (int i = 0; i < 100; i++)
            accounts.add(new Account(i, "user" + i, 250));

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread mailer = new Thread(new MailerThread());
        List<Future<String>> futures = new ArrayList<>();
        mailer.start();

        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            Future<String> submit = pool.submit(new MakeATransactions());
            futures.add(submit);
        }

        for (Future<String> future : futures) {
            future.get();
        }

        int sum = 0;

        for (Account account : accounts) {
            System.out.println(account);
            sum += account.getBalance();
        }

        System.err.println(sum);

    }

    private static class MakeATransactions implements Callable<String> {
        @Override
        public String call() throws Exception {
            int i = 0;
            while (i++ < 200) {
                bank.transferMoney(accounts.get((int) (Math.random() * 100))
                , accounts.get((int) (Math.random() * 100)), (int) (Math.random() * 200));
            }
            return "done";
        }
    }
}
