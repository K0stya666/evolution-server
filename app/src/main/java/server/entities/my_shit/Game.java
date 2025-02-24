package server.entities.my_shit;

import java.util.LinkedList;

public class Game {
    LinkedList<Card> deckCard;
    {
        deckCard = new LinkedList<>();
        deckCard.add(new Card()); // здесь накидываем
    }


    LinkedList<Player> players;
    public void addPlayer(Player player) {
        players.add(player);
    }

    private int rollDice() {
        return 0;
    }

    private void giveCards() {

    }

    private void developmentPhase() {
        for (Player player : players) {
            // каждый игрок выбирает карту, что-то мутит
            // пока не закончатся карты или не захочет ходить дальше
        }
    }

    private void feedPhase(int countFood) {
        while (countFood > 0) {
            // либо хищник хавает животинку,

            //либо хавается еда из общака
            countFood--;
        }
    }

    /**
     * Фаза вымирания
     */
    private void iwdPhase() {
        // все, кто не поел – смерть
    }

    /**
     * Дораздача кард в конце хода
     */
    private void addCards() {

    }


    public void startGame() {
        // Раздаём карты
        giveCards();

        // выбираем, кто первый

        boolean ended = false;

        while (!ended) {
            developmentPhase();

            // игрок, сходивший первым, кидает кубик
            // его бросок + кол-во игроков – кол-во еды на сегодня :)
//            int countFood = rollDice() + players.size();
            int countFood;

            assert players.size() >= 2 && players.size() <= 4;

            if (players.size() == 2)
                countFood = rollDice() + 2;
            else if (players.size() == 3)
                countFood = rollDice();
            else
                countFood = rollDice() + 2;


            feedPhase(countFood);

            iwdPhase();
            addCards();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player(1L, "Aleshka"));
        game.addPlayer(new Player(2L, "Kostya666"));

        game.startGame();
    }




}
