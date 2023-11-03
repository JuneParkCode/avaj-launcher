package avaj_launcher.flyable;

import avaj_launcher.tower.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    public void registerTower(WeatherTower p_tower) {
        weatherTower = p_tower;
        weatherTower.register(this);
    }

    public abstract String getInfo();

    public abstract void land();
}
