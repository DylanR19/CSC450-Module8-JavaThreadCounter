// Demonstrates two worker threads counting in a controlled sequence.
public class ThreadCounter {

    private static final int COUNTER_LIMIT = 20;

    private static void countUp() {
        System.out.println("Thread 1: Counting up");

        for (int number = 0; number <= COUNTER_LIMIT; number++) {
            System.out.println("Up: " + number);
        }
    }

    private static void countDown() {
        System.out.println("\nThread 2: Counting down");

        for (int number = COUNTER_LIMIT; number >= 0; number--) {
            System.out.println("Down: " + number);
        }
    }

    public static void main(String[] args) {
        System.out.println("Java Two-Thread Counter Demonstration\n");

        Thread upwardCounter =
                new Thread(ThreadCounter::countUp, "UpwardCounter");

        Thread downwardCounter =
                new Thread(ThreadCounter::countDown, "DownwardCounter");

        try {
            upwardCounter.start();
// Wait for the upward count to finish before starting the countdown.
            upwardCounter.join();

            downwardCounter.start();
            downwardCounter.join();

            System.out.println("\nBoth counters have finished.");
        } catch (InterruptedException exception) {
// Restore the interrupt status because join() clears it when interrupted.
            Thread.currentThread().interrupt();
            System.err.println(
                    "The main thread was interrupted while waiting.");
        }
    }
}