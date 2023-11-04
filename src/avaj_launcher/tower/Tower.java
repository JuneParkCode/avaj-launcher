package avaj_launcher.tower;

import java.util.LinkedList;
import java.util.List;

import avaj_launcher.flyable.Flyable;
import avaj_launcher.simulator.Logger;

public class Tower {
    private List<Flyable> observers;
    private List<Flyable> unregistList;

    public Tower() {
        this.observers = new LinkedList<Flyable>();
        this.unregistList = new LinkedList<Flyable>();
    }

    public void register(Flyable flyable) {
        if (this.observers.contains(flyable))
            return;
        this.observers.add(flyable);
        Logger.log(String.format("Tower says: %s registered to weather tower", flyable.getInfo()));
    }

    public void unregister(Flyable flyable) {
        this.unregistList.add(flyable);
        Logger.log(String.format("Tower says: %s unregistered from weather tower.", flyable.getInfo()));
    }

    protected void conditionChanged() {
        for (Flyable observer : this.observers) {
            observer.updateConditions();
        }
    }

    protected void unregisterAll() {
        for (Flyable unregister : this.unregistList) {
            observers.remove(unregister);
        }
        this.unregistList.clear();
    }
}
