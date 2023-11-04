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

    public Flyable newAircraft(String type, String name, Coordinates coordinates)
            throws InvalidAircraftTypeException {
        switch (type) {
            case "Baloon":
                return new Baloon(++currentId, name, coordinates);
            case "JetPlane":
                return new JetPlane(++currentId, name, coordinates);
            case "Helicopter":
                return new Helicopter(++currentId, name, coordinates);
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
