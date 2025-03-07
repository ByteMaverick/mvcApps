package mvc;
/*
Edits:
Anthony Kieu: 3/6 created file
Anthony Kieu: 3/6 completed implemented Publisher with all its methods
 */

import java.util.HashSet;
import java.util.Set;

public class Publisher {
    private Set<Subscriber> subscribers;
    public Publisher() {
        this.subscribers = new HashSet<Subscriber>();
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
    public void notifySubscribers() {
        for (Subscriber subscriber: subscribers) {
            subscriber.update();
        }
    }
}
