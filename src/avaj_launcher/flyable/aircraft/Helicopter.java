package avaj_launcher.flyable.aircraft;

import avaj_launcher.coordinates.Coordinates;
import avaj_launcher.util.Logger;

public class Helicopter extends Aircraft {
    private final static String TYPE = "Helicopter";

    // is there any better solution?
    // DEFINE INDEX
    private final static int LONGITUDE = 0;
    private final static int LATITUDE = 1;
    private final static int HEIGHT = 2;
    // { LONGITUDE, LATITUDE, HEIGHT }
    private final static int[] SUN_DIFF = { 10, 0, 2 };
    private final static int[] RAIN_DIFF = { 5, 0, 0 };
    private final static int[] FOG_DIFF = { 1, 0, 0 };
    private final static int[] SNOW_DIFF = { 0, 0, -12 };

    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    public void updateConditions() {
        final String weather = weatherTower.getWeather(coordinates);

        switch (weather) {
            case "SUN":
                updateCoordinates(SUN_DIFF[LONGITUDE], SUN_DIFF[LATITUDE], SUN_DIFF[HEIGHT]);
                Logger.log(String.format("%s: SUNNY DAY.", getInfo()));
                break;
            case "RAIN":
                updateCoordinates(RAIN_DIFF[LONGITUDE], RAIN_DIFF[LATITUDE], RAIN_DIFF[HEIGHT]);
                Logger.log(String.format("%s: RAIN RAIN RAIN.", getInfo()));
                break;
            case "FOG":
                updateCoordinates(FOG_DIFF[LONGITUDE], FOG_DIFF[LATITUDE], FOG_DIFF[HEIGHT]);
                Logger.log(String.format("%s: TOO FOGGY ", getInfo()));
                break;
            case "SNOW":
                updateCoordinates(SNOW_DIFF[LONGITUDE], SNOW_DIFF[LATITUDE], SNOW_DIFF[HEIGHT]);
                Logger.log(String.format("%s: IT SNOWS!! ", getInfo()));
                break;
        }
        if (coordinates.getHeight() == 0)
            land();
    }

    public String getInfo() {
        return String.format("%s#%s(%d)", TYPE, name, id);
    }
}
