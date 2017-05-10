import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by trein on 10.05.17.
 */
public class MailerThread implements Runnable {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10, true);

    public static void addToQueue(String s) {
        queue.offer(s);
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
