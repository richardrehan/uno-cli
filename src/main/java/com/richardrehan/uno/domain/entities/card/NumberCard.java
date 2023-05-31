package com.richardrehan.uno.domain.entities.card;

public class NumberCard extends Card
{

    public NumberCard(CardProperties.Color color, int value)
    {
        super(new CardProperties(color, CardProperties.Value.fromInt(value)));
    }

    public NumberCard(CardProperties.Color color, CardProperties.Value value)
    {
        super(new CardProperties(color, value));
    }

}
