package com.richardrehan.uno.domain.entities.card;

public final class CardProperties
{
    private final Color color;
    private final Value value;

    public CardProperties(Color color, Value value)
    {
        this.color = color;
        this.value = value;
    }

    public Color getColor()
    {
        return color;
    }

    public Value getValue()
    {
        return value;
    }

    @Override
    public boolean equals(Object obj)
    {
        CardProperties cardProperties;

        try
        {
            cardProperties = (CardProperties) obj;
        } catch (Exception e)
        {
            return false;
        }

        if (cardProperties.getColor() == this.getColor() && cardProperties.getValue() == this.getValue())
        {
            return true;
        }

        return false;
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
