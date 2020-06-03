package sample;


import java.util.ArrayList;

public class Human extends Player {

    private GameHelper helper = new GameHelper();

    Human() {
        super();
    }

    @Override
    public void setShipsLocations() {
        AllCoordsVariants allCoordsVariants = new AllCoordsVariants();
        for (Ship ship : shipList) {

            while (true) {
                Cell tmpCell = helper.getCellInput("Введите координаты");
                int row = helper.getAlphabet().indexOf(tmpCell.row());
                int col = tmpCell.col();
                for (int currSide = 1; currSide <= 4; currSide++) {
                    ArrayList<Cell> coords = new ArrayList<>();
                    System.out.println(row + " " + col);
                    for (int i = 0; i < ship.getSize(); i++) {

                        if (!helper.getGrid()[row][col].usedForShip()) {
                            if (currSide == 1) coords.add(helper.getGrid()[row++][col]);
                            if (currSide == 2) coords.add(helper.getGrid()[row--][col]);
                            if (currSide == 3) coords.add(helper.getGrid()[row][col++]);
                            if (currSide == 4) coords.add(helper.getGrid()[row][col--]);
                            if (row >= helper.getGrid().length || col >= helper.getGrid().length || row < 0 || col < 0)
                                break;
                        } else break;
                    }

                    if (coords.size() == ship.getSize()) allCoordsVariants  = allCoordsVariants.coordsVariantsOf(currSide,coords);

                }

                if (allCoordsVariants.isEmpty())
                    System.out.println("По вашей ячейке нельзя пойти ни в какую сторону, введите другую");
                else break;
            }

            allCoordsVariants.printWhereToGo();
            handleUserInput(ship, promptAndGetSideFromUser(), allCoordsVariants);
        }

    }

    private String promptAndGetSideFromUser() {
        return helper.getUserInput("Введите сторону света в которую хотите пойти");
    }

    private void handleUserInput(Ship ship, String side, AllCoordsVariants allCoordsVariants) {
        if (side.equals("вверх")) ship.setLocationCells(allCoordsVariants.up);
        if (side.equals("вниз")) ship.setLocationCells(allCoordsVariants.down);
        if (side.equals("вправо")) ship.setLocationCells(allCoordsVariants.right);
        if (side.equals("влево")) ship.setLocationCells(allCoordsVariants.left);
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

        AllCoordsVariants coordsVariantsOf(int i, ArrayList<Cell> coords) {//
            return switch (i) {
                case 1 -> new AllCoordsVariants(coords, this.down, this.right, this.left);
                case 2 ->  new AllCoordsVariants(this.up, coords, this.right, this.left);
                case 3 ->  new AllCoordsVariants(this.up, this.down, coords, this.left);
                case 4 -> new AllCoordsVariants(this.up, this.down, this.right, coords);
                default -> null;
            };
        }

        void printWhereToGo() {
            System.out.println();
            if (!up.isEmpty()) System.out.print("Вы можете пойти вверх ");
            if (!down.isEmpty()) System.out.print("Вы можете пойти вниз ");
            if (!right.isEmpty()) System.out.print("Вы можете пойти вправо ");
            if (!left.isEmpty()) System.out.print("Вы можете пойти влево ");
        }

        public boolean isEmpty() {
            return (up.isEmpty() && down.isEmpty() && right.isEmpty() && left.isEmpty());
        }
    }

    @Override
    public Cell makeGuess() {
        return helper.getCellInput("Сделайте ход");
    }

}
