package avaj_launcher.weather;

import avaj_launcher.flyable.aircraft.Coordinates;

public class WeatherProvider {
    private static WeatherProvider instance;
    private static int time = 0;
    private static int salt = 0;
    private static final Weather[] weathers = Weather.values();

    private WeatherProvider() {
        salt = (int) (Math.random() * 100000); // make randomized weather
    }

    public static WeatherProvider getInstance() {
        if (instance == null) {
            synchronized (WeatherProvider.class) {
                instance = new WeatherProvider();
            }
        }
        return instance;
    }

    public void setTime(int newTime) {
        time = newTime;
    }

    public void updateTime() {
        ++time;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int position = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        int index = (position + time + salt) % weathers.length;

        return weathers[index].name();
    }
}
