package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.entities.card.Card;

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
            Card chosenCard = inputReader.chooseCard(playableCards);

            // Choose a color when a wild card is played
            if (chosenCard.getColor().equals(Card.Color.WILD))
            {
                Card.Color color = inputReader.chooseColor();

                chosenCard.setColor(color);
            }

            this.removeCardFromHand(chosenCard);
            return chosenCard;
        }
    }
}
