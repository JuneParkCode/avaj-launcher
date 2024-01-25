package avaj_launcher.flyable.aircraft;

import avaj_launcher.utils.logger.Logger;

public class Helicopter extends Aircraft {
    private static final String TYPE = "Helicopter";

    // is there any better solution?
    // DEFINE INDEX
    private static final int LONGITUDE = 0;
    private static final int LATITUDE = 1;
    private static final int HEIGHT = 2;
    // { LONGITUDE, LATITUDE, HEIGHT }
    private static final int[] SUN_DIFF = { 10, 0, 2 };
    private static final int[] RAIN_DIFF = { 5, 0, 0 };
    private static final int[] FOG_DIFF = { 1, 0, 0 };
    private static final int[] SNOW_DIFF = { 0, 0, -12 };

    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
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

    @Override
    public String getInfo() {
        return String.format("%s#%s(%d)", TYPE, this.name, this.id);
    }
}
