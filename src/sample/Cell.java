package sample;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * https://www.baeldung.com/java-record-keyword
 */
public record Cell(char row, int col, boolean usedForShip) {

    private static final Pattern rowPattern = Pattern.compile("([a-z])");

    public Cell {
        Objects.checkIndex(col, 11);
        if (!rowPattern.matcher(String.valueOf(row)).find()) throw new IllegalArgumentException(String.format("row: '%c' should be ([a-z])", row));
    }

    public Cell(char row, int col) {
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
