package server.cards;

import lombok.Getter;
import org.springframework.stereotype.Service;
import server.entities.Player;

import java.util.LinkedList;

//@Service
public class Battle {
    @Getter
    private Deck deck;


    LinkedList<Player> players;

    public Battle() {
        this.deck = new Deck();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    private int rollDice() {
        return 0;
    }

    private void giveCards() {
        for (int i = 0; i < 6; i++) {
            for (var p : players) {
                var newCard = deck.takeCard();
                var cards = p.getCards();
                cards.add(newCard);
                p.setCards(cards);
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

//    public static void main(String[] args) {
//        Deck deck = new Deck();
//        Battle game = new Battle(deck);
////        game.addPlayer(new Player(1L, "Aleshka"));
////        game.addPlayer(new Player(2L, "Kostya666"));
//
//        game.startGame();
//    }




}
