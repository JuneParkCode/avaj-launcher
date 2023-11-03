package avaj_launcher.coordinates;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    // limits
    public static final int MIN_HEIGHT = 0;
    public static final int MAX_HEIGHT = 100;

    Coordinates(int p_longitude, int p_latitude, int p_height) {
        setLongitude(p_longitude);
        setLatitude(p_latitude);
        setHeight(p_height);
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setLongitude(int p_longitude) {
        longitude = p_longitude;
    }

    public void setLatitude(int p_latitude) {
        latitude = p_latitude;
    }

    public void setHeight(int p_height) {
        if (p_height < 0)
            height = 0;
        else if (p_height > MAX_HEIGHT)
            height = MAX_HEIGHT;
        else
            height = p_height;

    }

    public void setCoordinates(int p_longitude, int p_latitude, int p_height) {
        setLongitude(p_longitude);
        setLatitude(p_latitude);
        setHeight(p_height);
    }
}
