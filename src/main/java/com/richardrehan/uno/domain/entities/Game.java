package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.CardManager;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.PlayerManager;
import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.gamemode.GameMode;

import java.util.List;

public class Game {
    private GameMode gameMode;
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private CardManager cardManager;
    private PlayerManager playerManager;

    public Game(InputReader inputReader, OutputWriter outputWriter, GameMode gameMode, int numHumanPlayers, int numBotPlayers) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.gameMode = gameMode;

        this.cardManager = new CardManager();
        this.playerManager = new PlayerManager(numHumanPlayers, numBotPlayers, inputReader);
    }

    public void start() {
        if(this.gameMode != null) {
            outputWriter.write("Welcome to UNO!");
            this.gameMode.start(this);
        } else {
            outputWriter.write("GameMode not set!");
        }
    }

    public void handleActionCard(Card playedCard) {
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

    public void initializePlayerHands(int amount) {
        List<Player> players = this.playerManager.getPlayers();

        for (Player player : players) {
            List<Card> drawnCards = cardManager.drawCards(amount);
            player.addCardsToHand(drawnCards);
        }
    }

    public void initiateFirstCard() {
        boolean invalidCard = true;

        while(invalidCard) {
            Card startingCard = cardManager.drawCard();
            cardManager.addCardToStash(startingCard);

            if(startingCard.getColor() != Card.Color.WILD) {
                invalidCard = false;
            }
        }
    }

    public Player getCurrentPlayer() {
        return this.playerManager.getCurrentPlayer();
    }
}
