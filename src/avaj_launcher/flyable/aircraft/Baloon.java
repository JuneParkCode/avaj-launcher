package avaj_launcher.flyable.aircraft;

import avaj_launcher.coordinates.Coordinates;
import avaj_launcher.util.Logger;

public class Baloon extends Aircraft {
    private static final String TYPE = "Baloon";

    // is there any better solution?
    // DEFINE INDEX
    private static final int LONGITUDE = 0;
    private static final int LATITUDE = 1;
    private static final int HEIGHT = 2;
    // { LONGITUDE, LATITUDE, HEIGHT }
    private static final int[] SUN_DIFF = { 2, 0, 4 };
    private static final int[] RAIN_DIFF = { 0, 0, -5 };
    private static final int[] FOG_DIFF = { 0, 0, -3 };
    private static final int[] SNOW_DIFF = { 0, 0, -15 };

    public Baloon(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    public void updateConditions() {
        final String weather = this.weatherTower.getWeather(this.coordinates);

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
        if (this.coordinates.getHeight() == 0)
            land();
    }

    public String getInfo() {
        return String.format("%s#%s(%d)", TYPE, this.name, this.id);
    }
}
