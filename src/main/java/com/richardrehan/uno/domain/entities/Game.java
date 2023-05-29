package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.CardManager;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.PlayerManager;
import com.richardrehan.uno.domain.entities.Player;
import com.richardrehan.uno.domain.entities.card.Card;

import java.util.List;

public class Game {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private CardManager cardManager;
    private PlayerManager playerManager;

    public Game(InputReader inputReader, OutputWriter outputWriter, int numHumanPlayers, int numBotPlayers) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;

        this.cardManager = new CardManager();
        this.playerManager = new PlayerManager(numHumanPlayers, numBotPlayers, inputReader);
    }

    public void start() {
        outputWriter.write("Welcome to UNO!");

        this.initializePlayersHands();

        Card startingCard = cardManager.drawCard();
        cardManager.addCardToStash(startingCard);

        gameLoop();
    }

    public void gameLoop() {
        boolean gameEnd = false;

        while(!gameEnd) {
            outputWriter.write("\nIt's " + getCurrentPlayer().getName() + "'s turn.");
            outputWriter.write("They have " + getCurrentPlayer().getHand().getCards().size() + " Cards.");
            outputWriter.write("Current card is " + cardManager.peekTopCardStash().toString() + "\n");

            Card playedCard = getCurrentPlayer().playCard(cardManager.peekTopCardStash());

            if (playedCard != null) {
                cardManager.addCardToStash(playedCard);
                outputWriter.write(getCurrentPlayer().getName() + " played " + playedCard);

                if (getCurrentPlayer().getHand().getCards().size() == 0) {
                    outputWriter.write(getCurrentPlayer().getName() + " wins!");
                    gameEnd = true;
                } else {
                    handleActionCard(playedCard);
                }
            } else {
                outputWriter.write(getCurrentPlayer().getName() + " drew a card.");
                Card drawnCard = cardManager.drawCard();
                getCurrentPlayer().getHand().addCard(drawnCard);
            }

            this.playerManager.nextPlayer();
        }
    }



    private void handleActionCard(Card playedCard) {
        if (playedCard != null) {
            playedCard.executeAction(this, inputReader, outputWriter);
        }
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public CardManager getCardManager() {
        return this.cardManager;
    }

    public InputReader getInputReader() {
        return this.inputReader;
    }

    private void initializePlayersHands() {
        List<Player> players = this.playerManager.getPlayers();

        for (Player player : players) {
            List<Card> drawnCards = cardManager.drawCards(7);
            player.addCardsToHand(drawnCards);
        }
    }


    public Player getCurrentPlayer() {
        return this.playerManager.getCurrentPlayer();
    }
}
