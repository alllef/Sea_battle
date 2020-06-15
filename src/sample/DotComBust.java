package sample;

import java.util.ArrayList;
import java.util.List;

public class DotComBust {

    private GameHelper helper = new GameHelper();

    private List<Ship> dotComsList = new ArrayList<>();
    ;//Объявляем и инициализируем перемменные, которые нам понадобятся
    private int numOfGuesses = 0;

    private void setUpGame() {
        Human human = new Human();
        Computer computer = new Computer();
        //human.setShipsLocations();
        computer.setShipsLocations();
    }

    private void startPlaying() {
        Human human = new Human();
        Computer computer = new Computer();
        //human.setShipsLocations();
        computer.setShipsLocations();
        while (!computer.shipList.isEmpty()||!human.shipList.isEmpty()) {//До тех пор пока список объектов в DotCom не станет путсым
           Cell guess = human.makeGuess();
            computer.checkGuess(guess);
          guess =  computer.makeGuess();
            human.checkGuess(guess);
        }
    }


    private void finishGame() {
        System.out.println("Все \"сайты\"");

        if (numOfGuesses <= 18) {
            System.out.println("Это заняло у вас всего" + numOfGuesses + "попыток");
            System.out.println("Это заняло у вас всего" + numOfGuesses + "попыток");//Выводим сообщение о том, как пользователь прошел игру
            System.out.println("Рыбы водят хороводы вокруг ваши вложений");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();//Создаем игровой объект
        game.startPlaying();
    }
}
