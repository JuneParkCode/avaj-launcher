package avaj_launcher.flyable.aircraft;

import avaj_launcher.coordinates.Coordinates;
import avaj_launcher.flyable.Flyable;
import avaj_launcher.util.Logger;

/*
 * Aircraft class is suggested as concrete class in subject's UML
 * But, Aircraft can not instanced by self. so, I use "abstract class" for this class
*/
public abstract class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
        id = p_id;
        name = p_name;
        coordinates = p_coordinates;
    }

    public abstract void updateConditions();

    protected void updateCoordinates(int longitude_diff, int latitude_diff, int height_diff) {
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        height += height_diff;
        longitude += longitude_diff;
        latitude += latitude_diff;
        coordinates.setCoordinates(longitude, latitude, height);
    }

    public void land() {
        Logger.log(String.format("%s : landing...", getInfo()));
        weatherTower.unregister(this);
    }
}
