package sample;


import java.util.ArrayList;

public class Human extends Player {

    private Integer setupCounter = 0;

Integer getSetupCounter(){
    return setupCounter;
}
    public Human() {
        super();
    }


    public AllCoordsVariants setupShip(int setupRow, int setupCol) {
        AllCoordsVariants allCoordsVariants = new AllCoordsVariants();

        for (int cardinalDirection = 1; cardinalDirection <= 4; cardinalDirection++) {

            int row = setupRow;
            int col = setupCol;
            ArrayList<Cell> coords = new ArrayList<>();

            for (int i = 0; i < shipList.get(setupCounter).getSize(); i++) {

                if (!areShipsNear(row, col)) {

                    switch (cardinalDirection) {
                        case 1 -> coords.add(getGrid()[row--][col]);
                        case 2 -> coords.add(getGrid()[row++][col]);
                        case 3 -> coords.add(getGrid()[row][col++]);
                        case 4 -> coords.add(getGrid()[row][col--]);
                    }

                    if (row >= getGrid().length || col >= getGrid().length || row < 0 || col < 0)
                        break;
                } else break;
            }

            if (coords.size() == shipList.get(setupCounter).getSize())
                allCoordsVariants = allCoordsVariants.coordsVariantsOf(cardinalDirection, coords);

        }

        return allCoordsVariants;
    }


    public void setCells(ArrayList<Cell> coords) {

        shipList.get(setupCounter).setLocationCells(coords);

        for (Cell cell : shipList.get(setupCounter).getLocationCells()) {
            getGrid()[cell.row()][cell.col()] = cell;
        }

        setupCounter++;
    }


    public static record AllCoordsVariants(
            ArrayList<Cell>up,
            ArrayList<Cell>down,
            ArrayList<Cell>right,
            ArrayList<Cell>left
    ) {

        public AllCoordsVariants() {
            this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }

        AllCoordsVariants coordsVariantsOf(int i, ArrayList<Cell> coords) {

            return switch (i) {
                case 1 -> new AllCoordsVariants(coords, this.down, this.right, this.left);
                case 2 -> new AllCoordsVariants(this.up, coords, this.right, this.left);
                case 3 -> new AllCoordsVariants(this.up, this.down, coords, this.left);
                case 4 -> new AllCoordsVariants(this.up, this.down, this.right, coords);
                default -> null;
            };
        }

        public ArrayList<ArrayList<Cell>> getArgumentsAsList() {
            ArrayList<ArrayList<Cell>> argumentsList = new ArrayList<>();
            argumentsList.add(this.up);
            argumentsList.add(this.down);
            argumentsList.add(this.right);
            argumentsList.add(this.left);
            return argumentsList;
        }

        public boolean isEmpty() {
            return (up.isEmpty() && down.isEmpty() && right.isEmpty() && left.isEmpty());
        }
    }

}
