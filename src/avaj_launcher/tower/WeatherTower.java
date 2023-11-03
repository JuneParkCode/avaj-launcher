package avaj_launcher.tower;

import avaj_launcher.coordinates.Coordinates;
import avaj_launcher.weather.WeatherProvider;

public class WeatherTower extends Tower {
    private WeatherProvider weatherProvider;

    public WeatherTower() {
        weatherProvider = WeatherProvider.getInstance();
    }

    public String getWeather(Coordinates p_Coordinates) {
        return weatherProvider.getCurrentWeather(p_Coordinates);
    }

    public void changeWeather() {
        weatherProvider.updateTime();
        conditionChanged();
        unregisterAll();
    }
}
