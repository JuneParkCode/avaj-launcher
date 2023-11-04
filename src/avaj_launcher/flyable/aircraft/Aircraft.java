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

    protected Aircraft(long id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    public abstract void updateConditions();

    protected void updateCoordinates(int longitudeDiff, int latitudeDiff, int heightDiff) {
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        height += heightDiff;
        longitude += longitudeDiff;
        latitude += latitudeDiff;
        coordinates.setCoordinates(longitude, latitude, height);
    }

    public void land() {
        Logger.log(String.format("%s : landing...", getInfo()));
        this.weatherTower.unregister(this);
    }
}
