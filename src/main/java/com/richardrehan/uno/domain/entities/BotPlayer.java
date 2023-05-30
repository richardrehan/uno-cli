package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.entities.card.Card;

import java.util.List;
import java.util.Random;

public class BotPlayer extends Player
{
    private static final int MINIMUM_THINKING_TIME = 1000;
    private static final int MAX_THINKING_TIME = 5000;

    public BotPlayer(String name, InputReader inputReader)
    {
        super(name, inputReader);
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
            simulateThinkingTime();

            Card chosenCard = playableCards.get(0);

            // Choose a color when a wild card is played
            if (chosenCard.getColor().equals(Card.Color.WILD))
            {
                Card.Color color = Card.Color.values()[new Random().nextInt(Card.Color.values().length - 1)];

                chosenCard.setColor(color);
            }

            this.removeCardFromHand(chosenCard);
            return chosenCard;
        }
    }

    private void simulateThinkingTime()
    {
        try
        {
            // Random thinking time
            int thinkingTime = (int) (Math.random() * (MAX_THINKING_TIME - MINIMUM_THINKING_TIME + 1)) + MINIMUM_THINKING_TIME;

            Thread.sleep(thinkingTime);
        } catch (InterruptedException e)
        {
            // Handle exception if needed
        }
    }
}
