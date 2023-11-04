package avaj_launcher.tower;

import avaj_launcher.coordinates.Coordinates;
import avaj_launcher.weather.WeatherProvider;

public class WeatherTower extends Tower {
    private WeatherProvider weatherProvider;

    public WeatherTower() {
        this.weatherProvider = WeatherProvider.getInstance();
    }

    public String getWeather(Coordinates coordinates) {
        return this.weatherProvider.getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        this.weatherProvider.updateTime();
        conditionChanged();
        unregisterAll();
    }
}
