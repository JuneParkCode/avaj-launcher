package avaj_launcher.flyable.aircraft;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    // limits
    public static final int MIN_HEIGHT = 0;
    public static final int MAX_HEIGHT = 100;

    Coordinates(int longitude, int latitude, int height) {
        setLongitude(longitude);
        setLatitude(latitude);
        setHeight(height);
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setHeight(int height) {
        if (height < 0)
            this.height = 0;
        else if (height > MAX_HEIGHT)
            this.height = MAX_HEIGHT;
        else
            this.height = height;

    }

    public void setCoordinates(int longitude, int latitude, int height) {
        setLongitude(longitude);
        setLatitude(latitude);
        setHeight(height);
    }
}
