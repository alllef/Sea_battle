package sample;

import java.util.ArrayList;
import java.util.List;

abstract public class Player {
    protected List<Ship> shipList = new ArrayList<>();

    Player() {
        setupShips();
    }

    public void setupShips() {
        shipList.add(new Ship(5));
        shipList.add(new Ship(4));
        shipList.add(new Ship(4));
        shipList.add(new Ship(3));
        shipList.add(new Ship(3));
        shipList.add(new Ship(3));
        shipList.add(new Ship(2));
        shipList.add(new Ship(2));
        shipList.add(new Ship(2));
        shipList.add(new Ship(2));
        shipList.add(new Ship(1));
        shipList.add(new Ship(1));
        shipList.add(new Ship(1));
        shipList.add(new Ship(1));
        shipList.add(new Ship(1));
    }

   abstract public void setShipsLocations();

    abstract public Cell makeGuess();

    void checkGuess(Cell playerGuess) {
        String result = "Мимо";//Подразумевает промах, пока не выяснили обратного

        for (Ship shipToTest : shipList) {// повторяем это для всех объектов DotCom в списке
            result = shipToTest.checkYourself(playerGuess);//Просим DotCom проверить пользователя(ищет попадание или потопление)

            if (result.equals("Попал")) {
                break;//выбираем из цикла раньше времени нет смысла проверять другие сайты
            }

            if (result.equals("Потопил")) {//Ему пришел конец, так что удаляем его из списка сайтов
                shipList.remove(shipToTest);
                break;
            }

        }

        System.out.println(result);//выводим для пользователя результат
    }


}
