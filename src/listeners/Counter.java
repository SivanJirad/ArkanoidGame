package listeners;

/**
 * @author sivan Jhirad, ID: 209193481
 * listeners.Counter
 */

public class Counter {
    private int counter;

    /**
     * constructor.
     * @param counterStart counter in the begin og thr game
     */
    public Counter(int counterStart) {
        this.counter = counterStart;
    }

    /**
     * increase counter.
     * @param number  - add number to current count
     */
    public void increase(int number) {
        counter = counter + number;
    }

    /**
     * decrease counter.
     * @param number subtract number from current count.
     */
    public void decrease(int number) {
        counter = counter - number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return counter;
    }
}