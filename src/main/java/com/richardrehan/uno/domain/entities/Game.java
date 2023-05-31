package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.*;
import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.card.CardProperties;
import com.richardrehan.uno.domain.gamemode.GameMode;

import java.util.List;

public class Game
{
    private GameMode gameMode;
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private CardManager cardManager;
    private PlayerManager playerManager;
    private GameEvent gameEvent;

    public Game(InputReader inputReader, OutputWriter outputWriter, GameMode gameMode, int numHumanPlayers, int numBotPlayers, GameEvent gameEvent)
    {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.gameMode = gameMode;

        this.gameEvent = gameEvent;

        this.cardManager = new CardManager();
        this.playerManager = new PlayerManager(numHumanPlayers, numBotPlayers, inputReader);
    }

    public void start()
    {
        if (this.gameMode != null)
        {
            gameEvent.onGameStart();
            this.gameMode.start(this);
        } else
        {
            outputWriter.write("GameMode not set!");
        }
    }

    public void handleActionCard(Card playedCard)
    {
        if (playedCard != null)
        {
            playedCard.executeAction(this, inputReader, outputWriter);
        }
    }

    public PlayerManager getPlayerManager()
    {
        return this.playerManager;
    }

    public CardManager getCardManager()
    {
        return this.cardManager;
    }

    public InputReader getInputReader()
    {
        return this.inputReader;
    }

    public void initializePlayerHands(int amount)
    {
        List<Player> players = this.playerManager.getPlayers();

        for (Player player : players)
        {
            List<Card> drawnCards = cardManager.drawCards(amount);
            player.addCardsToHand(drawnCards);
        }
    }

    public void initiateFirstCard()
    {
        boolean invalidCard = true;

        while (invalidCard)
        {
            Card startingCard = cardManager.drawCard();
            cardManager.addCardToStash(startingCard);

            if (startingCard.getColor() != CardProperties.Color.WILD)
            {
                invalidCard = false;
            }
        }
    }

    public GameEvent getGameEvent() {
        return gameEvent;
    }

    public Card getCurrentTopCard() {
        return getCardManager().peekTopCardStash();
    }

    public void onTurnChange() {
        getGameEvent().onTurnChange(getCurrentPlayer(), getCurrentTopCard());
    }

    public void onCardPlayed(Card card) {
        getGameEvent().onCardPlayed(getCurrentPlayer(), card);
    }

    public void drawCard(Player player) {
        player.drawCards(getCardManager(), 1);
        getGameEvent().onCardDrawn(player);
    }

    public void drawCards(Player player, int amount) {
        player.drawCards(getCardManager(), amount);
        getGameEvent().onMultipleCardsDrawn(player, amount);
    }

    public void reverseOrder() {
        getPlayerManager().reverseOrder();
        getGameEvent().onReversingOrder();
    }

    public void skipPlayer() {
        Player nextPlayer = getPlayerManager().getNextPlayer();
        getPlayerManager().nextPlayer();
        getGameEvent().onSkippingPlayer(nextPlayer);
    }

    public Card playCard(Player player) {
        Card playedCard = player.playCard(getCardManager().peekTopCardStash());
        return playedCard;
    }

    public Player getCurrentPlayer()
    {
        return this.playerManager.getCurrentPlayer();
    }
}
