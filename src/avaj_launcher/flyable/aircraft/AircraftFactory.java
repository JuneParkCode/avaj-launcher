package avaj_launcher.flyable.aircraft;

import avaj_launcher.coordinates.Coordinates;
import avaj_launcher.flyable.Flyable;

public class AircraftFactory {
    private static AircraftFactory instance;
    private static int currentId = 0;

    private AircraftFactory() {
    }

    public static AircraftFactory getInstance() {
        if (instance == null) {
            synchronized (AircraftFactory.class) {
                instance = new AircraftFactory();
            }
        }
        return instance;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates)
            throws InvalidAircraftTypeException {
        switch (p_type) {
            case "Baloon":
                return new Baloon(++currentId, p_name, p_coordinates);
            case "JetPlane":
                return new JetPlane(++currentId, p_name, p_coordinates);
            case "Helicopter":
                return new Helicopter(++currentId, p_name, p_coordinates);
        }
        throw new InvalidAircraftTypeException();
    }

    public class InvalidAircraftTypeException extends Exception {
        public static final String MESSAGE = "inavliad aircraft type";

        public InvalidAircraftTypeException() {
            super(MESSAGE);
        }

        public InvalidAircraftTypeException(String cause) {
            super(String.format("%s : %s", MESSAGE, cause));
        }
    }
}
