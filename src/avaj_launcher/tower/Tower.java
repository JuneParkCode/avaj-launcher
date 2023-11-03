package avaj_launcher.tower;

import java.util.LinkedList;
import java.util.List;

import avaj_launcher.flyable.Flyable;
import avaj_launcher.util.Logger;

public class Tower {
    private List<Flyable> observers;
    private List<Flyable> unregistList;

    public Tower() {
        observers = new LinkedList<Flyable>();
        unregistList = new LinkedList<Flyable>();
    }

    public void register(Flyable p_Flyable) {
        if (observers.contains(p_Flyable))
            return;
        observers.add(p_Flyable);
        Logger.log(String.format("Tower says: %s registered to weather tower", p_Flyable.getInfo()));
    }

    public void unregister(Flyable p_Flyable) {
        unregistList.add(p_Flyable);
        Logger.log(String.format("Tower says: %s unregistered from weather tower.", p_Flyable.getInfo()));
    }

    protected void conditionChanged() {
        for (Flyable observer : observers) {
            observer.updateConditions();
        }
    }

    protected void unregisterAll() {
        for (Flyable unregister : unregistList) {
            observers.remove(unregister);
        }
        unregistList.clear();
    }
}
