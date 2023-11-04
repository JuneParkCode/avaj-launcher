package avaj_launcher.flyable.aircraft;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import avaj_launcher.flyable.Flyable;
import avaj_launcher.flyable.aircraft.AircraftFactory.InvalidAircraftTypeException;

public class AircraftParser {
    public AircraftParser() {
    }

    /**
     * FLYABLE LINE PARSER
     * FORMAT : TYPE(STRING) NAME(STRING) LONGITUDE(NUMBER) LATITUDE(NUMBER)
     * HEIGHT(NUMBER)
     * 
     * @param line must be formatted by "^(\\w+) (\\w+) (\\d+) (\\d+) (\\d+)$"
     * @return Flyable
     * @throws InvalidSyntaxException
     * @throws InvalidAircraftTypeException
     */
    public Flyable parse(final String line)
            throws InvalidSyntaxException, InvalidAircraftTypeException {
        final AircraftFactory aircraftFactory = AircraftFactory.getInstance();
        final String regex = "^(\\w+) (\\w+) (\\d+) (\\d+) (\\d+)$";
        final Matcher matcher = Pattern.compile(regex).matcher(line);
        if (!matcher.find()) {
            throw new InvalidSyntaxException();
        }

        final String type = matcher.group(1);
        final String name = matcher.group(2);
        final int longitude = Integer.parseInt(matcher.group(3));
        final int latitude = Integer.parseInt(matcher.group(4));
        final int height = Integer.parseInt(matcher.group(5));
        return aircraftFactory.newAircraft(type, name, new Coordinates(longitude, latitude, height));
    }

    public class InvalidSyntaxException extends Exception {
        public static final String MESSAGE = "Invalid scenario syntax";

        public InvalidSyntaxException() {
            super(MESSAGE);
        }

        public InvalidSyntaxException(String cause) {
            super(String.format("%s : %s", MESSAGE, cause));
        }
    }
}
