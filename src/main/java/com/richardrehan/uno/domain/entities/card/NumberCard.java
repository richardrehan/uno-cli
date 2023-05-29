package com.richardrehan.uno.domain.entities.card;

public class NumberCard extends Card {

    public NumberCard(Color color, int value) {
        super(color, Value.fromInt(value));
    }

}
