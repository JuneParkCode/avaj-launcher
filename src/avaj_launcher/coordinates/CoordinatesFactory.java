package avaj_launcher.coordinates;

/**
 * I created this class because you can't instantiate Coordinates from other
 * packages.
 */
public class CoordinatesFactory {
    public static Coordinates newCoordinates(int p_longitude, int p_latitude, int p_height) {
        return new Coordinates(p_longitude, p_latitude, p_height);
    }
}
