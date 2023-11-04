package avaj_launcher.weather;

import avaj_launcher.flyable.aircraft.Coordinates;

public class WeatherProvider {
    private String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };
    private static WeatherProvider instance;
    private static int time = 0;
    private static int salt = 0;

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
        int position = coordinates.getLongitude() * 10000 + coordinates.getLatitude() * 100
                + coordinates.getHeight();
        int index = (position + time + salt) % weather.length;

        return weather[index];
    }
}
