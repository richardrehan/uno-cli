package com.richardrehan.uno.domain.game;

import com.richardrehan.uno.domain.entities.Deck;
import com.richardrehan.uno.domain.entities.Player;
import com.richardrehan.uno.domain.entities.Stash;
import com.richardrehan.uno.domain.entities.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private Deck deck;
    private Stash stash;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean reverse;

    public Game(InputReader inputReader, OutputWriter outputWriter) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;

        this.deck = new Deck();
        this.stash = new Stash();

        this.players = new ArrayList<>();

        players.add(new Player("Player " + 1, inputReader, false));

        for (int i = 2; i <= 4; i++) {
            players.add(new Player("Player " + i, inputReader, true));
        }


        this.currentPlayerIndex = 0;
        this.reverse = false;
    }

    public void start() {
        outputWriter.write("Welcome to UNO!");
        deck.shuffle();

        for (Player player : players) {
            List<Card> drawnCards = deck.drawCards(7);
            player.addCardsToHand(drawnCards);
        }

        Card startingCard = deck.drawCard();
        stash.addCard(startingCard);

        gameLoop();
    }

    public void gameLoop() {
        boolean gameEnd = false;

        while(!gameEnd) {
            Player currentPlayer = players.get(currentPlayerIndex);

            outputWriter.write("It's " + currentPlayer.getName() + "'s turn.\nCurrent card is " + stash.peekTopCard().toString());

            Card playedCard = currentPlayer.playCard(stash.peekTopCard());

            if (playedCard != null) {
                stash.addCard(playedCard);
                outputWriter.write(currentPlayer.getName() + " played " + playedCard);
                if (currentPlayer.getHand().getCards().size() == 0) {
                    outputWriter.write(currentPlayer.getName() + " wins!");
                    gameEnd = true;
                } else {
                    handleActionCard(playedCard);
                }
            } else {
                outputWriter.write(currentPlayer.getName() + " drew a card.");
                Card drawnCard = deck.drawCard();
                currentPlayer.getHand().addCard(drawnCard);

                // DEBUG
                outputWriter.write("Card: " + drawnCard.toString());
            }

            if (!gameEnd) {
                currentPlayerIndex = getNextPlayerIndex();
            }
        }
    }



    private void handleActionCard(Card playedCard) {
        if (playedCard != null) {
            switch (playedCard.getValue()) {
                case SKIP:
                    outputWriter.write("Skipping next player!");
                    currentPlayerIndex = getNextPlayerIndex();
                    break;
                case DRAW_TWO:
                    outputWriter.write("Drawing two cards for next player!");
                    Player nextPlayer = getNextPlayer();
                    List<Card> drawnCards = deck.drawCards(2);
                    nextPlayer.getHand().addCards(drawnCards);
                    currentPlayerIndex = getNextPlayerIndex();
                    break;
                case REVERSE:
                    outputWriter.write("Reversing direction!");
                    reverse = !reverse;
                    break;
                case WILD:
                    outputWriter.write("Choosing new color!");
                    Card.Color color;
                    if(players.get(currentPlayerIndex).isBot()) {
                        color = Card.Color.values()[new Random().nextInt(Card.Color.values().length)];
                        outputWriter.write("New color: " + color);
                    }else {
                        color =  inputReader.chooseColor();
                    }
                    stash.peekTopCard().setColor(color);
                    break;
                case WILD_DRAW_FOUR:
                    outputWriter.write("Choosing new color!");
                    Card.Color colorDrawFour;
                    if(players.get(currentPlayerIndex).isBot()) {
                        color = Card.Color.values()[new Random().nextInt(Card.Color.values().length)];
                        outputWriter.write("New color: " + color);
                    }else {
                        color =  inputReader.chooseColor();
                    }
                    stash.peekTopCard().setColor(color);

                    outputWriter.write("Drawing two cards for next player!");
                    Player nextPlayerDrawFour = getNextPlayer();
                    List<Card> drawnCardsDrawFour = deck.drawCards(4);
                    nextPlayerDrawFour.getHand().addCards(drawnCardsDrawFour);
                    currentPlayerIndex = getNextPlayerIndex();
                    break;
                default:
                    break;
            }
        }
    }




    private int getNextPlayerIndex() {
        int numPlayers = players.size();
        if (reverse) {
            return (currentPlayerIndex - 1 + numPlayers) % numPlayers;
        } else {
            return (currentPlayerIndex + 1) % numPlayers;
        }
    }

    private Player getNextPlayer() {
        int nextPlayerIndex = getNextPlayerIndex();
        return players.get(nextPlayerIndex);
    }
}
