/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public interface HitNotifier {

    /**
     * This is a method that adds a given hit listener as a listener to hit events.
     *
     * @param hl the given hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * This is a method that removes a given hit listener as a listener to hit events.
     *
     * @param hl the given hit listener
     */
    void removeHitListener(HitListener hl);
}
