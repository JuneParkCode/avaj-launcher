package avaj_launcher.flyable.aircraft;

import avaj_launcher.utils.logger.Logger;

public class Baloon extends Aircraft {
    private static final String TYPE = "Baloon";

    // DEFINE INDEX
    private static final int LONGITUDE = 0;
    private static final int LATITUDE = 1;
    private static final int HEIGHT = 2;
    // { LONGITUDE, LATITUDE, HEIGHT }
    private static final int[] SUN_DIFF = { 2, 0, 4 };
    private static final int[] RAIN_DIFF = { 0, 0, -5 };
    private static final int[] FOG_DIFF = { 0, 0, -3 };
    private static final int[] SNOW_DIFF = { 0, 0, -15 };
    // MESSAGES
    private static final String SUN_MSG = "BOOLON IS HOT.";
    private static final String RAIN_MSG = "BOOLON IS WET";
    private static final String FOG_MSG = "TOO FOGGY! I CAN'T SEE ANYTHING!";
    private static final String SNOW_MSG = "BOOLON IS COLD!!";

    public Baloon(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
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
