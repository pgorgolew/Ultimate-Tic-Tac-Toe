package agh.ics.project2;

public enum Positions {
    TOP_LEFT(0),
    TOP_MIDDLE(1),
    TOP_RIGHT(2),
    MIDDLE_LEFT(3),
    MIDDLE(4),
    MIDDLE_RIGHT(5),
    BOTTOM_LEFT(6),
    BOTTOM_MIDDLE(7),
    BOTTOM_RIGHT(8);

    final int positionNumber;

    Positions(int positionNumber) {
        this.positionNumber = positionNumber;
    }

    public static Positions getPositionFromNumber(int givenNumber) {
        for (Positions position : Positions.values()) {
            if (position.positionNumber == givenNumber) {
                return position;
            }
        }

        return null;
    }

    public static Positions getPositionFromCords(int y, int x) {
        return getPositionFromNumber(x + 3 * y);
    }
}
