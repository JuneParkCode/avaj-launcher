package avaj_launcher.weather;

import avaj_launcher.coordinates.Coordinates;

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

    public void setTime(int p_time) {
        time = p_time;
    }

    public void updateTime() {
        ++time;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int position = p_coordinates.getLongitude() * 10000 + p_coordinates.getLatitude() * 100
                + p_coordinates.getHeight();
        int index = (position + time + salt) % weather.length;

        return weather[index];
    }
}
