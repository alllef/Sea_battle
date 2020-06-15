package sample;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * https://www.baeldung.com/java-record-keyword
 */
public record Cell(int row, int col, boolean usedForShip) {



    public Cell(int row, int col) {
        this(row, col, false);
    }

    /**
     * https://en.wikipedia.org/wiki/Prototype_pattern
     * The prototype pattern is a creational design pattern in software development.
     * It is used when the type of objects to create is determined by a prototypical instance,
     * which is cloned to produce new objects. This pattern is used to:
     */
    public Cell useForShip() {
        if (usedForShip) throw new IllegalStateException(String.format("%s is already used for Ship", this.toString()));
        return new Cell(row, col, true);
    }

}
