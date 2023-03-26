package com.richardrehan.uno.domain.entities.card;

public class Card {
    private Color color;
    private final Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Value getValue() {
        return value;
    }

    public boolean isWildCard() {
        return value == Value.WILD || value == Value.WILD_DRAW_FOUR;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }

    public enum Color {
        RED,
        YELLOW,
        GREEN,
        BLUE,
        WILD
    }

    public enum Value {
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
        WILD_DRAW_FOUR
    }
}
