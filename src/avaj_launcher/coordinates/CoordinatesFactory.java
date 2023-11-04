package avaj_launcher.coordinates;

/**
 * I created this class because you can't instantiate Coordinates from other
 * packages.
 */
public class CoordinatesFactory {
    public static Coordinates newCoordinates(int longitude, int latitude, int height) {
        return new Coordinates(longitude, latitude, height);
    }
}
