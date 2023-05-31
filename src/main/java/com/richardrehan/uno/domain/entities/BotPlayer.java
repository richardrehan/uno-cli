package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.card.CardProperties;
import com.richardrehan.uno.domain.strategy.FastThinkerStrategy;
import com.richardrehan.uno.domain.strategy.NormalStrategy;
import com.richardrehan.uno.domain.strategy.PlayStrategy;

import java.util.List;
import java.util.Random;

public class BotPlayer extends Player
{
    private PlayStrategy playStrategy;

    public BotPlayer(String name, InputReader inputReader)
    {
        super(name, inputReader);
        this.playStrategy = new NormalStrategy();
    }

    public BotPlayer(String name, InputReader inputReader, PlayStrategy playStrategy)
    {
        super(name, inputReader);
        this.playStrategy = playStrategy;
    }

    @Override
    public Card playCard(Card currentCard)
    {
        List<Card> playableCards = getPlayableCards(currentCard);
        if (playableCards.isEmpty())
        {
            return null;
        } else
        {
            Card chosenCard = chooseCard(playableCards);

            this.removeCardFromHand(chosenCard);

            return chosenCard;
        }
    }

    @Override
    protected Card chooseCard(List<Card> playableCards)
    {
        simulateThinkingTime();

        Card chosenCard = playStrategy.chooseCard(playableCards);

        // Choose a color when a wild card is played
        if (chosenCard.getColor().equals(CardProperties.Color.WILD))
        {
            CardProperties.Color color = chooseCardColor();
            chosenCard.setColor(color);
        }

        return chosenCard;
    }

    @Override
    protected CardProperties.Color chooseCardColor()
    {
        return CardProperties.Color.values()[new Random().nextInt(CardProperties.Color.values().length - 1)];
    }

    private void simulateThinkingTime()
    {
        try
        {
            int thinkingTime = playStrategy.thinkingTime();
            Thread.sleep(thinkingTime);
        } catch (InterruptedException e)
        {
            // Handle exception if needed
        }
    }

    public PlayStrategy getPlayStrategy()
    {
        return this.playStrategy;
    }

    public void setPlayStrategy(PlayStrategy playStrategy)
    {
        this.playStrategy = playStrategy;
    }

}
