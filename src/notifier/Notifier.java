package notifier;

import listeners.HitListener;

import java.util.LinkedList;
import java.util.List;

/**
 * @author sivan Jhirad, ID: 209193481
 * notifiers.notifier.Notifier
 */

public abstract class  Notifier implements HitNotifier {
    private List<HitListener> hitListeners;

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * @return get hitListeners
     */
    public List getHitListeners() {
        return hitListeners;
    }

    /**
     * create hit listener list.
     */
    public void createHitListenerList() {
        this.hitListeners = new LinkedList<>();
    }
}
