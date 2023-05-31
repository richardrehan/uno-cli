package com.richardrehan.uno.domain.entities.card;

import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

public abstract class Card
{
    private CardProperties cardProperties;

    public Card(CardProperties cardProperties)
    {
        this.cardProperties = cardProperties;
    }

    public CardProperties.Color getColor()
    {
        return this.cardProperties.getColor();
    }

    public void setColor(CardProperties.Color color)
    {
        CardProperties cardPropertiesNew = new CardProperties(color, this.cardProperties.getValue());
        this.cardProperties = cardPropertiesNew;
    }

    public CardProperties.Value getValue()
    {
        return this.cardProperties.getValue();
    }

    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter)
    {
        // Action
    }

    public boolean canBePlayedOn(Card otherCard)
    {
        if (this.getColor() == otherCard.getColor() || this.getValue() == otherCard.getValue() || this.getColor() == CardProperties.Color.WILD || otherCard == null)
        {
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return getColor() + " " + getValue();
    }
}
