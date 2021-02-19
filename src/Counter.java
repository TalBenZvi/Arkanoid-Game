/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class Counter {
    private int value;

    /**
     * This is a constructor method that initiates a Counter object using a given value.
     *
     * @param value the given value
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * This is a method that increases this counter's value by a given number.
     *
     * @param number the given number
     */
    public void increase(int number) {
        this.setValue(this.getValue() + number);
    }

    /**
     * This is a method that decreases this counter's value by a given number.
     *
     * @param number the given number
     */
    public void decrease(int number) {
        this.setValue(this.getValue() - number);
    }

    /**
     * This is a method that sets this counter's value to a given number.
     *
     * @param newValue the given number
     */
    public void setValue(int newValue) {
        this.value = newValue;
    }

    /**
     * This is a method that returns this counter's value.
     *
     * @return this counter's value
     */
    public int getValue() {
        return this.value;
    }
}
