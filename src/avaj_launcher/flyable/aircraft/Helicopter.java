package avaj_launcher.flyable.aircraft;

import avaj_launcher.utils.logger.Logger;

public class Helicopter extends Aircraft {
    private static final String TYPE = "Helicopter";

    // DEFINE INDEX
    private static final int LONGITUDE = 0;
    private static final int LATITUDE = 1;
    private static final int HEIGHT = 2;
    // { LONGITUDE, LATITUDE, HEIGHT }
    private static final int[] SUN_DIFF = { 10, 0, 2 };
    private static final int[] RAIN_DIFF = { 5, 0, 0 };
    private static final int[] FOG_DIFF = { 1, 0, 0 };
    private static final int[] SNOW_DIFF = { 0, 0, -12 };
    // MESSAGES
    private static final String SUN_MSG = "HELICOPTER IS HOT";
    private static final String RAIN_MSG = "RAIN SPLASHES";
    private static final String FOG_MSG = "TOO FOGGY!!!!";
    private static final String SNOW_MSG = "I WILL BE SNOWCOPTER";

    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        final String weather = this.weatherTower.getWeather(this.coordinates);

        updateByWeather(weather);
        if (this.coordinates.getHeight() == 0)
            land();
    }

    private void updateByWeather(String Weather) {
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();
        int[] weatherDiff = null;
        String message = "";

        switch (Weather) {
            case "SUN":
                weatherDiff = SUN_DIFF;
                message = SUN_MSG;
                break;
            case "RAIN":
                weatherDiff = RAIN_DIFF;
                message = RAIN_MSG;
                break;
            case "FOG":
                weatherDiff = FOG_DIFF;
                message = FOG_MSG;
                break;
            case "SNOW":
                weatherDiff = SNOW_DIFF;
                message = SNOW_MSG;
                break;
            default:
                // never happends
                return;
        }

        longitude += weatherDiff[LONGITUDE];
        latitude += weatherDiff[LATITUDE];
        height += weatherDiff[HEIGHT];
        updateCoordinates(longitude, latitude, height);
        Logger.log(String.format("%s: %s", getInfo(), message));
    }

    @Override
    public String getInfo() {
        return String.format("%s#%s(%d)", TYPE, this.name, this.id);
    }
}
