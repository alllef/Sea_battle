package sample;


import java.util.ArrayList;

public class Human extends Player {

    Integer setupCounter = 0;

    Human() {
        super();
    }


    public AllCoordsVariants setupShip(int roww, int coll) {

        AllCoordsVariants allCoordsVariants = new AllCoordsVariants();


        while (true) {

            for (int currSide = 1; currSide <= 4; currSide++) {
                int row = roww;
                int col = coll;
                ArrayList<Cell> coords = new ArrayList<>();

                for (int i = 0; i < shipList.get(setupCounter).getSize(); i++) {

                    if (!getGrid()[row][col].usedForShip()) {
                        if (currSide == 1) coords.add(getGrid()[row--][col]);
                        if (currSide == 2) coords.add(getGrid()[row++][col]);
                        if (currSide == 3) coords.add(getGrid()[row][col++]);
                        if (currSide == 4) coords.add(getGrid()[row][col--]);
                        if (row >= getGrid().length || col >= getGrid().length || row < 0 || col < 0)
                            break;
                    } else break;
                }
                System.out.println(row + " " + col);
                if (coords.size() == shipList.get(setupCounter).getSize())
                    allCoordsVariants = allCoordsVariants.coordsVariantsOf(currSide, coords);

            }
            break;
        }

        if (allCoordsVariants.isEmpty()) return null;
        System.out.println(allCoordsVariants.up().size()+" "+allCoordsVariants.left().size()+" "+allCoordsVariants.right().size()+" "+allCoordsVariants.down().size());
        return allCoordsVariants;
    }


    void setCells(ArrayList<Cell> coords) {
        shipList.get(setupCounter).setLocationCells(coords);
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


        public boolean isEmpty() {
            return (up.isEmpty() && down.isEmpty() && right.isEmpty() && left.isEmpty());
        }
    }

    @Override
    public Cell makeGuess() {
        return new Cell(0,0);
    }

}
