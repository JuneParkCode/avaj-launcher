package avaj_launcher.tower;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import avaj_launcher.flyable.Flyable;
import avaj_launcher.utils.logger.Logger;

public class Tower {
    private List<Flyable> observers;
    // unregist list 의 경우, observers iteration 과정에서 observer 가 remove 되면서 iterator
    // 손상이 일어나는 것을 막습니다.
    private List<Flyable> unregistList;

    public Tower() {
        this.observers = new LinkedList<Flyable>();
        this.unregistList = new ArrayList<Flyable>();
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
        this.observers.forEach(Flyable::updateConditions);
        this.observers.removeIf(flyable -> this.unregistList.contains(flyable));
        this.unregistList.clear();
    }
}
