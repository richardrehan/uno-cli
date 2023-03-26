package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.entities.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        this.cards = new Stack<>();

        // Add all the possible combinations of colors and values to the deck
        for (Card.Color color : Card.Color.values()) {
            if (color == Card.Color.WILD) {
                // Skip black cards, since they don't have a specific color
                continue;
            }
            for (Card.Value value : Card.Value.values()) {
                if (value == Card.Value.WILD || value == Card.Value.WILD_DRAW_FOUR) {
                    // Skip wild cards, since they don't have a specific color
                    continue;
                }
                cards.push(new Card(color, value));

                if (value != Card.Value.ZERO) {
                    // Add two copies of each action card to the deck, except for "0"
                    cards.push(new Card(color, value));
                }
            }
        }

        // Add four copies of each wild card to the deck
        for (int i = 0; i < 4; i++) {
            cards.push(new Card(Card.Color.WILD, Card.Value.WILD));
            cards.push(new Card(Card.Color.WILD, Card.Value.WILD_DRAW_FOUR));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }


    public Card peekTopCard() {
        return cards.peek();
    }

    public Card drawCard() {
        return cards.pop();
    }

    public List<Card> drawCards(int number) {
        List<Card> drawingCards = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            drawingCards.add(drawCard());
        }

        return drawingCards;
    }
}
