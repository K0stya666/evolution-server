package server.cards;

import lombok.Getter;
import server.entities.Player;

import java.util.LinkedList;

@Getter
public class Battle {
    private final Deck deck;

    private final LinkedList<Player> players;

    public Battle() {
        this.deck = new Deck();
        this.players = new LinkedList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public int countPlayers() {
        return players.size();
    }

    private int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    public void giveCards() {
        for (int i = 0; i < 6; i++) {
            for (var p : players) {
                p.addCard(deck.takeCard());
            }
        }
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
        for (Player player : players) {
            int countCards = player.getAnimals().size() + 1;
            for (int i = 0; i < countCards; i++) {
                player.addCard(deck.takeCard());
            }
        }
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
                countFood = rollDice() + rollDice();
            else
                countFood = rollDice() + rollDice() + 2;


            feedPhase(countFood);

            iwdPhase();
            addCards();
        }
    }

//    public static void main(String[] args) {
//        Deck deck = new Deck();
//        Battle game = new Battle(deck);
////        game.addPlayer(new Player(1L, "Aleshka"));
////        game.addPlayer(new Player(2L, "Kostya666"));
//
//        game.startGame();
//    }




}
