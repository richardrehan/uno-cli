package com.richardrehan.uno.domain.entities.card;

import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

public abstract class Card
{
    private Color color;
    private final Value value;

    public Card(Color color, Value value)
    {
        this.color = color;
        this.value = value;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Value getValue()
    {
        return value;
    }

    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter)
    {
        // Action
    }

    public boolean canBePlayedOn(Card otherCard)
    {
        if (this.getColor() == otherCard.getColor() || this.getValue() == otherCard.getValue() || this.getColor() == Card.Color.WILD || otherCard == null)
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
        return color + " " + value;
    }

    public enum Color
    {
        RED,
        YELLOW,
        GREEN,
        BLUE,
        WILD
    }

    public enum Value
    {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        SKIP,
        REVERSE,
        DRAW_TWO,
        WILD,
        WILD_DRAW_FOUR;

        public static Value fromInt(int value)
        {
            if (value >= 0 && value <= 9)
            {
                return Value.values()[value];
            }
            throw new IllegalArgumentException("Invalid value for card: " + value);
        }
    }
}
