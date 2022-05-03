package exceptions;

public class HeroExistsInSquadException extends Exception {
    public HeroExistsInSquadException(String message) {
        super(message);
    }
}
