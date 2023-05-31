package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.card.CardProperties;

import java.util.List;

public class HumanPlayer extends Player
{
    public HumanPlayer(String name, InputReader inputReader)
    {
        super(name, inputReader);
    }

    @Override
    public Card playCard(Card currentCard)
    {
        List<Card> nonPlayableCards = getNonPlayableCards(currentCard);
        System.out.println(nonPlayableCards);
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
        Card chosenCard = inputReader.chooseCard(playableCards);

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
        return inputReader.chooseColor();
    }
}
